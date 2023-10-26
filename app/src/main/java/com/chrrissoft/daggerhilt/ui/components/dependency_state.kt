package com.chrrissoft.daggerhilt.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.daggerhilt.ui.theme.textFieldColors

@Composable
fun DependencyStateUpdater(
    state: String,
    reference: String,
    modifier: Modifier = Modifier,
    onChangeState: (String) -> Unit,
) {
    TextField(
        value = state,
        onValueChange = { onChangeState(it) },
        colors = textFieldColors(),
        shape = shapes.medium,
        label = { Text(text = "Dependency State Updater $reference") },
        modifier = modifier
            .fillMaxWidth()
    )
}
