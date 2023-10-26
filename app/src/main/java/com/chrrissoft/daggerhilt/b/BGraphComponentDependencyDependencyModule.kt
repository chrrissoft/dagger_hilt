package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.Dependencies
import com.chrrissoft.daggerhilt.common.SharedDependencyDependencyEntryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Qualifier

@Module
@InstallIn(BGraphComponent::class)
object BGraphComponentDependencyDependencyModule {
    @Provides
    @BGraphDependencyDependencyModule
    fun provide(
        @SharedDependencyDependencyEntryModule.EntryQualifier
        entry: String,
    ): Dependencies.SharedDependencyDependency {
        return Dependencies.SharedDependencyDependencyImpl(entry)
    }


    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphDependencyDependencyModule
}
