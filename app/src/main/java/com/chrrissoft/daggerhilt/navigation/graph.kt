package com.chrrissoft.daggerhilt.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chrrissoft.daggerhilt.MainViewModel
import com.chrrissoft.daggerhilt.a.graphA
import com.chrrissoft.daggerhilt.b.graphB
import com.chrrissoft.daggerhilt.c.graphC

@Composable
fun Graph(controller: NavHostController) {
    NavHost(controller, Destinations.startRoute) {
        composable(Destinations.startRoute) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(
                graphs = Destinations.graphs,
                onNav = { router, entry ->
                    viewModel.changeEntry(entry)
                    controller.navigate(router)
                }
            )
        }

        graphA { controller.navigate(it) }
        graphB { controller.navigate(it) }
        graphC { controller.navigate(it) }
    }
}
