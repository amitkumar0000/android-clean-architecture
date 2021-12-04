package com.android.cleanarchitecturesamoe.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cleanarchitecturesamoe.databinding.FragmentListBinding
import com.android.cleanarchitecturesamoe.framework.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), IListAction {
    private lateinit var binding: FragmentListBinding

    private val noteListAdapter by lazy { NoteListAdapter(actions =  this) }

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }

        binding.addNote.setOnClickListener { goToNotesDetails() }

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNote()
    }

    private fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner, Observer { noteList ->
            binding.apply {
                loadingView.isVisible = false
                notesListView.isVisible = true
            }
            noteListAdapter.updateNotes(noteList.sortedByDescending { it.updateTime })
        })
    }

    override fun onClick(id: Long) {
        goToNotesDetails(id)
    }

    private fun goToNotesDetails(id: Long= 0) {
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(binding.notesListView).navigate(action)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}