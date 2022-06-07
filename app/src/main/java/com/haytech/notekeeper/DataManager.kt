package com.haytech.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with intents")
        var note = NoteInfo(course, course.title, "The note")
        notes.add(note)
        courses[course.courseId] = course



        course = CourseInfo("android_async", "Android Async programming and services")
        note = NoteInfo(course, course.title, "The note")
        notes.add(note)
        courses[course.courseId] = course

        course = CourseInfo("java_lang", "Java Fundamentals: The Java language")
        note = NoteInfo(course, course.title, "The note")
        notes.add(note)
        courses[course.courseId] = course

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        note = NoteInfo(course, course.title, "The note")
        notes.add(note)
        courses[course.courseId] = course
    }

}