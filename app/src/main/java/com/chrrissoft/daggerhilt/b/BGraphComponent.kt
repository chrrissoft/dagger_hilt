package com.chrrissoft.daggerhilt.b

import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@BGraphComponentScope
@DefineComponent(parent = SingletonComponent::class)
interface BGraphComponent {
    @DefineComponent.Builder
    interface Builder {
        fun build(): BGraphComponent
    }
}
