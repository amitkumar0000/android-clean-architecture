package com.android.cleanarchitecturesamoe.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.core.data.Note
import com.android.core.repository.NoteRepository
import com.android.core.usecase.AddNote
import com.android.core.usecase.GetAllNotes
import com.android.core.usecase.GetNote
import com.android.core.usecase.RemoveNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(application: Application, private val useCase: NoteUseCase): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCase.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            currentNote.postValue(useCase.getNote(id))
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch {
            useCase.removeNote(note)
            saved.postValue(true)
        }
    }
}