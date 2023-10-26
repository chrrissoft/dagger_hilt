package com.chrrissoft.daggerhilt.navigation

import android.content.Intent
import androidx.activity.addCallback
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.HomeWork
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.chrrissoft.daggerhilt.MainActivity
import com.chrrissoft.daggerhilt.R
import com.chrrissoft.daggerhilt.navigation.Destinations.Screen.Companion.screens
import com.chrrissoft.daggerhilt.navigation.Destinations.Screen.SCREEN_A

object Util {
    fun NavGraphBuilder.navigation(
        graph: Destinations.Graph,
        onNavigation: (String) -> Unit,
        builder: (NavGraphBuilder.() -> Unit)? = null,
    ) {
        navigation(SCREEN_A.buildRoute(graph), graph.route) {
            screens.forEach { screen ->
                composable(screen.buildRoute(graph)) { stackEntry ->
                    val viewModel = graph.viewModel
                    val state by viewModel.state.collectAsState()
                    val activity = LocalContext.current as MainActivity
                    var showDestructionDialog by remember {
                        mutableStateOf(false)
                    }
                    if (showDestructionDialog) {
                        AlertDialog(
                            confirmButton = {
                                Button(
                                    onClick = {
                                        val intent = Intent(activity, graph.destroyerService)
                                        activity.startService(intent)
                                        activity.navigationController.popBackStack()
                                        if (state.backComponents.worker) viewModel.onWork()
                                        if (state.backComponents.service) viewModel.onService()
                                        if (state.backComponents.receiver) viewModel.onReceiver()
                                        showDestructionDialog = false
                                    },
                                    content = { Text(text = "Okay") }
                                )
                            },
                            icon = { Icon(Rounded.Cancel, (null)) },
                            title = { Text(text = stringResource(R.string.back_graph)) },
                            text = {
                                Text(
                                    text = stringResource(R.string.back_graph_description),
                                    textAlign = Justify
                                )
                            },
                            onDismissRequest = { showDestructionDialog = false },
                            containerColor = colorScheme.onPrimary,
                            iconContentColor = colorScheme.primary,
                            titleContentColor = colorScheme.primary,
                            textContentColor = colorScheme.primary,
                        )
                    }
                    LaunchedEffect(Unit) {
                        if (activity.navigationController.isStartRoute) {
                            activity.navigationController.setOnBackPressedDispatcher(activity.onBackPressedDispatcher)
                            activity.onBackPressedDispatcher.addCallback(stackEntry) {
                                showDestructionDialog = true
                            }
                        }
                    }
                    run {
                        var showNestedTodoDialog by remember {
                            mutableStateOf(false)
                        }
                        if (showNestedTodoDialog) {
                            AlertDialog(
                                confirmButton = {
                                    Button(
                                        onClick = { showNestedTodoDialog = false },
                                        content = { Text(text = "Okay") }
                                    )
                                },
                                icon = { Icon(Rounded.HomeWork, (null)) },
                                title = { Text(text = stringResource(R.string.nested_component_not_yet)) },
                                text = {
                                    Text(
                                        text = stringResource(R.string.nested_component_not_yet_description),
                                        textAlign = Justify
                                    )
                                },
                                onDismissRequest = { showNestedTodoDialog = false },
                                containerColor = colorScheme.onPrimary,
                                iconContentColor = colorScheme.primary,
                                titleContentColor = colorScheme.primary,
                                textContentColor = colorScheme.primary,
                            )
                        }

                        Screen(
                            graph = graph,
                            state = state,
                            onNavigation = { route, entry, nested ->
                                if (nested) showNestedTodoDialog = true
                                else {
                                    viewModel.updateEntry(entry)
                                    onNavigation(route)
                                }
                            },
                            onEvent = { viewModel.handleEvent(it) },
                            screen = stackEntry.destination.route ?: "",
                            onUpdateEntry = { viewModel.updateEntry(it) },
                            onWork = { viewModel.onWork() },
                            onService = { viewModel.onService() },
                            onBroadcast = { viewModel.onReceiver() },
                        )
                    }
                }
            }
            builder?.let { it() }
        }
    }

    private val NavHostController.isStartRoute
        get() = run {
            val index = currentBackStack.value.size - 2
            currentBackStack.value[index].destination is NavGraph
        }
}
