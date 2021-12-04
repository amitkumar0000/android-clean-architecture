package com.android.cleanarchitecturesamoe.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface INoteDao {
    @Insert(onConflict = REPLACE)
    fun addNoteEntity(noteEntity: NoteEntity)

    @Query("Select * from Note where id= :id")
    fun getNoteEntity(id: Long): NoteEntity?

    @Query("Select * from Note")
    fun getAllNoteEntity(): List<NoteEntity>

    @Delete
    fun deleteNoteEntity(noteEntity: NoteEntity)
}