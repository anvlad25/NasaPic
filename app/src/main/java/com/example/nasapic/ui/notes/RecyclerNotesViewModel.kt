package com.example.nasapic.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasapic.model.json_model.notes.NotesData

class RecyclerNotesViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<MutableList<NotesData>> = MutableLiveData()

    fun getData(): LiveData<MutableList<NotesData>> {
        getNotesDataFromSource()
        return liveDataToObserve
    }

    private fun getNotesDataFromSource() {
        Thread {
            liveDataToObserve.postValue(getNotesData())
        }.start()
    }

    private fun getNotesData(): MutableList<NotesData> {
        val notesData: MutableList<NotesData> = mutableListOf(
            NotesData("Бла1", "БлаБлаБла1"),
            NotesData("Бла2", "БлаБлаБла2")
        )

        return notesData
    }
}