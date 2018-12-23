package com.example.adrianantonescu.qa.util;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adrianantonescu.qa.R;
import com.example.adrianantonescu.qa.network.Stud;

import java.util.List;

public class ReportAdapterStudents extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student> students;
    private LayoutInflater layoutInflater;

    public ReportAdapterStudents(@NonNull Context context, int resource, @NonNull List<Student> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.students=objects;
        this.layoutInflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View line=layoutInflater.inflate(resource,parent,false);
        TextView tvFullName, tvGrades;
        tvFullName=line.findViewById(R.id.lv_student_report_name);
        tvGrades=line.findViewById(R.id.lv_student_report_grades);
        Student stud = students.get(position);
        tvFullName.setText(stud.getFirstName()+" "+stud.getLastName());
        tvGrades.setText(stud.getNote().toString());
        return line;
    }
}
