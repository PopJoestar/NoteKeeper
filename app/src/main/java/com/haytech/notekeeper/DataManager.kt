package com.haytech.notekeeper

class DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with intents")
        courses[course.courseId] = course

        course = CourseInfo("android_async", "Android Async programming and services")
        courses[course.courseId] = course

        course = CourseInfo("java_lang", "Java Fundamentals: The Java language")
        courses[course.courseId] = course

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses[course.courseId] = course
    }
}