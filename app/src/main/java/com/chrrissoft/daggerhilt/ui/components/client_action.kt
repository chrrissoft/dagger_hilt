package com.chrrissoft.daggerhilt.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.chrrissoft.daggerhilt.common.ClientAction
import com.chrrissoft.daggerhilt.common.ClientAction.Rebuild

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientAction(
    title: String,
    actions: List<ClientAction> = ClientAction.actions,
    onAction: (ClientAction, String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showRebuildDialog by remember {
        mutableStateOf(false)
    }

    if (showRebuildDialog) {
        RebuildEntryDialog(
            onConfirm = {
                onAction(Rebuild, it)
                showRebuildDialog = false
            },
            onDismiss = { showRebuildDialog = false },
        )
    }


    MyCard(title = title, modifier = modifier) {
        Row(Modifier.fillMaxWidth()) {
            forEach(list = actions) {
                MyInputChip(
                    label = it::class.java.simpleName,
                    selected = true,
                    onClick = {
                        if (it == Rebuild) showRebuildDialog = true
                        else onAction(it, null)
                    },
                    modifier = Modifier.weight((1f))
                )
            }
        }
    }
}
