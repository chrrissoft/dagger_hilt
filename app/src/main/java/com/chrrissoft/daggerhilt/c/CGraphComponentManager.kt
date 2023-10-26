package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.hilt.EntryPoints.get
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class CGraphComponentManager @Inject constructor(
    private val provider: Provider<CGraphComponent.Builder>,
) : ComponentManager<CGraphComponentEntryPoint, CGraphComponent>() {
    override var component: CGraphComponent? = null

    override fun entryPoint() = run {
        if (mutableEntryPoint.value==null) {
            mutableEntryPoint.update { get((this), CGraphComponentEntryPoint::class.java) }
            mutableEntryPoint
        } else this.mutableEntryPoint
    }

    override fun generatedComponent(): CGraphComponent {
        return component ?: provider.get().build().also { component = it }
    }

    override fun rebuild() {
        component = provider.get().build()
        mutableEntryPoint.update { get((this), CGraphComponentEntryPoint::class.java) }
        onRebuildListeners.forEach { it() }
    }

    override val staticEntryPoint: CGraphComponentEntryPoint
        get() = entryPoint().value!!
}
