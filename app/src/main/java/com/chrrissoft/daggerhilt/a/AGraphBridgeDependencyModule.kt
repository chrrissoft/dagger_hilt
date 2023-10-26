package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.Dependencies.SharedDependency
import com.chrrissoft.daggerhilt.common.FlowContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.*
import javax.inject.Qualifier

/**
 * This a bridge between a [AGraphComponentEntryPoint] and view model clients of the
 * [SharedDependency], letting the view model clients get the [SharedDependency] like any other
 * normal dependency since [AGraphComponentEntryPoint.getDependency] is called here.
 * The [AGraphComponentEntryPoint] is injected from [AGraphComponentEntryPointModule]
 * @see [AGraphComponentEntryPointModule]
 * */
@Module
@InstallIn(SingletonComponent::class)
object AGraphBridgeDependencyModule {
    @Provides
    @AGraphBridgeQualifier
    fun provide(
        @AGraphComponentEntryPointModule.AGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<AGraphComponentEntryPoint>,
    ): FlowContainer<SharedDependency> {
        return FlowContainer(entryPoint.value.map { it.getDependency() })
    }

    @Provides
    @AGraphBridgeStaticQualifier
    fun provideStatic(
        @AGraphComponentEntryPointModule.AGraphComponentEntryPointStaticQualifier
        entryPoint: AGraphComponentEntryPoint,
    ): SharedDependency {
        return entryPoint.getDependency()
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphBridgeQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphBridgeStaticQualifier
}

