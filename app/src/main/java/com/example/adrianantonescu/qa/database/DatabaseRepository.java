package com.example.adrianantonescu.qa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.adrianantonescu.qa.network.Stud;
import com.example.adrianantonescu.qa.util.Course;
import com.example.adrianantonescu.qa.util.Student;
import com.example.adrianantonescu.qa.util.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    //    /**
//     * insert dummy teacher/student for log in
//     */
//    private long insertAdmin() {
//        Teacher teacher = new Teacher("admin1", "admin",
//                "asdf", "dfgh", "sdjflksdfjlskdflsjd@ase.ro", "bio bio");
//        return database.insert(TEACHER_PROFILE_TABLE_NAME, null, createContentValuesFromTeacherProfile(teacher));
//    }
    public void close(){
        try{
            database.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    //DML pentru tabela STUDENT_PROFILE

    public long insertStudent(Student student){
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

    //DML pentru tabela

    public int updateTeacher(Teacher teacher){
        if (teacher == null) {
            return -1;
        }
        return database.update(TEACHER_PROFILE_TABLE_NAME, createContentValuesFromTeacherProfile(teacher),
                TEACHER_PROFILE_COLUMN_ID + "=?", new String[]{teacher.getId().toString()});
    }

    public long insertTeacher(Teacher teacher){
        if (teacher == null) {
            return -1;
        }
        return database.insert(TEACHER_PROFILE_TABLE_NAME, null, createContentValuesFromTeacherProfile(teacher));
    }

    //DML pentru tabela COURSES

    public long insertCourse(Course course){
        if (course == null) {
            return -1;
        }
        return database.insert(COURSES_TABLE_NAME, null, createContentValuesFromCourses(course));
    }

    //DML pentru tabela STUDENTSCORES
    public long insertStudentScore(Student student, Course course, int points) {
        if(student == null || course == null) {
            return -1;
        }
        return database.insert(STUDENT_SCORES_TABLE_NAME, null,
                createContentValuesFromStudentCourse(student, course, points));
    }

    //DML PENTRU TABELA DISTRIBUTION
    public long insertDistribution(Teacher teacher, Course course) {
        if(teacher == null || course == null) {
            return -1;
        }
        return database.insert(DISTRIBUTION_TABLE_NAME, null,
                createContentValuesFromTeacherCourse(teacher, course));
    }

    private ContentValues createContentValuesFromTeacherCourse(Teacher teacher, Course course) {
        if(teacher == null || course == null) {
            return null;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(DISTRIBUTION_COLUMN_ID, 0);
        contentValues.put(DISTRIBUTION_COLUMN_TEACHER_ID, teacher.getId());
        contentValues.put(DISTRIBUTION_COLUMN_COURSE_ID, course.getId());

        return contentValues;
    }

    private ContentValues createContentValuesFromStudentCourse(Student student, Course course, int points) {
        if(student == null || course == null) {
            return null;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENT_SCORES_COLUMN_ID, 0);
        contentValues.put(STUDENT_SCORES_COLUMN_STUDENT_ID, student.getId());
        contentValues.put(STUDENT_SCORES_COLUMN_COURSE_ID, course.getId());
        contentValues.put(STUDENT_SCORES_COLUMN_POINTS, points);

        return contentValues;
    }

    public ContentValues createContentValuesFromStudentProfile(Student student){
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

    public int queryStudentForLogin(String username, String password) {
        String queryString = "SELECT " + STUDENT_PROFILE_COLUMN_USERNAME + ", "
                + STUDENT_PROFILE_COLUMN_PASSWORD + " FROM " + STUDENT_PROFILE_TABLE_NAME
                + " WHERE username = ?";

        Cursor cursor = database.rawQuery(queryString, new String[]{username});
        if(!cursor.isNull(0)) {
            cursor.moveToFirst();
            String usernameDb = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_USERNAME));
            String passwordDb = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_PASSWORD));
            cursor.close();
            if (usernameDb.equals(username) && passwordDb.equals(password)) {
                return -1;
            }
        } else {
            return -1;
        }
        return 1;
    }

    public int queryTeacherForLogin(String username, String password) {
        String queryString  = "SELECT " + TEACHER_PROFILE_USERNAME +", "
                + TEACHER_PROFILE_PASSWORD + " FROM " + TEACHER_PROFILE_TABLE_NAME
                + " WHERE username = ?";
        Cursor cursor = database.rawQuery(queryString, new String[] {username});
        cursor.moveToFirst();
        String usernameDb = cursor.getString(cursor.getColumnIndex(TEACHER_PROFILE_USERNAME));
        String passwordDb = cursor.getString(cursor.getColumnIndex(TEACHER_PROFILE_PASSWORD));
        cursor.close();
        if(usernameDb.equals(username) && passwordDb.equals(password)) {
            return -1;
        }
        return 1;
    }



    public List<Student> findAllStudents(){
        List<Student> studenti = new ArrayList<>();
        Cursor cursor = database.query(STUDENT_PROFILE_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_ID));
            String username = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_PASSWORD));
            String firstName = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_LAST_NAME));
            String email = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_EMAIL));
            String spec = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_SPECIALIZATION));
            Integer year = cursor.getInt(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_YEAR));
            String series = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_SERIES));
            Integer group = cursor.getInt(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_GROUP));
            String bio = cursor.getString(cursor.getColumnIndex(STUDENT_PROFILE_COLUMN_BIO));

            Cursor cursor1 = database.query(STUDENT_SCORES_TABLE_NAME, null, STUDENT_SCORES_COLUMN_STUDENT_ID +"=?",
                    new String[]{id.toString()}, null, null, null);
            HashMap<Long, Integer> note = new HashMap<Long, Integer>();

            while (cursor1.moveToNext()) {
                Long courseID = cursor1.getLong(cursor1.getColumnIndex(STUDENT_SCORES_COLUMN_COURSE_ID));
                Integer points = cursor1.getInt(cursor1.getColumnIndex(STUDENT_SCORES_COLUMN_POINTS));
                note.put(courseID, points);
            }
            cursor1.close();

            Student student = new Student(username, password, firstName, lastName, email, bio,  spec, year, series, group);
            student.setNote(note);
            studenti.add(student);

        }
        cursor.close();

        return studenti;
    }

    public List<Teacher> findAllTeachers(){
        List<Teacher> profesori = new ArrayList<>();
        Cursor cursor = database.query(TEACHER_PROFILE_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(TEACHER_PROFILE_COLUMN_ID));
            String firstName = cursor.getString(cursor.getColumnIndex(TEACHER_PROFILE_COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(TEACHER_PROFILE_COLUMN_LAST_NAME));
            String email = cursor.getString(cursor.getColumnIndex(TEACHER_PROFILE_COLUMN_EMAIL));
            profesori.add(new Teacher(id, null, null, firstName, lastName, email, null, null));
        }
        cursor.close();
        return profesori;
    }
}
