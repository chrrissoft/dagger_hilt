package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class BGraphComponentManagerModule {
    @Binds
    @BGraphComponentManagerQualifier
    abstract fun provide(
        impl: BGraphComponentManager
    ): ComponentManager<BGraphComponentEntryPoint, BGraphComponent>

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BGraphComponentManagerQualifier
}
