package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class AGraphComponentManagerModule {
    @Binds
    @AGraphComponentManagerQualifier
    abstract fun provide(
        impl: AGraphComponentManager
    ): ComponentManager<AGraphComponentEntryPoint, AGraphComponent>

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphComponentManagerQualifier
}
