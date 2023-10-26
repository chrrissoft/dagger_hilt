package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.common.ClientAction
import com.chrrissoft.daggerhilt.common.Dependencies
import com.chrrissoft.daggerhilt.common.MyEntryPoint
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow

@EntryPoint
@InstallIn(AGraphComponent::class)
interface AGraphComponentEntryPoint : MyEntryPoint {
    @AGraphComponentDependencyModule.AGraphEntryPointQualifier
    fun getDependency(): Dependencies.SharedDependency

    @AGraphClientActionModule.AGraphClientActionWorkerAction
    fun getWorkerAction(): MutableStateFlow<ClientAction?>

    @AGraphClientActionModule.AGraphClientActionServiceAction
    fun getServiceAction(): MutableStateFlow<ClientAction?>

    @AGraphActiveBackComponentsModule.AGraphActiveBackComponentsService
    fun getServiceActive(): MutableStateFlow<Boolean>

    @AGraphActiveBackComponentsModule.AGraphActiveBackComponentsReceiver
    fun getReceiverActive(): MutableStateFlow<Boolean>
}
