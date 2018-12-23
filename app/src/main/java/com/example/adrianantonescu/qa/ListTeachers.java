package com.example.adrianantonescu.qa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adrianantonescu.qa.database.DatabaseRepository;
import com.example.adrianantonescu.qa.util.Teacher;
import com.example.adrianantonescu.qa.util.TeacherAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListTeachers extends AppCompatActivity {

    private ListView listView;
    private List<Teacher> list;
    private DatabaseRepository databaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teachers);

        listView = findViewById(R.id.list_teachers_lv);
        databaseRepository = new DatabaseRepository(getApplicationContext());

        databaseRepository.open();
        list = databaseRepository.findAllTeachers();
        databaseRepository.close();
        TeacherAdapter adapter = new TeacherAdapter(getApplicationContext(), R.layout.lv_teacher, list, getLayoutInflater());
        listView.setAdapter(adapter);
    }
}
