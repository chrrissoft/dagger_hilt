package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.common.Dependencies
import com.chrrissoft.daggerhilt.common.SharedDependencyDependencyEntryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Qualifier

@Module
@InstallIn(CGraphComponent::class)
object CGraphComponentDependencyDependencyModule {
    @Provides
    @CGraphDependencyDependencyModule
    fun provide(
        @SharedDependencyDependencyEntryModule.EntryQualifier
        entry: String,
    ): Dependencies.SharedDependencyDependency {
        return Dependencies.SharedDependencyDependencyImpl(entry)
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphDependencyDependencyModule
}
