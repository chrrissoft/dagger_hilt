package com.chrrissoft.daggerhilt.b

import androidx.navigation.NavGraphBuilder
import com.chrrissoft.daggerhilt.navigation.Destinations.GraphB
import com.chrrissoft.daggerhilt.navigation.Util.navigation

fun NavGraphBuilder.graphB(onNavigation: (String) -> Unit) {
    navigation(
        graph = GraphB,
        onNavigation = onNavigation,
        builder = {
            navigation(
                graph = GraphB.GraphBA,
                onNavigation = onNavigation,
                builder = { navigation(GraphB.GraphBA.GraphBAA, onNavigation) },
            )

            navigation(
                graph = GraphB.GraphBB,
                onNavigation = onNavigation,
                builder = { navigation(GraphB.GraphBB.GraphBBA, onNavigation) },
            )

            navigation(
                graph = GraphB.GraphBC,
                onNavigation = onNavigation,
                builder = { navigation(GraphB.GraphBC.GraphBCA, onNavigation) },
            )
        },
    )
}
