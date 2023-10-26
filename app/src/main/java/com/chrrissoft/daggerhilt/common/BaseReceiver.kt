package com.chrrissoft.daggerhilt.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.daggerhilt.Utils.createNotification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseReceiver(
    private val notificationId: Int,
    private val onChangeAction: String,
) : BroadcastReceiver() {
    abstract val dependency: Dependencies.SharedDependency

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context==null || intent==null) return
        if (intent.action!=onChangeAction) return
        when (intent.action) {
            onChangeAction -> {
                val notification = createNotification(ctx = context, dependency = dependency)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) return
                notificationManager.notify(notificationId, notification)
            }
        }
    }
}
