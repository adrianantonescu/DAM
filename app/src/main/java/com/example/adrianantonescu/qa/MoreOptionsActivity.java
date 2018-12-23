package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoreOptionsActivity extends AppCompatActivity {

    private Button btnReport;
    private Button btnViewStatistics;
    private Button btnReportStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_options);
        btnReport = findViewById(R.id.btn_report_application_bug);
        btnReport.setOnClickListener(openReport());
        btnViewStatistics = findViewById(R.id.btn_view_statistics);
        btnViewStatistics.setOnClickListener(openStatistics());
        btnReportStudents = findViewById(R.id.btn_view_student_report);
        btnReportStudents.setOnClickListener(openReportStudents());
    }

    private View.OnClickListener openReportStudents() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),StudentsReportActivity.class);
                startActivity(i);
            }
        };
    }

    private View.OnClickListener openStatistics() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),TeacherStatisticsActivity.class);
                startActivity(i);
            }
        };
    }

    private View.OnClickListener openReport(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ReportBugActivity.class);
                startActivity(i);
            }
        };
    }
}
