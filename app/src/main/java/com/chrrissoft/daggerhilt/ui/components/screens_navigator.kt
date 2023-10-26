package com.chrrissoft.daggerhilt.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.daggerhilt.Utils.ui

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenNavigator(
    screen: String,
    screens: List<String>,
    onNavigation: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    MyCard(
        title = "Screen navigator",
        content = {
            Row(Modifier.fillMaxWidth()) {
                screens.forEachIndexed { i, e ->
                    MyInputChip(
                        enabled = e!=screen,
                        selected = screen==e,
                        label = e.takeLast(8).ui,
                        onClick = { onNavigation(e) },
                        modifier = Modifier.weight(1f),
                    )
                    if (i!=screens.lastIndex) Spacer(modifier = Modifier.weight(.05f))
                }
            }
        },
        modifier = modifier,
    )
}
