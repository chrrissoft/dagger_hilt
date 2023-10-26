package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.ClientAction
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Qualifier

@Module
@InstallIn(BGraphComponent::class)
object BGraphClientActionModule {
    @Provides
    @BGraphClientActionWorkerAction
    @BGraphComponentScope
    fun worker(): MutableStateFlow<ClientAction?> {
        return MutableStateFlow(null)
    }

    @Provides
    @BGraphClientActionServiceAction
    @BGraphComponentScope
    fun service(): MutableStateFlow<ClientAction?> {
        return MutableStateFlow(null)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphClientActionWorkerAction

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphClientActionServiceAction
}
