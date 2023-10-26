package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.hilt.EntryPoints.get
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class BGraphComponentManager @Inject constructor(
    private val provider: Provider<BGraphComponent.Builder>,
) : ComponentManager<BGraphComponentEntryPoint, BGraphComponent>() {
    override var component: BGraphComponent? = null

    override fun entryPoint() = run {
        if (mutableEntryPoint.value==null) {
            mutableEntryPoint.update { get((this), BGraphComponentEntryPoint::class.java) }
            mutableEntryPoint
        } else this.mutableEntryPoint
    }

    override fun generatedComponent(): BGraphComponent {
        return component ?: provider.get().build().also { component = it }
    }

    override fun rebuild() {
        component = provider.get().build()
        mutableEntryPoint.update { get((this), BGraphComponentEntryPoint::class.java) }
        onRebuildListeners.forEach { it() }
    }

    override val staticEntryPoint: BGraphComponentEntryPoint
        get() = entryPoint().value!!
}
