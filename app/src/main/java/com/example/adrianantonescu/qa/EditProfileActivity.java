package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.util.constants;

public class EditProfileActivity extends AppCompatActivity {

    EditText etNume, etPrenume, etBio;
    Intent intent;
    Button btnSave;
    String nume, prenume, bio;
    TextView tvEditPhoto;
    Uri imageUri;
    ImageView profilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
    }
    private void init()
    {
        etBio=findViewById(R.id.edit_profile_et_bio);
        etPrenume=findViewById(R.id.edit_profile_et_first_name);
        etNume=findViewById(R.id.edit_profile_et_last_name);
        btnSave=findViewById(R.id.edit_profile_btn_save);
        tvEditPhoto=findViewById(R.id.edit_profile_tv_change_picture);
        tvEditPhoto.setOnClickListener(changePic());
        btnSave.setOnClickListener(doSave());
        profilePic=findViewById(R.id.edit_profile_profile_pic);
    }
    private View.OnClickListener doSave(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getApplicationContext(),StudentProfileActivity.class);
                nume=etNume.getText().toString();
                prenume=etPrenume.getText().toString();
                bio=etBio.getText().toString();
                intent.putExtra(constants.FIRST_NAME_KEY,prenume);
                intent.putExtra(constants.LAST_NAME_KEY,nume);
                intent.putExtra(constants.BIO_KEY,bio);
                if(imageUri!=null) {
                    String uriToStrig = imageUri.toString();
                    intent.putExtra(constants.IMAGE_URI_KEY, uriToStrig);
                }
                Toast.makeText(getApplicationContext(),getString(R.string.edit_profile_updated_profile_msg),Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        };
    }

    private View.OnClickListener changePic()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,constants.PICK_IMG);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==constants.PICK_IMG&&resultCode==RESULT_OK)
        {
            imageUri=data.getData();
            profilePic.setImageURI(imageUri);
        }
    }
}
