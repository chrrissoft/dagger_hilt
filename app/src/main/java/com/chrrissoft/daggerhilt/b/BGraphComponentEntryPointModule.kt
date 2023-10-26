package com.chrrissoft.daggerhilt.b

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
object BGraphComponentEntryPointModule {
    @Provides
    @BGraphComponentEntryPointQualifier
    fun  provide(
        @BGraphComponentManagerModule.BGraphComponentManagerQualifier
        manager: ComponentManager<BGraphComponentEntryPoint, BGraphComponent>,
    ): FlowContainer<BGraphComponentEntryPoint> {
        return FlowContainer(manager.entryPoint().mapNotNull { it })
    }

    @Provides
    @BGraphComponentEntryPointStaticQualifier
    fun  provideStatic(
        @BGraphComponentManagerModule.BGraphComponentManagerQualifier
        manager: ComponentManager<BGraphComponentEntryPoint, BGraphComponent>,
    ): BGraphComponentEntryPoint {
        return manager.staticEntryPoint
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphComponentEntryPointQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphComponentEntryPointStaticQualifier
}
