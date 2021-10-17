package com.example.nasapic.ui.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapic.databinding.FragmentRecyclerNotesItemBinding
import com.example.nasapic.model.json_model.notes.NotesData
import com.example.nasapic.ui.notes.RecyclerNotesFragment

class RecyclerNotesFragmentAdapter(private var itemClickListener: RecyclerNotesFragment.OnItemViewClickListener) :
    RecyclerView.Adapter<RecyclerNotesFragmentAdapter.RecyclerViewHolder>() {

    private var notesData: MutableList<Pair<NotesData, Boolean>> = mutableListOf()
    private lateinit var binding: FragmentRecyclerNotesItemBinding

    fun setNotes(data: MutableList<NotesData>) {
        data.forEach {
            notesData.add(Pair(it, false))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        binding = FragmentRecyclerNotesItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(notesData[position])
    }

    override fun getItemCount() = notesData.size

    fun addItem(note: NotesData) {
        notesData.add(Pair(note, false))
        notifyItemInserted(itemCount - 1)
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(notesData: Pair<NotesData, Boolean>) = with(binding) {
            noteName.text = makeBlueText(notesData.first.notesName)
            noteDesc.text = notesData.first.notesDesc
            root.setOnClickListener {
                itemClickListener.onItemViewClick(notesData.first)
                toggleText()
            }
            noteDelete.setOnClickListener { deleteItem() }
            noteUp.setOnClickListener { moveUp() }
            noteDown.setOnClickListener { moveDown() }
            noteDesc.visibility = if (notesData.second) View.VISIBLE else View.GONE
        }

        private fun makeBlueText(text: String): SpannableString {
            val notesNameSpans = SpannableString(text)
            notesNameSpans.setSpan(
                ForegroundColorSpan(Color.BLUE),
                0, text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            return notesNameSpans
        }

        fun deleteItem() {
            notesData.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        private fun moveUp() {
            layoutPosition.takeIf { it >= 1 }?.also { currentPosition ->
                notesData.removeAt(currentPosition).apply {
                    notesData.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

        private fun moveDown() {
            layoutPosition.takeIf { it < notesData.size - 1 }?.also { currentPosition ->
                notesData.removeAt(currentPosition).apply {
                    notesData.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }

        private fun toggleText() {
            notesData[layoutPosition] =
                Pair(notesData[layoutPosition].first, !notesData[layoutPosition].second)
            notifyItemChanged(layoutPosition)
        }
    }
}