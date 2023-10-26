package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.common.ClientAction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Qualifier

@Module
@InstallIn(CGraphComponent::class)
object CGraphClientActionModule {
    @Provides
    @CGraphClientActionWorkerAction
    @CGraphComponentScope
    fun worker(): MutableStateFlow<ClientAction?> {
        return MutableStateFlow(null)
    }

    @Provides
    @CGraphClientActionServiceAction
    @CGraphComponentScope
    fun service(): MutableStateFlow<ClientAction?> {
        return MutableStateFlow(null)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphClientActionWorkerAction

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphClientActionServiceAction
}
