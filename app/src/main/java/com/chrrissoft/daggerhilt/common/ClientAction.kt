package com.chrrissoft.daggerhilt.common

sealed interface ClientAction {
    object Rebuild : ClientAction
    object Destroy : ClientAction

    companion object {
        val actions = listOf(Rebuild, Destroy)
    }
}
