package com.chrrissoft.daggerhilt.a

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
object AGraphBridgeClientActionsModule {
    @Provides
    @AGraphBridgeClientActionsWorkerAction
    fun worker(
        @AGraphComponentEntryPointModule.AGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<AGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<ClientAction?>> {
        return FlowContainer(entryPoint.value.map { it.getWorkerAction() })
    }

    @Provides
    @AGraphBridgeClientActionsServiceAction
    fun service(
        @AGraphComponentEntryPointModule.AGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<AGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<ClientAction?>> {
        return FlowContainer(entryPoint.value.map { it.getServiceAction() })
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphBridgeClientActionsWorkerAction

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphBridgeClientActionsServiceAction
}
