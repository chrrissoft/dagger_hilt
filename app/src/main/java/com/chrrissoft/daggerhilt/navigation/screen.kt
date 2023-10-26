package com.chrrissoft.daggerhilt.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrrissoft.daggerhilt.navigation.Destinations.Screen.Companion.screens
import com.chrrissoft.daggerhilt.Utils.ui
import com.chrrissoft.daggerhilt.common.ScreenEvent
import com.chrrissoft.daggerhilt.common.ScreenEvent.OnChangeText
import com.chrrissoft.daggerhilt.common.ScreenEvent.*
import com.chrrissoft.daggerhilt.common.ScreenState
import com.chrrissoft.daggerhilt.ui.components.*
import com.chrrissoft.daggerhilt.ui.theme.centerAlignedTopAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    screen: String,
    state: ScreenState,
    onEvent: (ScreenEvent) -> Unit,
    graph: Destinations.Graph,
    onNavigation: (String, String, Boolean) -> Unit,
    onUpdateEntry: (String) -> Unit,
    onWork: () -> Unit,
    onService: () -> Unit,
    onBroadcast: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "${screen.takeLastWhile { "$it" != "$" }.ui} - ${graph.label}") },
                colors = centerAlignedTopAppBarColors
            )
        },
        containerColor = colorScheme.onPrimary,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                DependencyStateUpdater(state = state.dependencyState, state.reference) {
                    onEvent(OnChangeText(it))
                }
                Spacer(modifier = Modifier.height(10.dp))

                val (entry, changeEntry) = remember {
                    mutableStateOf("")
                }

                ScreenNavigator(
                    screen = screen,
                    onNavigation = { onNavigation(it, entry, false) },
                    screens = screens.map { it.buildRoute(graph) },
                )
                Spacer(modifier = Modifier.height(20.dp))

                EntryTextField(entry = entry, onChangeEntry = changeEntry)
                Spacer(modifier = Modifier.height(10.dp))
                GraphNavigator(
                    title = "Nested graphs",
                    graph = graph,
                    graphs = graph.nestedGraphs,
                    onNavigation = { onNavigation(it, entry, true) },
                )
                Spacer(modifier = Modifier.height(10.dp))
                GraphNavigator(
                    title = "Siblings Graphs",
                    graph = graph,
                    graphs = graph.siblingsAndSelfGraphs,
                    onNavigation = { onNavigation(it, entry, false) },
                )

                Spacer(modifier = Modifier.height(20.dp))
                BackComponents(
                    work = state.backComponents.worker,
                    service = state.backComponents.service,
                    receiver = state.backComponents.receiver,
                    onWork = onWork,
                    onService = onService,
                    onBroadcast = onBroadcast,
                )
                Spacer(modifier = Modifier.height(10.dp))
                ClientAction(
                    title = "Screen actions",
                    onAction = { action, entity ->
                        entity?.let { onUpdateEntry(it) }
                        onEvent(OnScreenClientAction(action))
                    }
                )

                if (state.backComponents.worker) {
                    Spacer(modifier = Modifier.height(10.dp))
                    ClientAction(
                        title = "Worker actions",
                        onAction = { action, entity ->
                            entity?.let { onUpdateEntry(it) }
                            onEvent(OnWorkerClientAction(action))

                        }
                    )
                }

                if (state.backComponents.service) {
                    Spacer(modifier = Modifier.height(10.dp))
                    ClientAction(
                        title = "Service actions",
                        onAction = { action, entity ->
                            entity?.let { onUpdateEntry(it) }
                            onEvent(OnServiceClientAction(action))
                        }
                    )
                }
            }
        },
    )
}
