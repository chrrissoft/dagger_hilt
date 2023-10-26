package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.common.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@AndroidEntryPoint
class CGraphService : BaseService() {
    override val notificationId = NOTIFICATION_ID
    @Inject
    @CGraphBridgeDependencyModule.CGraphBridgeQualifier
    override lateinit var dependencyFlow: FlowContainer<Dependencies.SharedDependency>
    @Inject
    @CGraphBridgeClientActionsModule.CGraphBridgeClientActionsServiceAction
    override lateinit var actionFlowFlow: FlowContainer<MutableStateFlow<ClientAction?>>
    @Inject
    @CGraphBridgeActiveBackComponentsModule.CGraphBridgeActiveBackComponentsServiceActive
    override lateinit var runningStateFlow: FlowContainer<MutableStateFlow<Boolean>>

    @Inject
    @CGraphComponentManagerModule.CGraphComponentManagerQualifier
    override lateinit var manager: ComponentManager<CGraphComponentEntryPoint, CGraphComponent>
    companion object {
        const val NOTIFICATION_ID = 10
    }
}
