package com.haytech.notekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.haytech.notekeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val coursesAdapter = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )

        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding) {
            contentMain.spCourses.adapter = coursesAdapter

            notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?: intent.getIntExtra(
                NOTE_POSITION,
                POSITION_NOT_SET
            )

            if (notePosition != POSITION_NOT_SET) displayNote()
            else {
                DataManager.notes.add(NoteInfo())
                notePosition = DataManager.notes.lastIndex
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                showNextNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)

            if (menuItem != null) {
                menuItem.setIcon(R.drawable.ic_baseline_block_24)
                menuItem.isEnabled = false
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION, notePosition)
    }

    private fun saveNote() {
        if (notePosition == POSITION_NOT_SET) return

        val note = DataManager.notes[notePosition]
        with(binding) {
            note.title = contentMain.etNoteTitle.text.toString()
            note.text = contentMain.etNoteText.text.toString()
            note.course  = contentMain.spCourses.selectedItem as CourseInfo
        }
    }

    private fun showNextNote() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]

        binding.contentMain.etNoteTitle.setText(note.title)
        binding.contentMain.etNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)

        binding.contentMain.spCourses.setSelection(coursePosition, true)
    }

}