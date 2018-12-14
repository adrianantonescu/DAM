package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adrianantonescu.qa.util.constants;


public class ChangeBioActivity extends AppCompatActivity {

    EditText etBio;
    String bio;
    Intent intent;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bio);
        init();
    }
    private void init()
    {
        etBio=findViewById(R.id.change_bio_et_bio);
        btnSave=findViewById(R.id.change_bio_btn_save);
        btnSave.setOnClickListener(doSave());
    }
    private View.OnClickListener doSave(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bio=etBio.getText().toString();
                if(bio.isEmpty()) {
                    Toast.makeText(getApplicationContext(),getString(R.string.change_bio_write_bio_txt),Toast.LENGTH_SHORT).show();
                }
                else {
                    intent = new Intent(getApplicationContext(), TeacherProfileActivity.class);
                    intent.putExtra(constants.BIO_KEY, bio);
                    Toast.makeText(getApplicationContext(),R.string.change_bio_updated_bio_msg,Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        };
    }

}
