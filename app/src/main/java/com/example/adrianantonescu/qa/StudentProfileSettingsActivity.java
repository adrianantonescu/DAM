package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class StudentProfileSettingsActivity extends AppCompatActivity {

    CardView cvChangePass, cvEditProfile, cvLogout, cvCancel, cvList;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_settings);
        init();
    }

    private void init()
    {
        cvList = findViewById(R.id.student_settings_cv_list_teachers);
        cvChangePass=findViewById(R.id.student_settings_cv_change_password);
        cvEditProfile=findViewById(R.id.student_settings_cv_edit_profile);
        cvLogout=findViewById(R.id.student_settings_cv_logout);
        cvCancel=findViewById(R.id.student_settings_cv_cancel);
        cvChangePass.setOnClickListener(changePass());
        cvEditProfile.setOnClickListener(editProfile());
        cvLogout.setOnClickListener(logout());
        cvCancel.setOnClickListener(cancel());
        cvList.setOnClickListener(listTeachers());
    }

    private View.OnClickListener changePass()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getApplicationContext(),StudentSettingsChangePasswordActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener editProfile()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getApplicationContext(),EditProfileActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener logout()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getApplicationContext(),StartPageActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),getString(R.string.student_profile_settings_logout_message),Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener cancel()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
    }
    private View.OnClickListener listTeachers(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ListTeachers.class);
                startActivity(intent);
            }
        };
    }
}
