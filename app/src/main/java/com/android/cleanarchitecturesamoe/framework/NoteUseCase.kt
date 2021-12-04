package com.android.cleanarchitecturesamoe.framework

import com.android.core.usecase.AddNote
import com.android.core.usecase.GetAllNotes
import com.android.core.usecase.GetNote
import com.android.core.usecase.RemoveNote
import javax.inject.Inject

data class NoteUseCase @Inject constructor(
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
)
