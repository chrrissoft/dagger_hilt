package com.chrrissoft.daggerhilt.common

import dagger.hilt.internal.GeneratedComponentManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.ConcurrentLinkedDeque

abstract class ComponentManager<E : MyEntryPoint, C> : GeneratedComponentManager<C> {
    protected abstract var component: C?
    protected val mutableEntryPoint = MutableStateFlow<E?>((null))
    abstract fun entryPoint(): StateFlow<E?>
    private val onDestroyListeners = ConcurrentLinkedDeque<OnDestroyListener>()
    protected val onRebuildListeners = ConcurrentLinkedDeque<OnRebuildListener>()

    abstract val staticEntryPoint: E

    abstract fun rebuild()

    fun destroy() {
        component = null
        mutableEntryPoint.update { null }
        onDestroyListeners.forEach { it() }
    }

    fun addOnDestroyListener(listener: OnDestroyListener) {
        onDestroyListeners.add(listener)
    }

    fun removeOnDestroyListener(listener: OnDestroyListener) {
        onDestroyListeners.remove(listener)
    }

    fun addOnRebuildListener(listener: OnRebuildListener) {
        onRebuildListeners.add(listener)
    }

    fun removeOnRebuildListener(listener: OnRebuildListener) {
        onRebuildListeners.remove(listener)
    }


    fun interface OnDestroyListener {
        operator fun invoke()
    }

    fun interface OnRebuildListener {
        operator fun invoke()
    }
}
