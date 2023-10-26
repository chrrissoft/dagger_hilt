package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.ClientAction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Qualifier

@Module
@InstallIn(AGraphComponent::class)
object AGraphClientActionModule {
    @Provides
    @AGraphClientActionWorkerAction
    @AGraphComponentScope
    fun worker(): MutableStateFlow<ClientAction?> {
        return MutableStateFlow(null)
    }

    @Provides
    @AGraphClientActionServiceAction
    @AGraphComponentScope
    fun service(): MutableStateFlow<ClientAction?> {
        return MutableStateFlow(null)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphClientActionWorkerAction

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphClientActionServiceAction
}
