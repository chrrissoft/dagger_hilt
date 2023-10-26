package com.chrrissoft.daggerhilt.c

import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@CGraphComponentScope
@DefineComponent(parent = SingletonComponent::class)
interface CGraphComponent {
    @DefineComponent.Builder
    interface Builder {
        fun build(): CGraphComponent
    }
}
