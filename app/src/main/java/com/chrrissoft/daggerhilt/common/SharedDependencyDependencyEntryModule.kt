package com.chrrissoft.daggerhilt.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedDependencyDependencyEntryModule {
    @Provides
    @Singleton
    @MutableEntryQualifier
    fun provideMutableEntry(): MutableStateFlow<String> {
        return MutableStateFlow("")
    }

    @Provides
    @ImmutableEntryQualifier
    fun provideImmutableEntry(
        @MutableEntryQualifier
        mutableStateFlow: MutableStateFlow<String>
    ): StateFlow<String> {
        return mutableStateFlow.asStateFlow()
    }

    @Provides
    @EntryQualifier
    fun provideEntry(
        @ImmutableEntryQualifier state: StateFlow<String>
    ): String {
        return state.value
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MutableEntryQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ImmutableEntryQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class EntryQualifier
}
