package com.chrrissoft.daggerhilt.ui.components


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrrissoft.daggerhilt.ui.theme.DaggerHiltTheme

@Composable
fun App(
    content: @Composable () -> Unit,
) {
    DaggerHiltTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = colorScheme.onPrimary) {
            content()
        }
    }
}
