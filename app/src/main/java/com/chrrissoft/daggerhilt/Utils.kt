package com.chrrissoft.daggerhilt

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import com.chrrissoft.daggerhilt.Constants.GENERAL_NOTIFICATIONS_CHANEL_ID
import com.chrrissoft.daggerhilt.common.Dependencies
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.Observer
import java.util.*

object Utils {
    @SuppressLint("ComposableNaming")
    @Composable
    fun setBarsColors(
        status: Color = colorScheme.primaryContainer,
        bottom: Color = colorScheme.onPrimary,
    ) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setStatusBarColor(status, useDarkIcons)
            systemUiController.setNavigationBarColor(bottom)
            onDispose {}
        }
    }


    val String.ui
        get() = replace("_", " ").lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

    fun Any.createNotification(
        ctx: Context,
        dependency: Dependencies.SharedDependency,
        channel: String = GENERAL_NOTIFICATIONS_CHANEL_ID,
    ): Notification {
        return NotificationCompat.Builder(ctx, channel)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(dependency.state.value)
            .setSubText(this::class.java.simpleName)
            .setContentText(dependency.direction)
            .setOnlyAlertOnce(true)
            .setTimeoutAfter(500_000)
            .build()
    }

    fun Any.debug(message: Any?, tag: String? = null) {
        Log.d(tag ?: this.javaClass.simpleName, message.toString())
    }

    val Any.direction get() = "@" + toString().takeLastWhile { "$it"!="@" }

    suspend  fun <T> LiveData<T>.asFlow(scope: CoroutineScope): StateFlow<T> {
        return callbackFlow {
            val observer = Observer { it: T -> trySend(it) }
            observeForever(observer)
            awaitClose { this@asFlow.removeObserver(observer) }
        }.stateIn(scope)
    }
}
