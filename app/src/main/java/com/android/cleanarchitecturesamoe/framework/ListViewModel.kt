package com.android.cleanarchitecturesamoe.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.core.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val useCase: NoteUseCase): ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val notes = MutableLiveData<List<Note>>()

    fun getAllNote() {
        coroutineScope.launch {
            notes.postValue( useCase.getAllNotes())
        }
    }
}