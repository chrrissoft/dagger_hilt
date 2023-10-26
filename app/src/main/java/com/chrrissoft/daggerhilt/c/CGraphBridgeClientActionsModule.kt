package com.chrrissoft.daggerhilt.c

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
object CGraphBridgeClientActionsModule {
    @Provides
    @CGraphBridgeClientActionsWorkerAction
    fun worker(
        @CGraphComponentEntryPointModule.CGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<CGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<ClientAction?>> {
        return FlowContainer(entryPoint.value.map { it.getWorkerAction() })
    }

    @Provides
    @CGraphBridgeClientActionsServiceAction
    fun service(
        @CGraphComponentEntryPointModule.CGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<CGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<ClientAction?>> {
        return FlowContainer(entryPoint.value.map { it.getServiceAction() })
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphBridgeClientActionsWorkerAction

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphBridgeClientActionsServiceAction
}
