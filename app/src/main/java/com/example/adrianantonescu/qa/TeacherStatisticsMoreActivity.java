package com.example.adrianantonescu.qa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adrianantonescu.qa.network.Colectiv;
import com.example.adrianantonescu.qa.network.Stud;
import com.example.adrianantonescu.qa.util.CustomAdapter;
import com.example.adrianantonescu.qa.util.StudAdapter;
import com.example.adrianantonescu.qa.util.constants;

import java.util.ArrayList;
import java.util.List;

public class TeacherStatisticsMoreActivity extends AppCompatActivity {

    private ListView listView;
    private List<Colectiv> colInfo=null;
    private Colectiv colCibe=null, colStat=null;
    private List<Colectiv> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_statistics_more);
        init();
    }
    private void init(){
        listView=findViewById(R.id.lvSpecializare);
        Intent intent=getIntent();
        if(intent!=null){
            colInfo= (List<Colectiv>) intent.getSerializableExtra(constants.INFO_EC_KEY);
            colCibe= (Colectiv) intent.getSerializableExtra(constants.CIBE_KEY);
            colStat= (Colectiv) intent.getSerializableExtra(constants.STAT_KEY);
        }

        if(colInfo!=null) {
            list.addAll(colInfo);
        }
        if(colCibe!=null) {
            list.add(colCibe);
        }
        if(colStat!=null){
            list.add(colStat);

        }
        //Toast.makeText(getApplicationContext(),list.toString(),Toast.LENGTH_LONG).show();
        CustomAdapter adapter=new CustomAdapter(getApplicationContext(),R.layout.lv_teacher_statistics,list,getLayoutInflater());
        listView.setAdapter(adapter);
    }

}
