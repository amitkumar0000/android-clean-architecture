package com.android.cleanarchitecturesamoe.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cleanarchitecturesamoe.databinding.ItemNoteBinding
import com.android.core.data.Note
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class NoteListAdapter @Inject constructor(private val notes: ArrayList<Note> = arrayListOf(), private val actions: IListAction)
    : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }


    inner class NoteViewHolder(private val itemNoteBinding: ItemNoteBinding): RecyclerView.ViewHolder(itemNoteBinding.root) {
        fun bind(note: Note) {
            itemNoteBinding.apply {
                title.text = note.title
                content.text = note.content
                date.text = "Last updated ${SimpleDateFormat("MMM DD, HH:mm:ss").format(Date(note.updateTime))}"
                noteLayout.setOnClickListener { actions.onClick(note.id) }
            }
        }

    }
}