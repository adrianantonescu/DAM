package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.util.constants;

public class TeacherProfileSettingsActivity extends AppCompatActivity {

    TextView tvChangePicture, tvChangeBio, tvLogout, tvCancel;
    Intent intent;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile_settings);
        init();
    }
    private void init()
    {
        tvChangePicture=findViewById(R.id.teacher_profile_settings_tv_change_profile_picture);
        tvCancel=findViewById(R.id.teacher_profile_settings_tv_cancel);
        tvChangeBio=findViewById(R.id.teacher_profile_settings_tv_change_bio);
        tvLogout=findViewById(R.id.teacher_profile_settings_tv_logout);
        tvChangeBio.setOnClickListener(changeBio());
        tvLogout.setOnClickListener(logout());
        tvCancel.setOnClickListener(cancel());
        tvChangePicture.setOnClickListener(changePicture());
    }

    private View.OnClickListener changePicture()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,constants.PICK_IMG);
            }
        };
    }
     private View.OnClickListener cancel(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
     }

     private View.OnClickListener changeBio(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getApplicationContext(),ChangeBioActivity.class);
                startActivity(intent);
            }
        };
     }

     private View.OnClickListener logout(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getApplicationContext(),StartPageActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),getString(R.string.student_profile_settings_logout_message),Toast.LENGTH_SHORT).show();
            }
        };
     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==constants.PICK_IMG){
            imageUri=data.getData();
            if(imageUri!=null){
                String uriToString=imageUri.toString();
                intent = new Intent(getApplicationContext(), TeacherProfileActivity.class);
                intent.putExtra(constants.IMAGE_URI_KEY, uriToString);
                Toast.makeText(getApplicationContext(),getString(R.string.teacher_profile_settings_pic_updated_msg),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }
    }
}
