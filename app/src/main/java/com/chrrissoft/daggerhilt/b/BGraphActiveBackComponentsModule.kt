package com.chrrissoft.daggerhilt.b

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Qualifier

@Module
@InstallIn(BGraphComponent::class)
object BGraphActiveBackComponentsModule {
    @Provides
    @BGraphActiveBackComponentsService
    @BGraphComponentScope
    fun service(): MutableStateFlow<Boolean> {
        return MutableStateFlow((false))
    }


    @Provides
    @BGraphActiveBackComponentsReceiver
    @BGraphComponentScope
    fun receiver(): MutableStateFlow<Boolean> {
        return MutableStateFlow((false))
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphActiveBackComponentsService

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphActiveBackComponentsReceiver
}
