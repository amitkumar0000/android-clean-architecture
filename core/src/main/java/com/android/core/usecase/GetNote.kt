package com.android.core.usecase

import com.android.core.repository.NoteRepository
import javax.inject.Inject

class GetNote @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}