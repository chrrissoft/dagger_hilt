package com.chrrissoft.daggerhilt.b

import com.chrrissoft.daggerhilt.ComponentDestroyerService
import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BGraphComponentDestroyerService :
    ComponentDestroyerService<BGraphComponentEntryPoint, BGraphComponent>() {
    @Inject
    @BGraphComponentManagerModule.BGraphComponentManagerQualifier
    override lateinit var manager:
            ComponentManager<BGraphComponentEntryPoint, BGraphComponent>
}
