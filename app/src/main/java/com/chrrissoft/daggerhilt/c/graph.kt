package com.chrrissoft.daggerhilt.c

import androidx.navigation.NavGraphBuilder
import com.chrrissoft.daggerhilt.navigation.Destinations.GraphC
import com.chrrissoft.daggerhilt.navigation.Util.navigation

fun NavGraphBuilder.graphC(onNavigation: (String) -> Unit) {
    navigation(
        graph = GraphC,
        onNavigation = onNavigation,
        builder = {
            navigation(
                graph = GraphC.GraphCA,
                onNavigation = onNavigation,
                builder = { navigation(GraphC.GraphCA.GraphCAA, onNavigation) },
            )

            navigation(
                graph = GraphC.GraphCB,
                onNavigation = onNavigation,
                builder = { navigation(GraphC.GraphCB.GraphCBA, onNavigation) },
            )

            navigation(
                graph = GraphC.GraphCC,
                onNavigation = onNavigation,
                builder = { navigation(GraphC.GraphCC.GraphCCA, onNavigation) },
            )
        },
    )
}
