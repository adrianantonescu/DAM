package com.example.adrianantonescu.qa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.adrianantonescu.qa.network.Stud;
import com.example.adrianantonescu.qa.util.Course;
import com.example.adrianantonescu.qa.util.Student;
import com.example.adrianantonescu.qa.util.Teacher;

public class DatabaseRepository implements DatabaseConstants {
    private SQLiteDatabase database;
    private DatabaseController controller;

    public DatabaseRepository(Context context) {
        controller = DatabaseController.getInstance(context);
    }

    public void open(){
        try{
            database = controller.getWritableDatabase();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            database.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    //DML pentru tabela STUDENT_PROFILE

    public long inserStudent(Student student){
        if (student == null) {
            return -1;
        }

        return database.insert(STUDENT_PROFILE_TABLE_NAME, null, createContentValuesFromStudentProfile(student));
    }

    public int updateStudent(Student student){
        if (student == null) {
            return -1;
        }

        return database.update(STUDENT_PROFILE_TABLE_NAME, createContentValuesFromStudentProfile(student),
                STUDENT_PROFILE_COLUMN_ID + "=?", new String[]{student.getId().toString()});
    }

    public int deleteStudent(Student student){
        if (student == null) {
            return -1;
        }
        return database.delete(STUDENT_PROFILE_TABLE_NAME, STUDENT_PROFILE_COLUMN_ID + "=?",
                new String[]{student.getId().toString()});
    }

    //DML pentru tabela TEACHER_PROFILE

    public int updateTeacher(Teacher teacher){
        if (teacher == null) {
            return -1;
        }
        return database.update(TEACHER_PROFILE_TABLE_NAME, createContentValuesFromTeacherProfile(teacher),
                TEACHER_PROFILE_COLUMN_ID + "=?", new String[]{teacher.getId().toString()});
    }

    //DML pentru tabela COURSES

    public long insertCourse(Course course){
        if (course == null) {
            return -1;
        }
        return database.insert(COURSES_TABLE_NAME, null, createContentValuesFromCourses(course));
    }

    private ContentValues createContentValuesFromStudentProfile(Student student){
        if (student == null) {
            return null;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENT_PROFILE_COLUMN_ID, student.getId());
        contentValues.put(STUDENT_PROFILE_COLUMN_USERNAME, student.getUsername());
        contentValues.put(STUDENT_PROFILE_COLUMN_PASSWORD, student.getPassword());
        contentValues.put(STUDENT_PROFILE_COLUMN_FIRST_NAME, student.getFirstName());
        contentValues.put(STUDENT_PROFILE_COLUMN_LAST_NAME, student.getLastName());
        contentValues.put(STUDENT_PROFILE_COLUMN_EMAIL, student.getEmail());
        contentValues.put(STUDENT_PROFILE_COLUMN_SPECIALIZATION, student.getSpecialization());
        contentValues.put(STUDENT_PROFILE_COLUMN_YEAR, student.getYear());
        contentValues.put(STUDENT_PROFILE_COLUMN_SERIES, student.getSeries());
        contentValues.put(STUDENT_PROFILE_COLUMN_GROUP, student.getGroup());
        contentValues.put(STUDENT_PROFILE_COLUMN_BIO, student.getBio());
        return contentValues;
    }

    private ContentValues createContentValuesFromTeacherProfile(Teacher teacher){
        if (teacher == null) {
            return null;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(TEACHER_PROFILE_COLUMN_ID, teacher.getId());
        contentValues.put(TEACHER_PROFILE_USERNAME, teacher.getUsername());
        contentValues.put(TEACHER_PROFILE_PASSWORD, teacher.getPassword());
        contentValues.put(TEACHER_PROFILE_COLUMN_FIRST_NAME, teacher.getFirstName());
        contentValues.put(TEACHER_PROFILE_COLUMN_LAST_NAME, teacher.getLastName());
        contentValues.put(TEACHER_PROFILE_COLUMN_EMAIL, teacher.getEmail());
        contentValues.put(TEACHER_PROFILE_BIO, teacher.getBio());

        return contentValues;
    }

    private ContentValues createContentValuesFromCourses(Course course){
        if(course==null){
            return null;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSES_COLUMN_ID, course.getId());
        contentValues.put(COURSES_COLUMN_NAME, course.getName());
        contentValues.put(COURSES_COLUMN_CREDITS, course.getCredit());

        return contentValues;
    }


}
