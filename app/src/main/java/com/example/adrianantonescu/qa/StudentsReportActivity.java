package com.example.adrianantonescu.qa;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.database.DatabaseRepository;
import com.example.adrianantonescu.qa.util.ReportAdapterStudents;
import com.example.adrianantonescu.qa.util.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class StudentsReportActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseRepository databaseRepository;
    private Button btnSaveFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_report);
        init();
    }


    private void init() {
        listView = findViewById(R.id.students_report_lv);
        btnSaveFile = findViewById(R.id.btn_save_report_file);
        btnSaveFile.setOnClickListener(saveReport());
        databaseRepository = new DatabaseRepository(getApplicationContext());

        databaseRepository.open();
        List<Student> lista = databaseRepository.findAllStudents();
        databaseRepository.close();

        ReportAdapterStudents adapterStudents = new ReportAdapterStudents(getApplicationContext(),
                R.layout.lv_student_report, lista, getLayoutInflater());
        listView.setAdapter(adapterStudents);
    }

    private String getStringFromLV(){
        String result = "";
        View v;
        TextView name;
        TextView grades;

        for (int i = 0; i < listView.getChildCount(); i++) {
            v = listView.getChildAt(i);
            name = v.findViewById(R.id.lv_student_report_name);
            grades = v.findViewById(R.id.lv_student_report_grades);
            result += name.getText().toString() + " " + grades.getText().toString() +"\n";
        }
        return result;
    }

    private View.OnClickListener saveReport() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTextAsFile(getString(R.string.student_report_filename), getStringFromLV());
            }
        };
    }

    private void saveTextAsFile(String filename, String content) {
        try {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(content);
            osw.close();
            fos.close();
            Toast.makeText(this, getString(R.string.student_report_saved_to_file), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.students_report_save_error, Toast.LENGTH_LONG).show();
        }

    }

}
