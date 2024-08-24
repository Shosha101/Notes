package com.example.notesapp.ui.add_note.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.notesapp.R

import com.example.notesapp.data.models.Note
import com.example.notesapp.databinding.ActivityEditNoteBinding
import com.example.notesapp.ui.add_note.view_model.EditNoteViewModel
import com.google.android.material.snackbar.Snackbar

class EditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding
    private val viewModel: EditNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note)

        val noteId = intent.getLongExtra("NOTE_ID", -1L)
        if (noteId != -1L) {
            viewModel.loadNote(noteId)
        }

        binding.btEdit.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val noteText = binding.etNote.text.toString()
            if (title.isNotEmpty() && noteText.isNotEmpty()) {
                val updatedNote = Note(
                    id = noteId,
                    title = title,
                    note = noteText
                )
                viewModel.updateNote(updatedNote)
            }
        }

        binding.ivDelete.setOnClickListener {
            val noteId = intent.getLongExtra("NOTE_ID", -1L)
            if (noteId != -1L) {
                val note = viewModel.note.value
                note?.let {
                    viewModel.deleteNote(it)
                }
            }
        }

        viewModel.note.observe(this, Observer { note ->
            note?.let {
                binding.etTitle.setText(it.title)
                binding.etNote.setText(it.note)
            }
        })

        viewModel.updateNoteStatus.observe(this, Observer { isUpdated ->
            if (isUpdated) {
                binding.btEdit.hideKeyboard()
                Snackbar.make(binding.main, R.string.note_updated, Snackbar.LENGTH_LONG)
                    .setAction(R.string.dismiss) {
                        finish()
                    }.show()
            }
        })

        viewModel.deleteNoteStatus.observe(this, Observer { isDeleted ->
            if (isDeleted) {
                Snackbar.make(binding.main, R.string.note_deleted, Snackbar.LENGTH_LONG)
                    .setAction(R.string.dismiss) {
                        finish()
                    }.show()
            }
        })
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
