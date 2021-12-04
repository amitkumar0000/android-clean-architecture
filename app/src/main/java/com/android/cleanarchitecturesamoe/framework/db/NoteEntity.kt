package com.android.cleanarchitecturesamoe.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "creation_date")
    val creationTime: Long,
    @ColumnInfo(name = "update_time")
    val updateTime: Long,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) = NoteEntity(
            note.title, note.content, note.creationTime, note.updateTime, note.id
        )
    }

    fun toNote() =Note(title = title, content = content, creationTime = creationTime, updateTime = updateTime, id = id)
}