package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.common.ClientAction
import com.chrrissoft.daggerhilt.common.Dependencies
import com.chrrissoft.daggerhilt.common.MyEntryPoint
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.MutableStateFlow

@EntryPoint
@InstallIn(BGraphComponent::class)
interface BGraphComponentEntryPoint : MyEntryPoint {
    @BGraphComponentDependencyModule.BGraphEntryPointQualifier
    fun getDependency(): Dependencies.SharedDependency

    @BGraphClientActionModule.BGraphClientActionWorkerAction
    fun getWorkerAction(): MutableStateFlow<ClientAction?>

    @BGraphClientActionModule.BGraphClientActionServiceAction
    fun getServiceAction(): MutableStateFlow<ClientAction?>

    @BGraphActiveBackComponentsModule.BGraphActiveBackComponentsService
    fun getServiceActive(): MutableStateFlow<Boolean>

    @BGraphActiveBackComponentsModule.BGraphActiveBackComponentsReceiver
    fun getReceiverActive(): MutableStateFlow<Boolean>
}
