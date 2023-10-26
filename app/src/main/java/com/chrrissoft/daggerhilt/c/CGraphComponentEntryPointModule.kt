package com.chrrissoft.daggerhilt.c

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
object CGraphComponentEntryPointModule {
    @Provides
    @CGraphComponentEntryPointQualifier
    fun  provide(
        @CGraphComponentManagerModule.CGraphComponentManagerQualifier
        manager: ComponentManager<CGraphComponentEntryPoint, CGraphComponent>,
    ): FlowContainer<CGraphComponentEntryPoint> {
        return FlowContainer(manager.entryPoint().mapNotNull { it })
    }

    @Provides
    @CGraphComponentEntryPointStaticQualifier
    fun  provideStatic(
        @CGraphComponentManagerModule.CGraphComponentManagerQualifier
        manager: ComponentManager<CGraphComponentEntryPoint, CGraphComponent>,
    ): CGraphComponentEntryPoint {
        return manager.staticEntryPoint
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphComponentEntryPointQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphComponentEntryPointStaticQualifier
}
