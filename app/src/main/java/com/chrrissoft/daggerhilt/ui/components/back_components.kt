package com.chrrissoft.daggerhilt.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.daggerhilt.ui.theme.cardColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackComponents(
    work: Boolean,
    service: Boolean,
    receiver: Boolean,
    onWork: () -> Unit,
    onService: () -> Unit,
    onBroadcast: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier, colors = cardColors) {
        Column(Modifier.padding(10.dp)) {
            Text(text = "Actions", style = typography.titleMedium)
            Row(Modifier.fillMaxWidth()) {
                MyInputChip(
                    selected = work,
                    label = "Worker",
                    onClick = { onWork() },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.weight(.05f))
                MyInputChip(
                    selected = service,
                    label = "Service",
                    onClick = { onService() },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.weight(.05f))
                MyInputChip(
                    selected = receiver,
                    label = "Broadcast",
                    onClick = { onBroadcast() },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}
