package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.example.adrianantonescu.qa.util.constants;

public class HomeActivity extends AppCompatActivity {

    CardView cvProfile;
    CardView cvFeedback;
    CardView cvGradebook;
    CardView cvStatistics;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        initializare();
    }
    private void initializare(){
        cvProfile=findViewById(R.id.student_home_profile_cardView);
        cvProfile.setOnClickListener(openProfile());
        cvFeedback = findViewById(R.id.student_feedback_cardView);
        cvFeedback.setOnClickListener(startFeedback());
        cvGradebook = findViewById(R.id.student_home_gradebook_cardView);
        cvGradebook.setOnClickListener(startGradebook());
        cvStatistics = findViewById(R.id.student_home_statistics_cardView);
        cvStatistics.setOnClickListener(startStatistics());
        intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            Long getId = bundle.getLong(constants.ID_KEY);
            Toast.makeText(getApplicationContext(),getId.toString(),Toast.LENGTH_LONG).show();
        }
    }

    private View.OnClickListener startStatistics() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),StatisticsActivity.class);
                startActivity(i);
            }
        };
    }

    private View.OnClickListener startGradebook() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GradebookActivity.class);
                startActivity(i);
            }
        };
    }

    private View.OnClickListener startFeedback() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),WriteFeedback.class);
                startActivity(i);
            }
        };
    }

    private View.OnClickListener openProfile(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StudentProfileActivity.class);
                startActivity(intent);
            }
        };
    }

}
