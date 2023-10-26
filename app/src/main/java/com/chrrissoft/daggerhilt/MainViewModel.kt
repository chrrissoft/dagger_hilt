package com.chrrissoft.daggerhilt

import androidx.lifecycle.ViewModel
import com.chrrissoft.daggerhilt.common.SharedDependencyDependencyEntryModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @SharedDependencyDependencyEntryModule.MutableEntryQualifier
    private val mutableEntry: MutableStateFlow<String>
) : ViewModel() {
    fun changeEntry(entry: String) {
        mutableEntry.update { entry }
    }
}