package com.chrrissoft.daggerhilt.c

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkerParameters
import com.chrrissoft.daggerhilt.common.*
import kotlinx.coroutines.flow.MutableStateFlow

class CGraphWorker(
    params: WorkerParameters,
    appContext: Context,
    override val notificationId: Int = NOTIFICATION_ID,
    override val notificationManager: NotificationManagerCompat,
    override val dependencyFlow: FlowContainer<Dependencies.SharedDependency>,
    override val manager: ComponentManager<CGraphComponentEntryPoint, CGraphComponent>,
    override val actionFlowFlow: FlowContainer<MutableStateFlow<ClientAction?>>,
) : BaseWorker(appContext = appContext, params = params) {
    companion object {
        const val NOTIFICATION_ID = 0
    }
}
