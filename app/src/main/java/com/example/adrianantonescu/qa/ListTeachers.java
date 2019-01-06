package com.example.adrianantonescu.qa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.database.DatabaseRepository;
import com.example.adrianantonescu.qa.util.Teacher;
import com.example.adrianantonescu.qa.util.TeacherAdapter;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ListTeachers extends AppCompatActivity {

    private ListView listView;
    private List<Teacher> list;
    private DatabaseRepository databaseRepository;
    private Button btnSaveFile;

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
        btnSaveFile = findViewById(R.id.btn_save_teachers_list);
        btnSaveFile.setOnClickListener(saveReport());
    }

    private String getStringFromLV(){
        String result = "";
        View v;
        TextView firstName;
        TextView lastName;
        TextView email;

        for (int i = 0; i < listView.getChildCount(); i++) {
            v = listView.getChildAt(i);
            firstName = v.findViewById(R.id.tv_lv_teach_row_first_name);
            lastName = v.findViewById(R.id.tv_lv_teach_row_last_name);
            email = v.findViewById(R.id.tv_lv_teach_row_email);
            result += firstName.getText().toString() + " " + lastName.getText().toString()
                    + email.getText().toString() + "\n";
        }
        return result;
    }

    private View.OnClickListener saveReport() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTextAsFile("list_teachers.txt", getStringFromLV());
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
