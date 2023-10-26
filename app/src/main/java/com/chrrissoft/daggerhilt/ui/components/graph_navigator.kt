package com.chrrissoft.daggerhilt.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.daggerhilt.navigation.Destinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraphNavigator(
    title: String,
    graph: Destinations.Graph?,
    graphs: List<Destinations.Graph>?,
    onNavigation: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MyCard(
        title = title,
        content = {
            if (graphs==null) {
                Text(text = "No $title", style = typography.titleMedium)
                return@MyCard
            } else {
                Row(Modifier.fillMaxWidth()) {
                    graphs.forEachIndexed { i, e ->
                        MyInputChip(
                            label = e.label,
                            enabled = e!=graph,
                            selected = graph==e,
                            onClick = { onNavigation(e.route) },
                            modifier = Modifier.weight(1f),
                        )
                        if (i!=graphs.lastIndex) Spacer(modifier = Modifier.weight(.05f))
                    }
                }
            }
        },
        modifier = modifier
    )
}
