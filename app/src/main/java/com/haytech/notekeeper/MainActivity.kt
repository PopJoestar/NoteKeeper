package com.haytech.notekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
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

            notePosition = intent.getIntExtra(
                com.haytech.notekeeper.EXTRA_NOTE_POSITION,
                com.haytech.notekeeper.POSITION_NOT_SET
            )

            if (notePosition != com.haytech.notekeeper.POSITION_NOT_SET) displayNote()

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