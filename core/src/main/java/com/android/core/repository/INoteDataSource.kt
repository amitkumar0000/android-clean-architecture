package com.android.core.repository

import com.android.core.data.Note

interface INoteDataSource {
    suspend fun add(note: Note)

    suspend fun get(id: Long): Note?

    suspend fun getAll(): List<Note>

    suspend fun remove(note: Note)
}