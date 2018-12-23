package com.example.adrianantonescu.qa.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adrianantonescu.qa.R;

import java.util.List;

public class TeacherAdapter extends ArrayAdapter<Teacher> {

    private Context context;
    private int resource;
    private List<Teacher> teachers;
    private LayoutInflater inflater;

    public TeacherAdapter(@NonNull Context context, int resource, @NonNull List<Teacher> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.teachers = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View row = inflater.inflate(resource, parent, false);

        TextView tvId = row.findViewById(R.id.tv_lv_teach_row_id);
        TextView tvFirstName = row.findViewById(R.id.tv_lv_teach_row_first_name);
        TextView tvLastName = row.findViewById(R.id.tv_lv_teach_row_last_name);
        TextView tvEmail = row.findViewById(R.id.tv_lv_teach_row_email);

        Teacher teacher = teachers.get(position);

        tvId.setText(teacher.getId() != null ? teacher.getId().toString() : context.getString(R.string.tv_lv_teach_no_id));
        tvFirstName.setText(teacher.getFirstName());
        tvLastName.setText(teacher.getLastName());
        tvEmail.setText(teacher.getEmail());

        return row;
    }
}
