package com.example.adrianantonescu.qa;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adrianantonescu.qa.database.DatabaseRepository;
import com.example.adrianantonescu.qa.util.ReportAdapterStudents;
import com.example.adrianantonescu.qa.util.Student;

import java.util.List;

public class StudentsReportActivity extends AppCompatActivity {

    ListView listView;
    DatabaseRepository databaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_report);
        init();
    }

    private void init() {
        listView = findViewById(R.id.students_report_lv);
        databaseRepository = new DatabaseRepository(getApplicationContext());

        databaseRepository.open();
        List<Student> lista = databaseRepository.findAllStudents();
        databaseRepository.close();

        ReportAdapterStudents adapterStudents = new ReportAdapterStudents(getApplicationContext(),
                R.layout.lv_student_report, lista, getLayoutInflater());
        listView.setAdapter(adapterStudents);
    }
}
