package com.nja7.mynoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nja7.mynoteapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var db: NoteSQLDBHeper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteSQLDBHeper(this)
        noteId = intent.getIntExtra("id", -1)
        if (noteId == -1) {
            finish()
            return
        }
        val note = db.getNoteById(noteId)
        binding.addTitleEditText.setText(note.title)
        binding.addContentEditText.setText(note.content)

        binding.updateButton.setOnClickListener {
            val newTitle = binding.addTitleEditText.text.toString()
            val newContent = binding.addContentEditText.text.toString()
            val updatedNote = Note(noteId, newTitle, newContent)
            db.updateNote(updatedNote)
            db.close()
            finish()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }
    }
}