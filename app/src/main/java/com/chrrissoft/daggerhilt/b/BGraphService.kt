package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@AndroidEntryPoint
class BGraphService : BaseService() {
    override val notificationId = NOTIFICATION_ID
    @Inject
    @BGraphBridgeDependencyModule.BGraphBridgeQualifier
    override lateinit var dependencyFlow: FlowContainer<Dependencies.SharedDependency>
    @Inject
    @BGraphBridgeClientActionsModule.BGraphBridgeClientActionsServiceAction
    override lateinit var actionFlowFlow: FlowContainer<MutableStateFlow<ClientAction?>>
    @Inject
    @BGraphBridgeActiveBackComponentsModule.BGraphBridgeActiveBackComponentsServiceActive
    override lateinit var runningStateFlow: FlowContainer<MutableStateFlow<Boolean>>

    @Inject
    @BGraphComponentManagerModule.BGraphComponentManagerQualifier
    override lateinit var manager: ComponentManager<BGraphComponentEntryPoint, BGraphComponent>
    companion object {
        const val NOTIFICATION_ID = 10
    }
}
