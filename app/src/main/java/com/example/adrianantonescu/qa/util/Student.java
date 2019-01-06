package com.example.adrianantonescu.qa.util;

import com.example.adrianantonescu.qa.network.Stud;

import java.util.Arrays;
import java.util.HashMap;

public class Student extends Profile {
    private String specialization;
    private int year;
    private String series;
    private int group;
    HashMap<Long, Integer> note = new HashMap<>();

    public Student(String username, String password, String firstName, String lastName, String email, String bio, String specialization, int year, String series, int group) {
        super(username, password, firstName, lastName, email, bio);
        this.specialization = specialization;
        this.year = year;
        this.series = series;
        this.group = group;
    }

    public Student() {

    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public HashMap<Long, Integer> getNote() {
        return note;
    }

    public void setNote(HashMap<Long, Integer> note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Student{" +
                "specialization='" + specialization + '\'' +
                ", year=" + year +
                ", series='" + series + '\'' +
                ", group=" + group +
                ", note=" + note +
                "} " + super.toString();
    }
}
