package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object AGraphComponentModule {
    @Provides
    @AGraphComponentQualifier
    fun provide(
        @AGraphComponentManagerModule.AGraphComponentManagerQualifier
        manager: ComponentManager<AGraphComponentEntryPoint, AGraphComponent>
    ) : AGraphComponent = manager.generatedComponent()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AGraphComponentQualifier
}
