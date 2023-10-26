package com.chrrissoft.daggerhilt.common


sealed interface ScreenEvent {
    data class OnChangeText(val data: String) : ScreenEvent

    data class OnScreenClientAction(val data: ClientAction) : ScreenEvent
    data class OnWorkerClientAction(val data: ClientAction) : ScreenEvent
    data class OnServiceClientAction(val data: ClientAction) : ScreenEvent
}
