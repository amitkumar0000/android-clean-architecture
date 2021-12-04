package com.android.core.usecase

import com.android.core.repository.NoteRepository
import javax.inject.Inject

class GetAllNotes @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getNotes()
}