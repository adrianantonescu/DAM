package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.database.DatabaseRepository;
import com.example.adrianantonescu.qa.util.InitializeDbHelper;

public class StartPageActivity extends AppCompatActivity {
    private Button btnStudent;
    private Button btnTeacher;
    private LinearLayout llAbout;
    private DatabaseRepository repository;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        initComponents();
    }
    private void initComponents(){
        repository= new DatabaseRepository(getApplicationContext());
        InitializeDbHelper initializeDb = new InitializeDbHelper(repository);
        initializeDb.insertInDb();
        btnStudent = findViewById(R.id.btn_student);
        btnStudent.setOnClickListener(startLogin());
        btnTeacher = findViewById(R.id.btn_profesor);
        btnTeacher.setOnClickListener(startTeacherLogin());
        llAbout=findViewById(R.id.start_page_about);
        llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(intent);
            }
        });
    }
    private View.OnClickListener startLogin(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener startTeacherLogin(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginProfesorActivity.class);
                startActivity(i);
            }
        };
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),getString(R.string.start_page_back_press_txt),Toast.LENGTH_LONG).show();
    }

}
