package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentSettingsChangePasswordActivity extends AppCompatActivity {

    EditText et_newPass, et_confirm_newPass;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_settings_change_password);
        init();
    }
    private void init(){
        et_newPass=findViewById(R.id.change_pass_et_new_pass);
        et_confirm_newPass=findViewById(R.id.change_pass_et_confirm_pass);
        btnSave=findViewById(R.id.change_pass_btn_save);

        btnSave.setOnClickListener(savePass());
    }

    private View.OnClickListener savePass(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validare())
                {
                    Toast.makeText(getApplicationContext(),getString(R.string.student_settings_change_pass_schimbare_parola_text),Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),getString(R.string.student_settings_change_pass_eroare_schimbare_parola_text),Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private boolean validare()
    {
        String newPass=et_newPass.getText().toString();
        String confirm_newPass=et_confirm_newPass.getText().toString();
        if(!newPass.isEmpty())
            return newPass.equals(confirm_newPass);
        else
            return false;
    }
}
