package com.chrrissoft.daggerhilt.c

import com.chrrissoft.daggerhilt.ComponentDestroyerService
import com.chrrissoft.daggerhilt.common.ComponentManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CGraphComponentDestroyerService :
    ComponentDestroyerService<CGraphComponentEntryPoint, CGraphComponent>() {
    @Inject
    @CGraphComponentManagerModule.CGraphComponentManagerQualifier
    override lateinit var manager:
            ComponentManager<CGraphComponentEntryPoint, CGraphComponent>
}
