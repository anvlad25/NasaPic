package com.example.nasapic.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nasapic.databinding.FragmentRecyclerNotesBinding
import com.example.nasapic.model.json_model.notes.NotesData
import com.example.nasapic.ui.adapter.RecyclerNotesFragmentAdapter

class RecyclerNotesFragment : Fragment() {
    private lateinit var adapter: RecyclerNotesFragmentAdapter

    private var _binding: FragmentRecyclerNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(RecyclerNotesViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, { insertMovie(it) })
    }

    private fun insertMovie(moviesList: MutableList<NotesData>) = with(binding) {
        adapter = RecyclerNotesFragmentAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(notes: NotesData) {
                Toast.makeText(context, notes.notesName, Toast.LENGTH_SHORT).show()
            }
        }).apply {
            setNotes(moviesList)
        }
        recyclerViewNotes.adapter = adapter
        recyclerNoteAddFAB.setOnClickListener { adapter.addItem(NotesData("qwe", "qwerty")) }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(notes: NotesData)
    }
}