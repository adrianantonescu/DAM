package com.example.adrianantonescu.qa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adrianantonescu.qa.network.ManagerHttp;
import com.example.adrianantonescu.qa.network.ReadJson;
import com.example.adrianantonescu.qa.network.Specializare;
import com.example.adrianantonescu.qa.util.CustomAdapter;
import com.example.adrianantonescu.qa.util.constants;

import org.json.JSONException;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class TeacherStatisticsActivity extends AppCompatActivity {

    private static final String link="https://api.myjson.com/bins/hpdv6";
    private URL url;

    {
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private Specializare specializare=null;
    private Button infoEconomica, cibernetica, statistica;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_statistics);
        @SuppressLint("StaticFieldLeak") ManagerHttp managerHttp=new ManagerHttp(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    specializare=ReadJson.read(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        managerHttp.execute(url);
        init();
    }
    private void init(){
        infoEconomica=findViewById(R.id.btn_informatics);
        cibernetica=findViewById(R.id.btn_cybernetics);
        statistica=findViewById(R.id.btn_statistics);
        intent=new Intent(getApplicationContext(),TeacherStatisticsMoreActivity.class);
        infoEconomica.setOnClickListener(doInfo());
        cibernetica.setOnClickListener(doCibe());
        statistica.setOnClickListener(doStat());
    }
    private View.OnClickListener doInfo(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(constants.INFO_EC_KEY, (Serializable) specializare.getInformaticaEconomica());
                startActivity(intent);
                finish();
            }
        };
    }

    private View.OnClickListener doCibe(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(constants.CIBE_KEY,specializare.getCiberneticaEconomica());
                startActivity(intent);
                finish();
            }
        };
    }
    private View.OnClickListener doStat(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(constants.STAT_KEY,specializare.getStatistica());
                startActivity(intent);
                finish();
            }
        };
    }
}
