package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    CardView cvProfile;
    CardView cvFeedback;
    CardView cvGradebook;
    CardView cvStatistics;


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
