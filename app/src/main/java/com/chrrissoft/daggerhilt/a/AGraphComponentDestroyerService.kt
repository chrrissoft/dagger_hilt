package com.chrrissoft.daggerhilt.a

import com.chrrissoft.daggerhilt.ComponentDestroyerService
import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AGraphComponentDestroyerService :
    ComponentDestroyerService<AGraphComponentEntryPoint, AGraphComponent>() {
    @Inject
    @AGraphComponentManagerModule.AGraphComponentManagerQualifier
    override lateinit var manager:
            ComponentManager<AGraphComponentEntryPoint, AGraphComponent>
}
