package com.chrrissoft.daggerhilt.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun<T> RowScope.forEach(list: List<T>, space: Float = .05f, block: @Composable (T) -> Unit) {
    list.forEachIndexed { index, t ->
        block(t)
        if (index<list.lastIndex) Spacer(modifier = Modifier.weight(space))
    }
}
