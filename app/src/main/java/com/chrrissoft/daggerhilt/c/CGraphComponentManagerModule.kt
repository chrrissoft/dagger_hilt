package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class CGraphComponentManagerModule {
    @Binds
    @CGraphComponentManagerQualifier
    abstract fun provide(
        impl: CGraphComponentManager
    ): ComponentManager<CGraphComponentEntryPoint, CGraphComponent>

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CGraphComponentManagerQualifier
}
