package com.chrrissoft.daggerhilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chrrissoft.daggerhilt.Utils.setBarsColors
import com.chrrissoft.daggerhilt.navigation.Graph
import com.chrrissoft.daggerhilt.ui.components.App
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navigationController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                setBarsColors()
                navigationController = rememberNavController()
                Graph(navigationController)
            }
        }
    }
}
