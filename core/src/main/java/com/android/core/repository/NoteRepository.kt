package com.android.core.repository

import com.android.core.data.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dataSource: INoteDataSource) {
    suspend fun addNote(note: Note) = dataSource.add(note)

    suspend fun getNote(id: Long) = dataSource.get(id)

    suspend fun getNotes() = dataSource.getAll()

    suspend fun removeNote(note: Note) = dataSource.remove(note)
}