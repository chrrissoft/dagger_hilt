package com.chrrissoft.daggerhilt.a

import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.chrrissoft.daggerhilt.DaggerHiltApp
import com.chrrissoft.daggerhilt.common.*
import com.chrrissoft.daggerhilt.common.Dependencies.SharedDependency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AGraphViewModel @Inject constructor(
    app: DaggerHiltApp,
    workManager: WorkManager,
    @AGraphBridgeDependencyModule.AGraphBridgeQualifier
    dependency: FlowContainer<SharedDependency>,
    @AGraphComponentManagerModule.AGraphComponentManagerQualifier
    manager: ComponentManager<AGraphComponentEntryPoint, AGraphComponent>,
    @SharedDependencyDependencyEntryModule.MutableEntryQualifier
    mutableEntry: MutableStateFlow<String>,
    @AGraphBridgeClientActionsModule.AGraphBridgeClientActionsWorkerAction
    bridgeWorkerActionAction: FlowContainer<MutableStateFlow<ClientAction?>>,
    @AGraphBridgeClientActionsModule.AGraphBridgeClientActionsServiceAction
    bridgeServiceActionAction: FlowContainer<MutableStateFlow<ClientAction?>>,
    @AGraphBridgeActiveBackComponentsModule.AGraphBridgeActiveBackComponentsServiceActive
    serviceActive: FlowContainer<MutableStateFlow<Boolean>>,
    @AGraphBridgeActiveBackComponentsModule.AGraphBridgeActiveBackComponentsReceiverActive
    receiverActive: FlowContainer<MutableStateFlow<Boolean>>,
) : BaseViewModel(
    app = app,
    workerTag = AGraphWorker::class.java.name,
    manager = manager,
    workManager = workManager,
    mutableEntry = mutableEntry,
    dependencyFlow = dependency,
    serviceRunningFlow = serviceActive,
    receiverRunningFlow = receiverActive,
    receiverFilter = AGraphReceiver.filter,
    serviceIntent = Intent(app, AGraphService::class.java),
    request = OneTimeWorkRequestBuilder<AGraphWorker>().addTag(AGraphWorker::class.java.name).build(),
    workerActionFlow = bridgeWorkerActionAction,
    serviceActionFlow = bridgeServiceActionAction,
    onChangeAction = AGraphReceiver.onChangeStateAction,
) {
    override fun getReceiver(): BaseReceiver {
        return AGraphReceiver()
    }
}
