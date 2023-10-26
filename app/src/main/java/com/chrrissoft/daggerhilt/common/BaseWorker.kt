package com.chrrissoft.daggerhilt.common

import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.chrrissoft.daggerhilt.Constants
import com.chrrissoft.daggerhilt.R
import com.chrrissoft.daggerhilt.Utils.createNotification
import com.chrrissoft.daggerhilt.common.ClientAction.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseWorker(
    params: WorkerParameters,
    private val appContext: Context,
) : CoroutineWorker(appContext, params), ComponentManagerClient {
    protected abstract val dependencyFlow: FlowContainer<Dependencies.SharedDependency>
    protected abstract val actionFlowFlow: FlowContainer<MutableStateFlow<ClientAction?>>

    abstract val notificationId: Int
    abstract val notificationManager: NotificationManagerCompat

    override suspend fun doWork(): Result {
        loadBehavior()
        return Result.success()
    }

    private suspend fun loadBehavior() {
        val onDestructionListener = { launchNotification(title = "Destruction") }
        val onRebuildListener = { launchNotification(title = "Rebuild") }
        withContext(CoroutineExceptionHandler { _, e ->
            manager.removeOnDestroyListener(onDestructionListener)
            manager.removeOnRebuildListener(onRebuildListener)
            throw e
        }) {
            manager.addOnDestroyListener(onDestructionListener)
            manager.addOnRebuildListener(onRebuildListener)
            actionFlowFlow.collect(this) {
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
            collectDependency { loadDependency(it) }
        }
    }

    private suspend fun collectDependency(
        block: suspend (Dependencies.SharedDependency) -> Unit,
    ) = dependencyFlow.collect { block(it) }

    private fun CoroutineScope.loadDependency(
        dependency: Dependencies.SharedDependency,
    ) {
        collectDependencyState(dependency) {
            launchNotification(dependency)
        }
    }


    private fun CoroutineScope.collectDependencyState(
        dependency: Dependencies.SharedDependency,
        block: suspend (String) -> Unit,
    ) {
        launch { dependency.state.collect { block(it) } }
    }


    private fun launchNotification(dependency: Dependencies.SharedDependency) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) return
        val notification = createNotification(ctx = appContext, dependency = dependency)
        notificationManager.notify((notificationId), notification)
    }

    private fun launchNotification(title: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) return
        NotificationCompat.Builder(appContext, Constants.GENERAL_NOTIFICATIONS_CHANEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOnlyAlertOnce(true)
            .setSubText(this::class.java.simpleName)
            .build()
            .let { notificationManager.notify(notificationId + 1, it) }
    }
}
