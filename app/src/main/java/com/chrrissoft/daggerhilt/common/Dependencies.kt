package com.chrrissoft.daggerhilt.common

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

object Dependencies {

    interface SharedDependencyDependency {
        val entry: String
    }

    class SharedDependencyDependencyImpl @Inject constructor(
        @SharedDependencyDependencyEntryModule.EntryQualifier
        override val entry: String
    ) : SharedDependencyDependency

    interface SharedDependency {
        val state: MutableStateFlow<String>
    }

    class SharedDependencyImpl @Inject constructor(
        dependency: SharedDependencyDependency
    ) : SharedDependency {
        override val state = MutableStateFlow(dependency.entry)
    }
}
