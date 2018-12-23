package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.util.constants;

public class LoginActivity extends AppCompatActivity{

    private Button loginButton;
    private TextView tvForgot;
    private EditText edtUsername;
    private EditText edtPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
    }
    private void initComponents(){
        loginButton = findViewById(R.id.btn_login);
//        loginButton.setOnClickListener(homePageStudent());
        tvForgot = findViewById(R.id.tv_forgot_password);
        tvForgot.setOnClickListener(startForgot());
        edtUsername = findViewById(R.id.et_username);
        edtPassword = findViewById(R.id.et_password);

        sharedPreferences = getSharedPreferences(constants.LOGIN_PREF_FILE_NAME,MODE_PRIVATE);
        loginButton.setOnClickListener(saveEvent());
        restoreSharedPref();
    }

    private View.OnClickListener saveEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText() != null ?
                        edtUsername.getText().toString() : null;
                String password = edtPassword.getText() != null ?
                        edtPassword.getText().toString() : null;

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(constants.LOGIN_USERNAME_PREF, username);
                editor.putString(constants.LOGIN_PASSWORD_PREF, password);

                boolean result = editor.commit();

                if (result) {
                    Toast.makeText(getApplicationContext(), getString(R.string.login_shared_succes), Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.login_shared_error), Toast.LENGTH_LONG).show();
                }
                if (isValid()) {
                    Intent intent = new Intent(getApplicationContext(),
                            HomeActivity.class);
                    startActivity(intent);
                }else if ((edtUsername.getText() == null) || (edtUsername.getText().toString().trim().isEmpty()) || (edtUsername.getText().toString() == null)) {
                    edtUsername.setError(getString(R.string.login_error_inset_username));
                }else if ((edtPassword.getText() == null) || (edtPassword.getText().toString().trim().isEmpty()) ||(edtPassword.getText().toString() == null)) {
                    edtPassword.setError(getString(R.string.login_error_insert_password));
                }
            }
        };
    }

    private void restoreSharedPref() {
        String username = sharedPreferences.getString(constants.LOGIN_USERNAME_PREF, null);
        String password = sharedPreferences.getString(constants.LOGIN_PASSWORD_PREF, null);

        edtUsername.setText(username);
        edtPassword.setText(password);
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

    private boolean isValid(){
        if ((edtUsername.getText() == null) || (edtUsername.getText().toString().trim().isEmpty()) || (edtUsername.getText().toString() == null)) {
            return false;
        }
        if ((edtPassword.getText() == null) || (edtPassword.getText().toString().trim().isEmpty()) ||(edtPassword.getText().toString() == null)) {
            return false;
        }
        return true;
    }

//    private View.OnClickListener homePageStudent() {
//            return new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (isValid()) {
//                        Intent intent = new Intent(getApplicationContext(),
//                                HomeActivity.class);
//                        startActivity(intent);
//                    }else if ((edtUsername.getText() == null) || (edtUsername.getText().toString().trim().isEmpty()) || (edtUsername.getText().toString() == null)) {
//                       edtUsername.setError(getString(R.string.login_error_inset_username));
//                    }else if ((edtPassword.getText() == null) || (edtPassword.getText().toString().trim().isEmpty()) ||(edtPassword.getText().toString() == null)) {
//                        edtPassword.setError(getString(R.string.login_error_insert_password));
//                    }
//
//                }
//
//            };
    }

