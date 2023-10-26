package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.Dependencies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Qualifier

@Module
@InstallIn(AGraphComponent::class)
object AGraphComponentDependencyModule {
    @Provides
    @AGraphComponentScope
    @AGraphEntryPointQualifier
    fun provide(
        @AGraphComponentDependencyDependencyModule.AGraphDependencyDependencyModule
        dependency: Dependencies.SharedDependencyDependency
    ): Dependencies.SharedDependency {
        return Dependencies.SharedDependencyImpl(dependency)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphEntryPointQualifier
}
