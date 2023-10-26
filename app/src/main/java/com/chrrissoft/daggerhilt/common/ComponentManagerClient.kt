package com.chrrissoft.daggerhilt.common


interface ComponentManagerClient {
    val manager: ComponentManager<*, *>

    fun destroy() {
        manager.destroy()
    }

    fun rebuild() {
        manager.rebuild()
    }
}
