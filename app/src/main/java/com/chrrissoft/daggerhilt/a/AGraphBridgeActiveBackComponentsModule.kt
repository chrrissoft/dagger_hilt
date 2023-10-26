package com.chrrissoft.daggerhilt.a

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
object AGraphBridgeActiveBackComponentsModule {
    @Provides
    @AGraphBridgeActiveBackComponentsServiceActive
    fun service(
        @AGraphComponentEntryPointModule.AGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<AGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<Boolean>> {
        return FlowContainer(entryPoint.value.map { it.getServiceActive() })
    }

    @Provides
    @AGraphBridgeActiveBackComponentsReceiverActive
    fun receiver(
        @AGraphComponentEntryPointModule.AGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<AGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<Boolean>> {
        return FlowContainer(entryPoint.value.map { it.getReceiverActive() })
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphBridgeActiveBackComponentsServiceActive

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphBridgeActiveBackComponentsReceiverActive
}
