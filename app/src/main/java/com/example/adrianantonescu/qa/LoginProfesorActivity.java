package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adrianantonescu.qa.database.DatabaseRepository;

public class LoginProfesorActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvForgot;
    private EditText edtUsername;
    private EditText edtPassword;

    private DatabaseRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profesor);
        initComponents();
    }
    private void initComponents(){
        btnLogin = findViewById(R.id.btn_login_profesor);
        btnLogin.setOnClickListener(startTeacherHome());
        tvForgot = findViewById(R.id.tv_forgot_password_profesor);
        tvForgot.setOnClickListener(startForgot());
        edtUsername = findViewById(R.id.et_username_profesor);
        edtPassword = findViewById(R.id.et_password_profesor);
        repository = new DatabaseRepository(getApplicationContext());
    }

    private boolean isValid(){
        if ((edtUsername.getText() == null) || (edtUsername.getText().toString().trim().isEmpty()) || (edtUsername.getText().toString() == null)) {
            return false;
        }
        if ((edtPassword.getText() == null) || (edtPassword.getText().toString().trim().isEmpty()) ||(edtPassword.getText().toString() == null)) {
            return false;
        }
        return true;
    }

    private View.OnClickListener startTeacherHome(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    Intent i = new Intent(getApplicationContext(), TeacherHomeActivity.class);
                    startActivity(i);
                }else if ((edtUsername.getText() == null) || (edtUsername.getText().toString().trim().isEmpty()) || (edtUsername.getText().toString() == null)) {
                    edtUsername.setError(getString(R.string.username_error));
                }else if ((edtPassword.getText() == null) || (edtPassword.getText().toString().trim().isEmpty()) ||(edtPassword.getText().toString() == null)) {
                    edtPassword.setError(getString(R.string.password_error));
                }
            }
        };
    }

    private View.OnClickListener startForgot() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
                startActivity(i);

            }
        };
    }


}
