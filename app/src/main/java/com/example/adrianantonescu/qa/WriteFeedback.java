package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteFeedback extends AppCompatActivity {

    Button btnFeedback;
    EditText etName;
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_feedback);
        btnFeedback = findViewById(R.id.write_feedback_btn_send);
        btnFeedback.setOnClickListener(sendFeedback());
        etName=findViewById(R.id.write_feedback_et_name);
        etMessage=findViewById(R.id.write_feedback_et_message);
    }
    private boolean isValid()
    {
        if (etName.getText() == null || etName.getText().toString() == null || etName.getText().toString().trim().isEmpty() || etMessage.getText() == null || etMessage.getText().toString() == null || etMessage.getText().toString().trim().isEmpty())
            return false;
        return true;
    }
    private View.OnClickListener sendFeedback() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.feedback_sent), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), WriteFeedback.class);
                    startActivity(i);
                }
                else {
                    if (etName.getText() == null || etName.getText().toString() == null || etName.getText().toString().trim().isEmpty())
                        etName.setError(getString(R.string.feedback_name));
                    if (etMessage.getText() == null || etMessage.getText().toString() == null || etMessage.getText().toString().trim().isEmpty())
                        etMessage.setError(getString(R.string.feedback_message));
                }
            }
        };
    }
}
