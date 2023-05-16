package com.example.coursemanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterStudent extends BaseAdapter {

    private ActivityStudent context;
    private ArrayList<StudentModel> ArrayListStudent;

    public AdapterStudent(ActivityStudent context, ArrayList<StudentModel> arrayListStudent) {
        this.context = context;
        ArrayListStudent = arrayListStudent;
    }

    @Override
    public int getCount() {
        return ArrayListStudent.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.liststudent, null);

        TextView txtName = convertView.findViewById(R.id.txtStudent);
        TextView txtIDCode = convertView.findViewById(R.id.txtIDCode);

        ImageButton btnInfo = convertView.findViewById(R.id.info);
        ImageButton btnUpdate = convertView.findViewById(R.id.update);
        ImageButton btnDelete = convertView.findViewById(R.id.delete);

        StudentModel studentModel = ArrayListStudent.get(position);

        txtName.setText(studentModel.getNameStudent());
        txtIDCode.setText(studentModel.getCodeStudent());

        int id = studentModel.getIdStudent();

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.infomation(id);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateStudent(id);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });

        return convertView;
    }
}
