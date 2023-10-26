package com.chrrissoft.daggerhilt.b

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
object BGraphBridgeActiveBackComponentsModule {
    @Provides
    @BGraphBridgeActiveBackComponentsServiceActive
    fun service(
        @BGraphComponentEntryPointModule.BGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<BGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<Boolean>> {
        return FlowContainer(entryPoint.value.map { it.getServiceActive() })
    }

    @Provides
    @BGraphBridgeActiveBackComponentsReceiverActive
    fun receiver(
        @BGraphComponentEntryPointModule.BGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<BGraphComponentEntryPoint>,
    ): FlowContainer<MutableStateFlow<Boolean>> {
        return FlowContainer(entryPoint.value.map { it.getReceiverActive() })
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphBridgeActiveBackComponentsServiceActive

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphBridgeActiveBackComponentsReceiverActive
}
