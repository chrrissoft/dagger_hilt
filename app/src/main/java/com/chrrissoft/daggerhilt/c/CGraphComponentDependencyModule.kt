package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.common.Dependencies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Qualifier

@Module
@InstallIn(CGraphComponent::class)
object CGraphComponentDependencyModule {
    @Provides
    @CGraphComponentScope
    @CGraphEntryPointQualifier
    fun provide(
        @CGraphComponentDependencyDependencyModule.CGraphDependencyDependencyModule
        dependency: Dependencies.SharedDependencyDependency
    ): Dependencies.SharedDependency {
        return Dependencies.SharedDependencyImpl(dependency)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphEntryPointQualifier
}
