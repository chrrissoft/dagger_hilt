package com.chrrissoft.daggerhilt

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.chrrissoft.daggerhilt.common.ComponentManager
import com.chrrissoft.daggerhilt.common.MyEntryPoint

abstract class ComponentDestroyerService<E : MyEntryPoint, C> : Service() {
    abstract var manager: ComponentManager<E, C>

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        manager.destroy()
        return super.onStartCommand(intent, flags, startId).apply { stopSelf() }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
