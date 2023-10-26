package com.chrrissoft.daggerhilt.common


data class ScreenState(
    val reference: String = "",
    val dependencyState: String = "",
    val backComponents: BackgroundComponents = BackgroundComponents(),
) {
    data class BackgroundComponents(
        val worker: Boolean = false,
        val service: Boolean = false,
        val receiver: Boolean = false,
    )
}
