package com.example.adrianantonescu.qa.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adrianantonescu.qa.R;
import com.example.adrianantonescu.qa.network.Colectiv;
import com.example.adrianantonescu.qa.network.Stud;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Colectiv> {
    private Context context;
    private int resource;
    private List<Colectiv> cols;
    private LayoutInflater layoutInflater;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Colectiv> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.cols=objects;
        this.layoutInflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tvSubject, tvGroup, tvSeries, tvYear, tvList;
        //ListView lvStuds;
        View linie=layoutInflater.inflate(resource,parent,false);
        tvSubject=linie.findViewById(R.id.lv_stats_tv_subject);
        tvGroup=linie.findViewById(R.id.lv_stats_tv_group);
        tvSeries=linie.findViewById(R.id.lv_stats_tv_series);
        tvYear=linie.findViewById(R.id.lv_stats_tv_year);
        //lvStuds=linie.findViewById(R.id.lv_teacher_stats_student_list);
        tvList=linie.findViewById(R.id.lv_teacher_student_list);
        Colectiv col=cols.get(position);
        tvSubject.setText(col.getSubject());
        tvGroup.setText(col.getGroup().toString());
        tvSeries.setText(col.getSeries());
        tvYear.setText(col.getYear().toString());
        /*List<Stud> studList=new ArrayList<>();
        studList.addAll(col.getStudents());
        StudAdapter studAdapter=new StudAdapter(getContext(),R.layout.lv_studs,studList,layoutInflater);
        lvStuds.setAdapter(studAdapter);*/
        StringBuilder stdList=new StringBuilder();
        for(int i=0;i<col.getStudents().size();i++)
            stdList.append(col.getStudents().get(i).toString());
        tvList.setText(stdList.toString());
        return linie;
    }
}
