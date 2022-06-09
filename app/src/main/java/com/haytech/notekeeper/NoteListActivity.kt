package com.haytech.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.haytech.notekeeper.databinding.ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        with(binding) {
            fab.setOnClickListener {
                val activityIntent = Intent(this@NoteListActivity, MainActivity::class.java)

                startActivity(activityIntent)

            }
            contentNoteList.lvNoteList.adapter = ArrayAdapter<NoteInfo>(
                this@NoteListActivity,
                android.R.layout.simple_list_item_1,
                DataManager.notes
            )

            contentNoteList.lvNoteList.setOnItemClickListener { _, _, position, _ ->
                val activityIntent = Intent(this@NoteListActivity, MainActivity::class.java)
                activityIntent.putExtra(EXTRA_NOTE_POSITION, position)

                startActivity(activityIntent)
            }
        }

    }

}