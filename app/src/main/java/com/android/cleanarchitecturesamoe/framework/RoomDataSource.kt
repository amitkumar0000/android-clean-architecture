package com.android.cleanarchitecturesamoe.framework

import android.content.Context
import com.android.cleanarchitecturesamoe.framework.db.DatabaseService
import com.android.cleanarchitecturesamoe.framework.db.NoteEntity
import com.android.core.data.Note
import com.android.core.repository.INoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RoomDataSource @Inject constructor(@ApplicationContext context: Context): INoteDataSource{
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) {
        noteDao.addNoteEntity(noteEntity = NoteEntity.fromNote(note))
    }

    override suspend fun get(id: Long): Note? {
        return noteDao.getNoteEntity(id)?.toNote()
    }

    override suspend fun getAll(): List<Note> {
       return noteDao.getAllNoteEntity().map { it.toNote() }
    }

    override suspend fun remove(note: Note) {
        noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
    }
}