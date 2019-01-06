package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.database.DatabaseRepository;
import com.example.adrianantonescu.qa.firebase.FirebaseController;
import com.example.adrianantonescu.qa.util.Teacher;
import com.example.adrianantonescu.qa.util.constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherProfileSettingsActivity extends AppCompatActivity {

    TextView tvChangePicture, tvChangeBio, tvLogout, tvCancel;
    Intent intent;
    Uri imageUri;
    private DatabaseRepository databaseRepository;
    private Long id;
    private FirebaseController firebaseController;
    private List<Teacher> teachers = new ArrayList<>();
    private String key;
    private Teacher teacher;
    String newBio;


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
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            id = bundle.getLong(constants.ID_KEY);
        }
        firebaseController = FirebaseController.getInstance();
        databaseRepository = new DatabaseRepository(getApplicationContext());
        databaseRepository.open();
        teacher = databaseRepository.queryTeacher(id);
        databaseRepository.close();

        if(bundle != null) {
            newBio = bundle.getString(constants.BIO_KEY);
            if(newBio != null) {
                teacher.setBio(newBio);
            }
        }
        firebaseController.findAllTeachers(selectEventListener());
    }

    private ValueEventListener selectEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Teacher t = data.getValue(Teacher.class);
                    Log.i("TeacherProfileSettings", t.getGlobalId());
                    if (t != null) {
                        teachers.add(t);
                        Log.i("TeacherProfileSettings", "teacher added to list");
                    }
                }
                Integer size = teachers.size();
                if (size == 0) {
                    Log.e("TeacherProfileSettings", "No teachers found");
                }
                for (Teacher t : teachers) {
                    if (t.getId() == teacher.getId()) {
                        Log.i("TeacherProfileSettings", "teacher found");
                        teacher.setGlobalId(t.getGlobalId());
                    }
                }
                key = firebaseController.upsertTeacher(teacher);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TeacherProfileSettings", "Data is not available");
            }
        };
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
                intent.putExtra(constants.ID_KEY, id);
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
