package com.haytech.notekeeper

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.haytech.notekeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coursesAdapter = ArrayAdapter<CourseInfo>(
                 this,
                android.R.layout.simple_spinner_item,
                DataManager.courses.values.toList()
            )

        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spCourses.adapter = coursesAdapter


    }

}