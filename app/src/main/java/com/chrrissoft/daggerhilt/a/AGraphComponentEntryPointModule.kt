package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.ComponentManager
import com.chrrissoft.daggerhilt.common.FlowContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object AGraphComponentEntryPointModule {
    @Provides
    @AGraphComponentEntryPointQualifier
    fun  provide(
        @AGraphComponentManagerModule.AGraphComponentManagerQualifier
        manager: ComponentManager<AGraphComponentEntryPoint, AGraphComponent>,
    ): FlowContainer<AGraphComponentEntryPoint> {
        return FlowContainer(manager.entryPoint().mapNotNull { it })
    }

    @Provides
    @AGraphComponentEntryPointStaticQualifier
    fun  provideStatic(
        @AGraphComponentManagerModule.AGraphComponentManagerQualifier
        manager: ComponentManager<AGraphComponentEntryPoint, AGraphComponent>,
    ): AGraphComponentEntryPoint {
        return manager.staticEntryPoint
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphComponentEntryPointQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphComponentEntryPointStaticQualifier
}
