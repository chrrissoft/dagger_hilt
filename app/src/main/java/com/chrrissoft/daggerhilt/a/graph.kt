package com.chrrissoft.daggerhilt.a

import androidx.navigation.NavGraphBuilder
import com.chrrissoft.daggerhilt.navigation.Destinations.GraphA
import com.chrrissoft.daggerhilt.navigation.Util.navigation

fun NavGraphBuilder.graphA(onNavigation: (String) -> Unit) {
    navigation(
        graph = GraphA,
        onNavigation = onNavigation,
        builder = {
            navigation(
                graph = GraphA.GraphAA,
                onNavigation = onNavigation,
                builder = { navigation(GraphA.GraphAA.GraphAAA, onNavigation) },
            )

            navigation(
                graph = GraphA.GraphAB,
                onNavigation = onNavigation,
                builder = { navigation(GraphA.GraphAB.GraphABA, onNavigation) },
            )

            navigation(
                graph = GraphA.GraphAC,
                onNavigation = onNavigation,
                builder = { navigation(GraphA.GraphAC.GraphACA, onNavigation) },
            )
        },
    )
}
