package com.nja7.mynoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nja7.mynoteapp.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private  lateinit var db: NoteSQLDBHeper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= NoteSQLDBHeper(this)
        binding.addButton.setOnClickListener{
            val title = binding.addTitleEditText.text.toString()
            val content = binding.addContentEditText.text.toString()
            val note = Note(0,title,content)
            db.insertNote(note)
            finish()
            Toast.makeText(this, "note added", Toast.LENGTH_SHORT).show()

        }

    }
}