package com.chrrissoft.daggerhilt.c

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
object CGraphBridgeActiveBackComponentsModule {
    @Provides
    @CGraphBridgeActiveBackComponentsServiceActive
    fun service(
        @CGraphComponentEntryPointModule.CGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<CGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<Boolean>> {
        return FlowContainer(entryPoint.value.map { it.getServiceActive() })
    }

    @Provides
    @CGraphBridgeActiveBackComponentsReceiverActive
    fun receiver(
        @CGraphComponentEntryPointModule.CGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<CGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<Boolean>> {
        return FlowContainer(entryPoint.value.map { it.getReceiverActive() })
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphBridgeActiveBackComponentsServiceActive

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphBridgeActiveBackComponentsReceiverActive
}
