package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.Dependencies.SharedDependency
import com.chrrissoft.daggerhilt.common.FlowContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.*
import javax.inject.Qualifier

/**
 * This a bridge between a [BGraphComponentEntryPoint] and view model clients of the
 * [SharedDependency], letting the view model clients get the [SharedDependency] like any other
 * normal dependency since [BGraphComponentEntryPoint.getDependency] is called here.
 * The [BGraphComponentEntryPoint] is injected from [BGraphComponentEntryPointModule]
 * @see [BGraphComponentEntryPointModule]
 * */
@Module
@InstallIn(SingletonComponent::class)
object BGraphBridgeDependencyModule {
    @Provides
    @BGraphBridgeQualifier
    fun provide(
        @BGraphComponentEntryPointModule.BGraphComponentEntryPointQualifier
        entryPoint: FlowContainer<BGraphComponentEntryPoint>,
    ): FlowContainer<SharedDependency> {
        return FlowContainer(entryPoint.value.map { it.getDependency() })
    }

    @Provides
    @BGraphBridgeStaticQualifier
    fun provideStatic(
        @BGraphComponentEntryPointModule.BGraphComponentEntryPointStaticQualifier
        entryPoint: BGraphComponentEntryPoint,
    ): SharedDependency {
        return entryPoint.getDependency()
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphBridgeQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphBridgeStaticQualifier
}

