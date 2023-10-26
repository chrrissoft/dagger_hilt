package com.chrrissoft.daggerhilt.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.daggerhilt.ui.components.EntryTextField
import com.chrrissoft.daggerhilt.ui.theme.centerAlignedTopAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    graphs: List<Destinations.Graph>,
    onNav: (String, String) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Main Screen") },
                colors = centerAlignedTopAppBarColors
            )
        },
        containerColor = colorScheme.onPrimary,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(10.dp)
            ) {
                val (entry, changeEntry) = remember {
                    mutableStateOf("")
                }
                EntryTextField(entry = entry, onChangeEntry = changeEntry)
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(modifier = Modifier) {
                    items(graphs) { graph ->
                        Button(
                            onClick = { onNav(graph.route, entry) },
                            shape = shapes.medium,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Nav to ${graph.label}")
                        }
                    }
                }
            }
        },
    )
}
