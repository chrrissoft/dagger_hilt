package com.chrrissoft.daggerhilt.c

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
class CGraphViewModel @Inject constructor(
    app: DaggerHiltApp,
    workManager: WorkManager,
    @CGraphBridgeDependencyModule.CGraphBridgeQualifier
    dependency: FlowContainer<SharedDependency>,
    @CGraphComponentManagerModule.CGraphComponentManagerQualifier
    manager: ComponentManager<CGraphComponentEntryPoint, CGraphComponent>,
    @SharedDependencyDependencyEntryModule.MutableEntryQualifier
    mutableEntry: MutableStateFlow<String>,
    @CGraphBridgeClientActionsModule.CGraphBridgeClientActionsWorkerAction
    bridgeWorkerActionAction: FlowContainer<MutableStateFlow<ClientAction?>>,
    @CGraphBridgeClientActionsModule.CGraphBridgeClientActionsServiceAction
    bridgeServiceActionAction: FlowContainer<MutableStateFlow<ClientAction?>>,
    @CGraphBridgeActiveBackComponentsModule.CGraphBridgeActiveBackComponentsServiceActive
    serviceActive: FlowContainer<MutableStateFlow<Boolean>>,
    @CGraphBridgeActiveBackComponentsModule.CGraphBridgeActiveBackComponentsReceiverActive
    receiverActive: FlowContainer<MutableStateFlow<Boolean>>,
) : BaseViewModel(
    app = app,
    workerTag = CGraphWorker::class.java.name,
    manager = manager,
    workManager = workManager,
    mutableEntry = mutableEntry,
    dependencyFlow = dependency,
    serviceRunningFlow = serviceActive,
    receiverRunningFlow = receiverActive,
    receiverFilter = CGraphReceiver.filter,
    serviceIntent = Intent(app, CGraphService::class.java),
    request = OneTimeWorkRequestBuilder<CGraphWorker>().addTag(CGraphWorker::class.java.name).build(),
    workerActionFlow = bridgeWorkerActionAction,
    serviceActionFlow = bridgeServiceActionAction,
    onChangeAction = CGraphReceiver.onChangeStateAction,
) {
    override fun getReceiver(): BaseReceiver {
        return CGraphReceiver()
    }
}
