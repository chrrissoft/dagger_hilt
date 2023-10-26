package com.chrrissoft.daggerhilt.a

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Qualifier

@Module
@InstallIn(AGraphComponent::class)
object AGraphActiveBackComponentsModule {
    @Provides
    @AGraphActiveBackComponentsService
    @AGraphComponentScope
    fun service(): MutableStateFlow<Boolean> {
        return MutableStateFlow((false))
    }


    @Provides
    @AGraphActiveBackComponentsReceiver
    @AGraphComponentScope
    fun receiver(): MutableStateFlow<Boolean> {
        return MutableStateFlow((false))
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphActiveBackComponentsService

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphActiveBackComponentsReceiver
}
