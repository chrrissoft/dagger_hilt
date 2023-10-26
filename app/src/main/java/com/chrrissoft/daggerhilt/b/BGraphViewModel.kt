package com.chrrissoft.daggerhilt.b

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
class BGraphViewModel @Inject constructor(
    app: DaggerHiltApp,
    workManager: WorkManager,
    @BGraphBridgeDependencyModule.BGraphBridgeQualifier
    dependency: FlowContainer<SharedDependency>,
    @BGraphComponentManagerModule.BGraphComponentManagerQualifier
    manager: ComponentManager<BGraphComponentEntryPoint, BGraphComponent>,
    @SharedDependencyDependencyEntryModule.MutableEntryQualifier
    mutableEntry: MutableStateFlow<String>,
    @BGraphBridgeClientActionsModule.BGraphBridgeClientActionsWorkerAction
    bridgeWorkerActionAction: FlowContainer<MutableStateFlow<ClientAction?>>,
    @BGraphBridgeClientActionsModule.BGraphBridgeClientActionsServiceAction
    bridgeServiceActionAction: FlowContainer<MutableStateFlow<ClientAction?>>,
    @BGraphBridgeActiveBackComponentsModule.BGraphBridgeActiveBackComponentsServiceActive
    serviceActive: FlowContainer<MutableStateFlow<Boolean>>,
    @BGraphBridgeActiveBackComponentsModule.BGraphBridgeActiveBackComponentsReceiverActive
    receiverActive: FlowContainer<MutableStateFlow<Boolean>>,
) : BaseViewModel(
    app = app,
    workerTag = BGraphWorker::class.java.name,
    manager = manager,
    workManager = workManager,
    mutableEntry = mutableEntry,
    dependencyFlow = dependency,
    serviceRunningFlow = serviceActive,
    receiverRunningFlow = receiverActive,
    receiverFilter = BGraphReceiver.filter,
    serviceIntent = Intent(app, BGraphService::class.java),
    request = OneTimeWorkRequestBuilder<BGraphWorker>().addTag(BGraphWorker::class.java.name).build(),
    workerActionFlow = bridgeWorkerActionAction,
    serviceActionFlow = bridgeServiceActionAction,
    onChangeAction = BGraphReceiver.onChangeStateAction,
) {
    override fun getReceiver(): BaseReceiver {
        return BGraphReceiver()
    }
}
