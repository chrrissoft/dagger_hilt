package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.common.Dependencies.SharedDependency
import com.chrrissoft.daggerhilt.common.FlowContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.*
import javax.inject.Qualifier

/**
 * This a bridge between a [CGraphComponentEntryPoint] and view model clients of the
 * [SharedDependency], letting the view model clients get the [SharedDependency] like any other
 * normal dependency since [CGraphComponentEntryPoint.getDependency] is called here.
 * The [CGraphComponentEntryPoint] is injected from [CGraphComponentEntryPointModule]
 * @see [CGraphComponentEntryPointModule]
 * */
@Module
@InstallIn(SingletonComponent::class)
object CGraphBridgeDependencyModule {
    @Provides
    @CGraphBridgeQualifier
    fun provide(
        @CGraphComponentEntryPointModule.CGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<CGraphComponentEntryPoint>,
    ): FlowContainer<SharedDependency> {
        return FlowContainer(entryPoint.value.map { it.getDependency() })
    }

    @Provides
    @CGraphBridgeStaticQualifier
    fun provideStatic(
        @CGraphComponentEntryPointModule.CGraphComponentEntryPointStaticQualifier
        entryPoint: CGraphComponentEntryPoint,
    ): SharedDependency {
        return entryPoint.getDependency()
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphBridgeQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphBridgeStaticQualifier
}

