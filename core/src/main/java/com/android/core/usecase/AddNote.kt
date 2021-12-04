package com.android.core.usecase

import com.android.core.data.Note
import com.android.core.repository.NoteRepository
import javax.inject.Inject

class AddNote @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}