package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.database.DatabaseRepository;
import com.example.adrianantonescu.qa.util.InitializeDbHelper;
import com.example.adrianantonescu.qa.util.constants;

public class LoginProfesorActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvForgot;
    private EditText edtUsername;
    private EditText edtPassword;
    private SharedPreferences sharedPreferences;

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
        InitializeDbHelper initializeDb = new InitializeDbHelper(repository);
        initializeDb.insertInDb();
        sharedPreferences = getSharedPreferences(constants.LOGIN_PREF_FILE_NAME_TEACHER,MODE_PRIVATE);
        restoreSharedPref();
    }


    private void restoreSharedPref() {
        String username = sharedPreferences.getString(constants.LOGIN_USERNAME_PREF_TEACH, null);
        String password = sharedPreferences.getString(constants.LOGIN_PASSWORD_PREF_TEACH, null);

        edtUsername.setText(username);
        edtPassword.setText(password);
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
                String username = edtUsername.getText() != null ?
                        edtUsername.getText().toString() : null;
                String password = edtPassword.getText() != null ?
                        edtPassword.getText().toString() : null;

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(constants.LOGIN_USERNAME_PREF_TEACH, username);
                editor.putString(constants.LOGIN_PASSWORD_PREF_TEACH, password);

                boolean result = editor.commit();

                if (result) {
                    Toast.makeText(getApplicationContext(), getString(R.string.login_shared_succes), Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.login_shared_error), Toast.LENGTH_LONG).show();
                }
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
