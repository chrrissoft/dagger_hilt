package com.chrrissoft.daggerhilt.a

import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@AGraphComponentScope
@DefineComponent(parent = SingletonComponent::class)
interface AGraphComponent {
    @DefineComponent.Builder
    interface Builder {
        fun build(): AGraphComponent
    }
}
