package com.chrrissoft.daggerhilt.workmanager

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.chrrissoft.daggerhilt.a.*
import com.chrrissoft.daggerhilt.b.*
import com.chrrissoft.daggerhilt.c.*
import com.chrrissoft.daggerhilt.common.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DaggerHiltAppWorkerFactory @Inject constructor(
    private val notificationManager: NotificationManagerCompat,

    @AGraphBridgeDependencyModule.AGraphBridgeQualifier
    private val aGraphDependencyFlow: FlowContainer<Dependencies.SharedDependency>,
    @AGraphComponentManagerModule.AGraphComponentManagerQualifier
    private val aGraphManager: ComponentManager<AGraphComponentEntryPoint, AGraphComponent>,
    @AGraphBridgeClientActionsModule.AGraphBridgeClientActionsWorkerAction
    private val aGraphActionFlow: FlowContainer<MutableStateFlow<ClientAction?>>,

    @BGraphBridgeDependencyModule.BGraphBridgeQualifier
    private val bGraphDependencyFlow: FlowContainer<Dependencies.SharedDependency>,
    @BGraphComponentManagerModule.BGraphComponentManagerQualifier
    private val bGraphManager: ComponentManager<BGraphComponentEntryPoint, BGraphComponent>,
    @BGraphBridgeClientActionsModule.BGraphBridgeClientActionsWorkerAction
    private val bGraphActionFlow: FlowContainer<MutableStateFlow<ClientAction?>>,

    @CGraphBridgeDependencyModule.CGraphBridgeQualifier
    private val cGraphDependencyFlow: FlowContainer<Dependencies.SharedDependency>,
    @CGraphComponentManagerModule.CGraphComponentManagerQualifier
    private val cGraphManager: ComponentManager<CGraphComponentEntryPoint, CGraphComponent>,
    @CGraphBridgeClientActionsModule.CGraphBridgeClientActionsWorkerAction
    private val cGraphActionFlow: FlowContainer<MutableStateFlow<ClientAction?>>,
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return when (workerClassName) {
            AGraphWorker::class.java.name -> {
                AGraphWorker(
                    appContext = appContext,
                    params = workerParameters,
                    manager = aGraphManager,
                    actionFlowFlow = aGraphActionFlow,
                    dependencyFlow = aGraphDependencyFlow,
                    notificationManager = notificationManager,
                )
            }

            BGraphWorker::class.java.name -> {
                BGraphWorker(
                    appContext = appContext,
                    params = workerParameters,
                    manager = bGraphManager,
                    actionFlowFlow = bGraphActionFlow,
                    dependencyFlow = bGraphDependencyFlow,
                    notificationManager = notificationManager,
                )
            }

            CGraphWorker::class.java.name -> {
                CGraphWorker(
                    appContext = appContext,
                    params = workerParameters,
                    manager = cGraphManager,
                    actionFlowFlow = cGraphActionFlow,
                    dependencyFlow = cGraphDependencyFlow,
                    notificationManager = notificationManager,
                )
            }
            else -> null
        }
    }
}
