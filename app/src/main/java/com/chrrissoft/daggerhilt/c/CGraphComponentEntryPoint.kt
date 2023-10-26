package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.common.ClientAction
import com.chrrissoft.daggerhilt.common.Dependencies
import com.chrrissoft.daggerhilt.common.MyEntryPoint
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow

@EntryPoint
@InstallIn(CGraphComponent::class)
interface CGraphComponentEntryPoint : MyEntryPoint {
    @CGraphComponentDependencyModule.CGraphEntryPointQualifier
    fun getDependency(): Dependencies.SharedDependency

    @CGraphClientActionModule.CGraphClientActionWorkerAction
    fun getWorkerAction(): MutableStateFlow<ClientAction?>

    @CGraphClientActionModule.CGraphClientActionServiceAction
    fun getServiceAction(): MutableStateFlow<ClientAction?>

    @CGraphActiveBackComponentsModule.CGraphActiveBackComponentsService
    fun getServiceActive(): MutableStateFlow<Boolean>

    @CGraphActiveBackComponentsModule.CGraphActiveBackComponentsReceiver
    fun getReceiverActive(): MutableStateFlow<Boolean>
}
