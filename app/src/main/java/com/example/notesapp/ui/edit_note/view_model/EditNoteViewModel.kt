package com.example.notesapp.ui.edit_note.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.AppDatabase
import com.example.notesapp.data.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditNoteViewModel(application: Application) : AndroidViewModel(application) {

    private val _note = MutableLiveData<Note?>()
    val note: MutableLiveData<Note?> = _note

    private val _updateNoteStatus = MutableLiveData<Boolean>()
    val updateNoteStatus: LiveData<Boolean> get() = _updateNoteStatus
    private val _deleteNoteStatus = MutableLiveData<Boolean>()
    val deleteNoteStatus: LiveData<Boolean> get() = _deleteNoteStatus

    private val noteDao = AppDatabase.DatabaseBuilder.getInstance(application).noteDao()

    fun loadNote(noteId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val loadedNote = noteDao.getNoteById(noteId)
            withContext(Dispatchers.Main) {
                _note.postValue(loadedNote)
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.updateNote(note)
            withContext(Dispatchers.Main) {
                _updateNoteStatus.postValue(true)
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteNote(note)
            withContext(Dispatchers.Main) {
                _deleteNoteStatus.postValue(true)
            }
        }
    }
}