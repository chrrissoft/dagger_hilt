package com.chrrissoft.daggerhilt.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Sentences
import com.chrrissoft.daggerhilt.ui.theme.textFieldColors

@Composable
fun EntryTextField(
    entry: String,
    onChangeEntry: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = entry,
        onValueChange = onChangeEntry,
        shape = shapes.medium,
        colors = textFieldColors(),
        label = { Text(text = "Entry") },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = remember { KeyboardOptions(Sentences, imeAction = Done) }
    )
}

@Composable
fun RebuildEntryDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (entry, changeEntry) = remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { onConfirm(entry) }) {
                Text(text = "Okay")
            }
        },
        text = {
            TextField(
                value = entry,
                onValueChange = changeEntry,
                shape = shapes.medium,
                colors = textFieldColors(),
                label = { Text(text = "Entry") },
                modifier = modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = remember { KeyboardOptions(Sentences, imeAction = Done) },
                keyboardActions = KeyboardActions { onConfirm(entry) }
            )
        },
        icon = { Icon(Rounded.Refresh, (null)) },
        title = { Text(text = "Rebuild component") },
        containerColor = colorScheme.onPrimary,
        iconContentColor = colorScheme.primary,
        titleContentColor = colorScheme.primary,
    )
}
