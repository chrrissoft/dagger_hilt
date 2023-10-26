package com.chrrissoft.daggerhilt.common

import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo.State.RUNNING
import androidx.work.WorkManager
import com.chrrissoft.daggerhilt.DaggerHiltApp
import com.chrrissoft.daggerhilt.Utils.asFlow
import com.chrrissoft.daggerhilt.Utils.direction
import com.chrrissoft.daggerhilt.common.ClientAction.*
import com.chrrissoft.daggerhilt.common.Dependencies.SharedDependency
import com.chrrissoft.daggerhilt.common.ScreenEvent.*
import com.chrrissoft.daggerhilt.common.ScreenState.BackgroundComponents
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope as scope

abstract class BaseViewModel(
    private val onChangeAction: String,
    private val app: DaggerHiltApp,
    private val workManager: WorkManager,
    private val workerTag: String,
    private val mutableEntry: MutableStateFlow<String>,
    private val serviceIntent: Intent,
    private val request: OneTimeWorkRequest,
    private val receiverFilter: IntentFilter,
    dependencyFlow: FlowContainer<SharedDependency>,
//    dependencyParentFlow: FlowContainer<SharedDependency>? = null,
    serviceRunningFlow: FlowContainer<MutableStateFlow<Boolean>>,
    receiverRunningFlow: FlowContainer<MutableStateFlow<Boolean>>,
    workerActionFlow: FlowContainer<MutableStateFlow<ClientAction?>>,
    serviceActionFlow: FlowContainer<MutableStateFlow<ClientAction?>>,
    override val manager: ComponentManager<*, *>,
) : ViewModel(), ComponentManagerClient {
    private val _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()
    private lateinit var dependency: SharedDependency
    private lateinit var serviceRunning: MutableStateFlow<Boolean>
    private lateinit var receiverRunning: MutableStateFlow<Boolean>
    private lateinit var workerAction: MutableStateFlow<ClientAction?>
    private lateinit var serviceAction: MutableStateFlow<ClientAction?>

    init {
        scope.launch(Default) {
            workManager.getWorkInfosByTagLiveData(workerTag).asFlow(scope).collect { infos ->
                val test = infos.any { it.state==RUNNING }
                _state.update { it.copy(backComponents = it.backComponents.copy(worker = test)) }
            }
        }
        dependencyFlow.collect(scope) { sharedDependency ->
            dependency = sharedDependency
            updateState(
                reference = sharedDependency.direction,
                dependencyState = sharedDependency.state.value,
            )
            collectDependencyState(sharedDependency) {
                updateState(dependencyState = it)
            }
        }
        workerActionFlow.collect(scope) { workerAction = it }
        serviceActionFlow.collect(scope) { serviceAction = it }
        serviceRunningFlow.collect(scope) { serviceRunning = it; observeServiceRunning(it) }
        receiverRunningFlow.collect(scope) { receiverRunning = it; observeReceiverRunning(it) }
    }

    private fun updateDependency(state: String) {
        dependency.state.update {
            app.sendBroadcast(Intent(onChangeAction))
            state
        }
    }

    fun updateEntry(entry: String) {
        mutableEntry.update { entry }
    }

    fun onWork() {
        if (state.value.backComponents.worker) {
            workManager.cancelAllWorkByTag(workerTag)
            return
        }
        workManager.enqueue(request)
    }

    fun onService() {
        if (serviceRunning.value) app.stopService(serviceIntent)
        else app.startService(serviceIntent)
    }

    fun onReceiver() {
        if (receiverRunning.value) {
            app.unregisterReceiver(app.receivers.remove(onChangeAction))
            receiverRunning.update { false }
        } else {
            val receiver = getReceiver()
            app.registerReceiver(receiver, receiverFilter)
            app.receivers[onChangeAction] = receiver
            receiverRunning.update { true }
        }
    }

    abstract fun getReceiver(): BaseReceiver

    private fun updateBackComponentsState(
        worker: Boolean = state.value.backComponents.worker,
        service: Boolean = state.value.backComponents.service,
        receiver: Boolean = state.value.backComponents.receiver,
    ) {
        updateState(
            backComponents = state.value.backComponents.copy(
                worker = worker, service = service, receiver = receiver,
            )
        )
    }

    private fun updateState(
        reference: String = state.value.reference,
        dependencyState: String = state.value.dependencyState,
        backComponents: BackgroundComponents = state.value.backComponents,
    ) {
        _state.update {
            it.copy(
                reference = reference,
                dependencyState = dependencyState,
                backComponents = backComponents,
            )
        }
    }


    private fun collectDependencyState(dependency: SharedDependency, block: (String) -> Unit) {
        scope.launch(Default) { dependency.state.collect { block(it) } }
    }

    private fun observeServiceRunning(state: StateFlow<Boolean>) {
        scope.launch(Default) { state.collect { updateBackComponentsState(service = it) } }
    }

    private fun observeReceiverRunning(state: StateFlow<Boolean>) {
        scope.launch(Default) { state.collect { updateBackComponentsState(receiver = it) } }
    }

    fun handleEvent(event: ScreenEvent) {
        when (event) {
            is OnChangeText -> updateDependency(event.data)
            is OnScreenClientAction -> {
                when (event.data) {
                    Destroy -> destroy()
                    Rebuild -> rebuild()
                }
            }
            is OnWorkerClientAction -> workerAction.update { event.data }
            is OnServiceClientAction -> serviceAction.update { event.data }
        }
    }
}
