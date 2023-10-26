package com.chrrissoft.daggerhilt.c

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Qualifier

@Module
@InstallIn(CGraphComponent::class)
object CGraphActiveBackComponentsModule {
    @Provides
    @CGraphActiveBackComponentsService
    @CGraphComponentScope
    fun service(): MutableStateFlow<Boolean> {
        return MutableStateFlow((false))
    }


    @Provides
    @CGraphActiveBackComponentsReceiver
    @CGraphComponentScope
    fun receiver(): MutableStateFlow<Boolean> {
        return MutableStateFlow((false))
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphActiveBackComponentsService

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphActiveBackComponentsReceiver
}
