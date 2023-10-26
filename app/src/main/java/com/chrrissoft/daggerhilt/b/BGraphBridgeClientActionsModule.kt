package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.ClientAction
import com.chrrissoft.daggerhilt.common.FlowContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object BGraphBridgeClientActionsModule {
    @Provides
    @BGraphBridgeClientActionsWorkerAction
    fun worker(
        @BGraphComponentEntryPointModule.BGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<BGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<ClientAction?>> {
        return FlowContainer(entryPoint.value.map { it.getWorkerAction() })
    }

    @Provides
    @BGraphBridgeClientActionsServiceAction
    fun service(
        @BGraphComponentEntryPointModule.BGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<BGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<ClientAction?>> {
        return FlowContainer(entryPoint.value.map { it.getServiceAction() })
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphBridgeClientActionsWorkerAction

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphBridgeClientActionsServiceAction
}
