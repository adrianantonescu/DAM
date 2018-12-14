package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrianantonescu.qa.util.constants;

public class StudentProfileActivity extends AppCompatActivity {
    ImageView imgSettings, imgProfilePic;
    Button btnBack;
    TextView tvNumePrenume, tvBio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        init();
    }
    private void init()
    {
        imgSettings=findViewById(R.id.student_activity_image_view_settings);
        imgSettings.setOnClickListener(openSettings());
        btnBack=findViewById(R.id.student_profile_btn_back);
        btnBack.setOnClickListener(backTo());
        tvNumePrenume=findViewById(R.id.student_profile_tv_nume);
        tvBio=findViewById(R.id.student_profile_tv_bio);
        imgProfilePic=findViewById(R.id.student_profile_photo);
        Intent intent=getIntent();
        String prenume, nume, bio, imageUri;
        Bundle bundle=intent.getExtras();
        if(bundle!=null) {
            imageUri = bundle.getString(constants.IMAGE_URI_KEY);
            if (imageUri != null)
                if (!imageUri.isEmpty()) {
                    Uri stringToUri = Uri.parse(imageUri);
                    imgProfilePic.setImageURI(stringToUri);
                }
            prenume = bundle.getString(constants.FIRST_NAME_KEY);
            nume = bundle.getString(constants.LAST_NAME_KEY);
            bio = bundle.getString(constants.BIO_KEY);
            if (prenume != null && nume != null)
                if (!prenume.isEmpty() && !nume.isEmpty())
                    tvNumePrenume.setText(String.format("%s %s", nume, prenume));
            if (bio != null)
                if (!bio.isEmpty())
                    tvBio.setText(bio);
        }
    }
    private View.OnClickListener openSettings()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StudentProfileSettingsActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener backTo(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        };
    }
}
