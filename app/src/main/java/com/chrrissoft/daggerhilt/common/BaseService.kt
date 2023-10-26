package com.chrrissoft.daggerhilt.common

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.chrrissoft.daggerhilt.Constants
import com.chrrissoft.daggerhilt.R
import com.chrrissoft.daggerhilt.Utils.createNotification
import com.chrrissoft.daggerhilt.common.ClientAction.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseService : Service(), ComponentManagerClient {
    abstract val dependencyFlow: FlowContainer<Dependencies.SharedDependency>
    abstract val actionFlowFlow: FlowContainer<MutableStateFlow<ClientAction?>>
    abstract val runningStateFlow: FlowContainer<MutableStateFlow<Boolean>>

    abstract val notificationId: Int

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    private val scope = CoroutineScope(SupervisorJob())
    private lateinit var runningState: MutableStateFlow<Boolean>

    private val onRebuildListener = { this.launchNotification(("Rebuild")) }
    private val onDestroyListener = { this.launchNotification(("Destroyed")) }

    override fun onCreate() {
        manager.addOnDestroyListener(onDestroyListener)
        manager.addOnRebuildListener(onRebuildListener)
        actionFlowFlow.collect(scope) {
            launch {
                it.collect {
                    when (it) {
                        Destroy -> destroy()
                        Rebuild -> rebuild()
                        null -> {}
                    }
                }
            }
        }
        dependencyFlow.collect(scope) { loadDependency(it) }
        runningStateFlow.collect(scope) {
            runningState = it
            runningState.update { true }
        }
        super.onCreate()
    }

    override fun onDestroy() {
        runningState.update { false }
        scope.cancel()
        manager.removeOnDestroyListener(onDestroyListener)
        manager.removeOnRebuildListener(onRebuildListener)
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    private fun CoroutineScope.loadDependency(dependency: Dependencies.SharedDependency) {
        collectDependencyState(dependency) { launchNotification(dependency) }
    }

    private fun CoroutineScope.collectDependencyState(
        dependency: Dependencies.SharedDependency,
        block: suspend (String) -> Unit,
    ) = launch { dependency.state.collect { block(it) } }

    private fun launchNotification(dependency: Dependencies.SharedDependency) {
        val notification = createNotification(ctx = this, dependency = dependency)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) return
        notificationManager.notify((notificationId), notification)
    }

    private fun launchNotification(title: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) return
        NotificationCompat.Builder(this, Constants.GENERAL_NOTIFICATIONS_CHANEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOnlyAlertOnce(true)
            .setSubText(this::class.java.simpleName)
            .build()
            .let { notificationManager.notify(notificationId * 7, it) }
    }
}
