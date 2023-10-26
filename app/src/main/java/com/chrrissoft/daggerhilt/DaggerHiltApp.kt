package com.chrrissoft.daggerhilt

import android.app.Application
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Configuration
import com.chrrissoft.daggerhilt.Constants.GENERAL_NOTIFICATIONS_CHANEL_ID
import com.chrrissoft.daggerhilt.Constants.GENERAL_NOTIFICATIONS_CHANEL_NAME
import com.chrrissoft.daggerhilt.common.BaseReceiver
import com.chrrissoft.daggerhilt.workmanager.DaggerHiltAppWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltAndroidApp
class DaggerHiltApp : Application(), Configuration.Provider {
    val scope = CoroutineScope(SupervisorJob())

    @Inject
    lateinit var workerFactory: DaggerHiltAppWorkerFactory

    @Inject
    lateinit var notificationManagerCompat: NotificationManagerCompat

    val receivers = hashMapOf<String, BaseReceiver>()

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val channel = NotificationChannelCompat.Builder(
            GENERAL_NOTIFICATIONS_CHANEL_ID,
            NotificationManager.IMPORTANCE_LOW
        )
            .setName(GENERAL_NOTIFICATIONS_CHANEL_NAME)
            .build()
        notificationManagerCompat.createNotificationChannel(channel)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory).apply {
                Log.d("DeleteFamilies", "setWorkerFactory")
            }
            .build()
    }
}
