package com.example.adrianantonescu.qa.util;

import java.util.Arrays;

public class Teacher extends Profile {

    private Course[] courses;

    public Teacher(String username, String password, String firstName, String lastName, String email, String bio, Course[] courses) {
        super(username, password, firstName, lastName, email, bio);
        this.courses = courses;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "courses=" + Arrays.toString(courses) +
                "} " + super.toString();
    }
}
