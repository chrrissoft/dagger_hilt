package com.chrrissoft.daggerhilt.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow

@Module
@InstallIn(SingletonComponent::class)
object RegisteredReceiversModule {
    @Provides
    fun provide() : MutableStateFlow<List<String>> {
        return MutableStateFlow(emptyList())
    }
}
