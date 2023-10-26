package com.chrrissoft.daggerhilt.c

import android.content.IntentFilter
import com.chrrissoft.daggerhilt.common.BaseReceiver
import com.chrrissoft.daggerhilt.common.Dependencies
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CGraphReceiver @Inject constructor() : BaseReceiver(
    notificationId = NOTIFICATION_ID,
    onChangeAction = onChangeStateAction,
) {
    @Inject
    @CGraphBridgeDependencyModule.CGraphBridgeStaticQualifier
    override lateinit var dependency: Dependencies.SharedDependency

    companion object {
        private const val NOTIFICATION_ID = 20
        val onChangeStateAction = "${this::class.java.name}.DEPENDENCY_CHANGE_ACTION"
        val filter = IntentFilter(onChangeStateAction)
    }
}
