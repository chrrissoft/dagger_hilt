package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@AndroidEntryPoint
class AGraphService : BaseService() {
    override val notificationId = NOTIFICATION_ID
    @Inject
    @AGraphBridgeDependencyModule.AGraphBridgeQualifier
    override lateinit var dependencyFlow: FlowContainer<Dependencies.SharedDependency>
    @Inject
    @AGraphBridgeClientActionsModule.AGraphBridgeClientActionsServiceAction
    override lateinit var actionFlowFlow: FlowContainer<MutableStateFlow<ClientAction?>>
    @Inject
    @AGraphBridgeActiveBackComponentsModule.AGraphBridgeActiveBackComponentsServiceActive
    override lateinit var runningStateFlow: FlowContainer<MutableStateFlow<Boolean>>

    @Inject
    @AGraphComponentManagerModule.AGraphComponentManagerQualifier
    override lateinit var manager: ComponentManager<AGraphComponentEntryPoint, AGraphComponent>
    companion object {
        const val NOTIFICATION_ID = 10
    }
}
