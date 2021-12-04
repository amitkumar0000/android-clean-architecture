package com.android.cleanarchitecturesamoe.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.android.cleanarchitecturesamoe.R
import com.android.cleanarchitecturesamoe.databinding.FragmentNoteBinding
import com.android.cleanarchitecturesamoe.framework.NoteViewModel
import com.android.core.data.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var noteId = 0L
    private lateinit var binding: FragmentNoteBinding
    private  val viewModel: NoteViewModel by viewModels()

    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }
        if(noteId != 0L) {
            viewModel.getNote(noteId)
        }
        binding.saveView.setOnClickListener {
            saveContent()
        }

        observeViewModel()
    }

    private fun saveContent() {
        binding.apply {
            if(titleView.text.isNotEmpty() || contentView.text.isNotEmpty()) {
                viewModel.saveNote(currentNote.apply {
                    title = titleView.text.toString()
                    content = contentView.text.toString()
                    creationTime = if (id == 0L)  System.currentTimeMillis() else creationTime
                    updateTime = System.currentTimeMillis()
                })
            } else {
                Navigation.findNavController(titleView).popBackStack()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if(it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_LONG).show()
                hideKeyboard()
                Navigation.findNavController(binding.titleView).popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, {note ->
            note?.let {
                currentNote = it
                binding?.apply {
                    titleView.setText(currentNote.title, TextView.BufferType.EDITABLE)
                    contentView.setText(currentNote.content, TextView.BufferType.EDITABLE)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.deleteNote -> {
                context?.let {
                    if(noteId!= 0L) {
                        AlertDialog.Builder(it)
                            .setTitle("Delete Notes")
                            .setMessage("Are you sure want to delete this note?")
                            .setPositiveButton("Yes") {_, _ ->
                                viewModel.deleteNote(note = currentNote)
                            }
                            .setNegativeButton("No") {_, _ -> }
                            .create()
                            .show()
                    }
                }
            }
        }

        return true
    }

    private fun hideKeyboard() {
        (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(binding.contentView.windowToken, 0)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NoteFragment()
    }
}