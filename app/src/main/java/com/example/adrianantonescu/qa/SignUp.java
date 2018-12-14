package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private Button signUpButton;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etFirstName=findViewById(R.id.sign_up_et_name);
        etLastName=findViewById(R.id.et_last_name);
        etUsername=findViewById(R.id.sign_up_et_username);
        etPassword=findViewById(R.id.sign_up_et_password);
        signUpButton = findViewById(R.id.sign_up_btn_sign_up);
        signUpButton.setOnClickListener(startTeacherHome());
    }


    private boolean isValid() {
        if (etFirstName.getText() == null || etFirstName.getText().toString() == null || etFirstName.getText().toString().trim().isEmpty() ||
                etLastName.getText() ==null || etLastName.getText().toString() ==null || etLastName.getText().toString().trim().isEmpty() ||
                etUsername.getText() == null || etUsername.getText().toString() ==null || etUsername.getText().toString().trim().isEmpty() ||
                etPassword.getText() ==null||etPassword.getText().toString() ==null||etPassword.getText().toString().trim().isEmpty())
            return false;
        return true;
    }

    private View.OnClickListener startTeacherHome() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    Intent i = new Intent(getApplicationContext(),TeacherHomeActivity.class);
                    Toast.makeText(getApplicationContext(),getString(R.string.toast_register), Toast.LENGTH_LONG).show();
                    startActivity(i);
                }
                else
                {
                    if (etFirstName.getText() == null || etFirstName.getText().toString() == null || etFirstName.getText().toString().trim().isEmpty())
                    {  etFirstName.setError(getString(R.string.et_firstname)); }

                    if (etLastName.getText() ==null || etLastName.getText().toString() ==null || etLastName.getText().toString().trim().isEmpty())
                    { etLastName.setError(getString(R.string.et_lastname));}

                    if(etUsername.getText() == null || etUsername.getText().toString() ==null || etUsername.getText().toString().trim().isEmpty())
                    { etUsername.setError(getString(R.string.et_username)); }

                    if (etPassword.getText() ==null||etPassword.getText().toString() ==null||etPassword.getText().toString().trim().isEmpty())
                    {etPassword.setError(getString(R.string.et_password)); }
                }
            }
        };
    }

}
