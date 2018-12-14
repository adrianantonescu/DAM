package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.adrianantonescu.qa.util.Question;

import java.util.ArrayList;

public class AddQuestionActivity extends AppCompatActivity {

    Intent intent;

    Spinner spnCategories;
    Button btnSelect;
    public static ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        intent = getIntent();
        initComponents();
    }

    protected void initComponents() {
        spnCategories = findViewById(R.id.add_question_spinner);
        btnSelect = findViewById(R.id.add_question_button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.add_question_categories, R.layout.question_spinner_row);
        spnCategories.setAdapter(adapter);
        btnSelect.setOnClickListener(selectEvent());

    }

    private View.OnClickListener selectEvent() {

//        Toast.makeText(getApplicationContext(), category, Toast.LENGTH_LONG).show();
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                long category = spnCategories.getSelectedItemId();
                if(category == 0) {
                    intent = new Intent(getApplicationContext(),
                            AddSimpleQuestionActivity.class);
                    startActivity(intent);
                }

                else if(category == 1){

                    intent = new Intent(getApplicationContext(),
                            AddMultipleQuestionActivity.class);
                    startActivity(intent);
               }
                else {
                    intent = new Intent(getApplicationContext(),
                            AddOpenQuestionActivity.class);
                    startActivity(intent);
                }
            }
        };
    }
}
