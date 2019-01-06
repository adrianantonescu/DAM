package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adrianantonescu.qa.database.DatabaseRepository;
import com.example.adrianantonescu.qa.util.Chart;
import com.example.adrianantonescu.qa.util.Student;
import com.example.adrianantonescu.qa.util.constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradebookActivity extends AppCompatActivity {

    DatabaseRepository repository;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null) {
            Long id = bundle.getLong(constants.ID_KEY);
            repository = new DatabaseRepository(getApplicationContext());
            repository.open();
            List<Student> list1 = repository.findAllStudents();
            repository.close();
//            Toast.makeText(getApplicationContext(),id.toString(),Toast.LENGTH_LONG).show();
            Map<Long, Integer> results=null;
            for (Student std : list1)
                if (std.getId().equals(id)) {
                    results = std.getNote();
                }
//            Toast.makeText(getApplicationContext(),list1.toString(),Toast.LENGTH_LONG).show();
            if(results==null)
                Toast.makeText(getApplicationContext(),R.string.gradebook_toast_no_grades,Toast.LENGTH_LONG).show();
            Chart chart = new Chart(getApplicationContext(), results);
            setContentView(chart);
        }

    }

}
