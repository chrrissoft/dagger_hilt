package com.chrrissoft.daggerhilt.b

import android.content.IntentFilter
import com.chrrissoft.daggerhilt.common.BaseReceiver
import com.chrrissoft.daggerhilt.common.Dependencies
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BGraphReceiver @Inject constructor() : BaseReceiver(
    notificationId = NOTIFICATION_ID,
    onChangeAction = onChangeStateAction,
) {
    @Inject
    @BGraphBridgeDependencyModule.BGraphBridgeStaticQualifier
    override lateinit var dependency: Dependencies.SharedDependency

    companion object {
        private const val NOTIFICATION_ID = 20
        val onChangeStateAction = "${this::class.java.name}.DEPENDENCY_CHANGE_ACTION"
        val filter = IntentFilter(onChangeStateAction)
    }
}
