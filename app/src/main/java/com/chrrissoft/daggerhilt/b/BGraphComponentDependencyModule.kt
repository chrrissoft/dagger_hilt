package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.Dependencies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Qualifier

@Module
@InstallIn(BGraphComponent::class)
object BGraphComponentDependencyModule {
    @Provides
    @BGraphComponentScope
    @BGraphEntryPointQualifier
    fun provide(
        @BGraphComponentDependencyDependencyModule.BGraphDependencyDependencyModule
        dependency: Dependencies.SharedDependencyDependency
    ): Dependencies.SharedDependency {
        return Dependencies.SharedDependencyImpl(dependency)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphEntryPointQualifier
}
