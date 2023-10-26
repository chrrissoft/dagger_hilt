package com.chrrissoft.daggerhilt.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.chrrissoft.daggerhilt.Utils.ui
import com.chrrissoft.daggerhilt.a.AGraphComponentDestroyerService
import com.chrrissoft.daggerhilt.a.AGraphViewModel
import com.chrrissoft.daggerhilt.b.BGraphComponentDestroyerService
import com.chrrissoft.daggerhilt.b.BGraphViewModel
import com.chrrissoft.daggerhilt.c.CGraphComponentDestroyerService
import com.chrrissoft.daggerhilt.c.CGraphViewModel

object Destinations {
    const val startRoute = "start_route"

    sealed interface Graph {
        val route: String get() = this.javaClass.name
        val nestedGraphs: List<Graph>? get() = null
        val siblingsAndSelfGraphs: List<Graph>? get() = null

        val label
            get() = run {
                route.takeLastWhile { "$it" != "$" }.replace("Graph", "Graph ")
            }

        val destroyerService get() = run {
            when (this) {
                GraphA -> AGraphComponentDestroyerService::class.java
                GraphA.GraphAA -> AGraphComponentDestroyerService::class.java
                GraphA.GraphAA.GraphAAA -> AGraphComponentDestroyerService::class.java
                GraphA.GraphAB -> AGraphComponentDestroyerService::class.java
                GraphA.GraphAB.GraphABA -> AGraphComponentDestroyerService::class.java
                GraphA.GraphAC -> AGraphComponentDestroyerService::class.java
                GraphA.GraphAC.GraphACA -> AGraphComponentDestroyerService::class.java
                GraphB -> BGraphComponentDestroyerService::class.java
                GraphB.GraphBA -> BGraphComponentDestroyerService::class.java
                GraphB.GraphBA.GraphBAA -> BGraphComponentDestroyerService::class.java
                GraphB.GraphBB -> BGraphComponentDestroyerService::class.java
                GraphB.GraphBB.GraphBBA -> BGraphComponentDestroyerService::class.java
                GraphB.GraphBC -> BGraphComponentDestroyerService::class.java
                GraphB.GraphBC.GraphBCA -> BGraphComponentDestroyerService::class.java
                GraphC -> CGraphComponentDestroyerService::class.java
                GraphC.GraphCA -> CGraphComponentDestroyerService::class.java
                GraphC.GraphCA.GraphCAA -> CGraphComponentDestroyerService::class.java
                GraphC.GraphCB -> CGraphComponentDestroyerService::class.java
                GraphC.GraphCB.GraphCBA -> CGraphComponentDestroyerService::class.java
                GraphC.GraphCC -> CGraphComponentDestroyerService::class.java
                GraphC.GraphCC.GraphCCA -> CGraphComponentDestroyerService::class.java
            }
        }

        val viewModel @Composable get() = run {
            when (this) {
                GraphA -> hiltViewModel<AGraphViewModel>()
                GraphA.GraphAA -> hiltViewModel<AGraphViewModel>()
                GraphA.GraphAA.GraphAAA -> hiltViewModel<AGraphViewModel>()
                GraphA.GraphAB -> hiltViewModel<AGraphViewModel>()
                GraphA.GraphAB.GraphABA -> hiltViewModel<AGraphViewModel>()
                GraphA.GraphAC -> hiltViewModel<AGraphViewModel>()
                GraphA.GraphAC.GraphACA -> hiltViewModel<AGraphViewModel>()
                GraphB -> hiltViewModel<BGraphViewModel>()
                GraphB.GraphBA -> hiltViewModel<BGraphViewModel>()
                GraphB.GraphBA.GraphBAA -> hiltViewModel<BGraphViewModel>()
                GraphB.GraphBB -> hiltViewModel<BGraphViewModel>()
                GraphB.GraphBB.GraphBBA -> hiltViewModel<BGraphViewModel>()
                GraphB.GraphBC -> hiltViewModel<BGraphViewModel>()
                GraphB.GraphBC.GraphBCA -> hiltViewModel<BGraphViewModel>()
                GraphC -> hiltViewModel<CGraphViewModel>()
                GraphC.GraphCA -> hiltViewModel<CGraphViewModel>()
                GraphC.GraphCA.GraphCAA -> hiltViewModel<CGraphViewModel>()
                GraphC.GraphCB -> hiltViewModel<CGraphViewModel>()
                GraphC.GraphCB.GraphCBA -> hiltViewModel<CGraphViewModel>()
                GraphC.GraphCC -> hiltViewModel<CGraphViewModel>()
                GraphC.GraphCC.GraphCCA -> hiltViewModel<CGraphViewModel>()
            }
        }
    }

    enum class Screen(val route: String) {
        SCREEN_A(route = "SCREEN_A"),
        SCREEN_B(route = "SCREEN_B"),
        SCREEN_C(route = "SCREEN_C"),
        ;

        fun buildRoute(graph: Graph): String {
            return graph.route.plus("_$route")
        }

        val label get() = route.ui

        companion object {
            val screens = listOf(SCREEN_A, SCREEN_B, SCREEN_C)
        }
    }

    object GraphA : Graph {
        val graphsA = listOf(GraphAA, GraphAB, GraphAC)
        override val nestedGraphs get() = graphsA
        override val siblingsAndSelfGraphs get() = graphs

        object GraphAA : Graph {
            override val nestedGraphs get() = listOf(GraphAAA)
            override val siblingsAndSelfGraphs get() = graphsA

            object GraphAAA : Graph
        }

        object GraphAB : Graph {
            override val nestedGraphs get() = listOf(GraphABA)
            override val siblingsAndSelfGraphs get() = graphsA

            object GraphABA : Graph
        }

        object GraphAC : Graph {
            override val nestedGraphs get() = listOf(GraphACA)
            override val siblingsAndSelfGraphs get() = graphsA

            object GraphACA : Graph
        }
    }

    object GraphB : Graph {
        val graphsB = listOf(GraphBA, GraphBB, GraphBC)
        override val nestedGraphs get() = graphsB
        override val siblingsAndSelfGraphs get() = graphs

        object GraphBA : Graph {
            override val nestedGraphs get() = listOf(GraphBAA)
            override val siblingsAndSelfGraphs get() = graphsB

            object GraphBAA : Graph
        }

        object GraphBB : Graph {
            override val nestedGraphs get() = listOf(GraphBBA)
            override val siblingsAndSelfGraphs get() = graphsB

            object GraphBBA : Graph
        }

        object GraphBC : Graph {
            override val nestedGraphs get() = listOf(GraphBCA)
            override val siblingsAndSelfGraphs get() = graphsB

            object GraphBCA : Graph
        }
    }

    object GraphC : Graph {
        val graphsC = listOf(GraphCA, GraphCB, GraphCC)
        override val nestedGraphs get() = graphsC
        override val siblingsAndSelfGraphs get() = graphs

        object GraphCA : Graph {
            override val nestedGraphs get() = listOf(GraphCAA)
            override val siblingsAndSelfGraphs get() = graphsC

            object GraphCAA : Graph
        }

        object GraphCB : Graph {
            override val nestedGraphs get() = listOf(GraphCBA)
            override val siblingsAndSelfGraphs get() = graphsC

            object GraphCBA : Graph
        }

        object GraphCC : Graph {
            override val nestedGraphs get() = listOf(GraphCCA)
            override val siblingsAndSelfGraphs get() = graphsC

            object GraphCCA : Graph
        }
    }

    val graphs = buildList {
        add(GraphA)
        add(GraphB)
        add(GraphC)
    }
}
