package com.chrrissoft.daggerhilt.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FlowContainer<T>(val value: Flow<T>) {
    fun collect(scope: CoroutineScope, block: suspend CoroutineScope.(T) -> Unit) {
        scope.launch(Default) { value.collect { block(it) } }
    }

    suspend fun collect(block: suspend (T) -> Unit) {
        value.collect { block(it) }
    }
}
