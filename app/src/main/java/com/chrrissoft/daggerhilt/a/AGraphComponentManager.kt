package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.hilt.EntryPoints.get
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AGraphComponentManager @Inject constructor(
    private val provider: Provider<AGraphComponent.Builder>,
) : ComponentManager<AGraphComponentEntryPoint, AGraphComponent>() {
    override var component: AGraphComponent? = null

    override fun entryPoint() = run {
        if (mutableEntryPoint.value==null) {
            mutableEntryPoint.update { get((this), AGraphComponentEntryPoint::class.java) }
            mutableEntryPoint
        } else this.mutableEntryPoint
    }

    override fun generatedComponent(): AGraphComponent {
        return component ?: provider.get().build().also { component = it }
    }

    override fun rebuild() {
        component = provider.get().build()
        mutableEntryPoint.update { get((this), AGraphComponentEntryPoint::class.java) }
        onRebuildListeners.forEach { it() }
    }

    override val staticEntryPoint: AGraphComponentEntryPoint
        get() = entryPoint().value!!
}
