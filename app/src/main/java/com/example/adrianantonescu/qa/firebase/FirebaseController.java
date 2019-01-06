package com.example.adrianantonescu.qa.firebase;

import android.media.Image;
import android.net.Uri;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.adrianantonescu.qa.network.Stud;
import com.example.adrianantonescu.qa.util.Profile;
import com.example.adrianantonescu.qa.util.Student;
import com.example.adrianantonescu.qa.util.Teacher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FirebaseController implements FirebaseConstants{
    private DatabaseReference database;
    private FirebaseDatabase controller;
    private static FirebaseController firebaseController;

    private FirebaseController() {
        controller = FirebaseDatabase.getInstance();
    }

    private void openTeachers() {
        database = controller.getReference(TEACHERS_TABLE_NAME);
    }

    private void openStudents() {
        database = controller.getReference(STUDENTS_TABLE_NAME);
    }

    public static FirebaseController getInstance() {
        if(firebaseController == null) {
            synchronized (FirebaseController.class) {
                if(firebaseController == null) {
                    firebaseController = new FirebaseController();
                }
            }
        }
        return firebaseController;
    }

    public String upsertTeacher(Teacher teacher) {
        if(teacher == null) {
            return null;
        }

        openTeachers();
        if(teacher.getGlobalId() == null || teacher.getGlobalId().trim().isEmpty()) {
            teacher.setGlobalId(database.push().getKey());
        }

        database.child(teacher.getGlobalId()).setValue(teacher);

        database.child(teacher.getGlobalId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Teacher temp = dataSnapshot.getValue(Teacher.class);
                if(temp != null) {
                    Log.i("FirebaseController", "Teacher is updated" + temp.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseController", "Teacher is not saved");
            }
        });
        return teacher.getGlobalId();
    }

    public List<Teacher> findAllTeachers(ValueEventListener eventListener) {
        if(eventListener == null) {
            return null;
        }
        final List<Teacher> teachers = new ArrayList<>();
        openTeachers();
        database.addValueEventListener(eventListener);
        return teachers;
    }

    public String upsertStudent(final Student student) {
        if(student == null) {
            return null;
        }

        openStudents();
        if(student.getGlobalId() == null || student.getGlobalId().trim().isEmpty()) {
            student.setGlobalId(database.push().getKey());
        }

        database.child(student.getGlobalId()).setValue(student);

        database.child(student.getGlobalId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student temp = dataSnapshot.getValue(Student.class);
                if(temp != null) {
                    Log.i("FirebaseController", "Student is updated" + temp.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseController", "Student is not saved");
            }
        });
        return student.getGlobalId();
    }

    public List<Student> findAllStudents(ValueEventListener eventListener) {
        if(eventListener == null) {
            return null;
        }
        final List<Student> students = new ArrayList<>();
        openStudents();
        database.addValueEventListener(eventListener);
        return students;
    }
}
