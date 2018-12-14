package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.adrianantonescu.qa.util.Question;

public class AddOpenQuestionActivity extends AddQuestionAbstractActivity {

    Intent intent;
    Button btnSave;
    TextInputEditText tidQuestionText;
    Spinner spnSubject;
    TextInputEditText tidQuestionAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_open_question);
        initComponents();
        intent = getIntent();
    }


    protected void initComponents(){

        btnSave = findViewById(R.id.add_open_question_button_save);
        btnSave.setOnClickListener(saveEvent());
        tidQuestionText = findViewById(R.id.add_open_question_tid_question_text);
        tidQuestionAnswer = findViewById(R.id.add_open_question_tid_question_answer);
    }

    @Override
    protected boolean isValid() {
        if(tidQuestionText.getText() == null ||
                tidQuestionText.getText().toString().trim().isEmpty() ||
                tidQuestionText.getText().toString() == null) {
            tidQuestionText.setError(getString(R.string.add_simple_question_error1));
            return false;
        }
        if(tidQuestionAnswer.getText() == null ||
                tidQuestionAnswer.getText().toString().trim().isEmpty() ||
                tidQuestionAnswer.getText().toString() == null) {
            tidQuestionAnswer.setError(getString(R.string.add_simple_question_error2));
            return false;
        }
        return true;
    }

    protected View.OnClickListener saveEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()) {
                    Question q;
                    String subject = "BPC";
                    String questionText = tidQuestionText.getText().toString().trim();
                    String questionAnswer = tidQuestionAnswer.getText().toString().trim();
                    q = questionFactory.createQuestion("open", subject,
                            questionText, questionAnswer);
                    AddQuestionActivity.questions.add(q);
                }
                    finish();
            }
        };
    }
}
