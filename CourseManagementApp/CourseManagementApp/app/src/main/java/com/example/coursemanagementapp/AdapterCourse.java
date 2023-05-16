package com.example.coursemanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.IDNA;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AdapterCourse extends BaseAdapter {

    private ActivityCourse context;
    private ArrayList<CourseModel> ArrayListCourse;

    public AdapterCourse(ActivityCourse context, ArrayList<CourseModel> arrayListCourse) {
        this.context = context;
        ArrayListCourse = arrayListCourse;
    }

    @Override
    public int getCount() {
        return ArrayListCourse.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListCourse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listcourse, null);
        TextView textViewCourseName = view.findViewById(R.id.txtCourse);
        TextView textViewCredit = view.findViewById(R.id.txtnumberCredit);

        ImageButton imageInfo = view.findViewById(R.id.info);
        ImageButton imageUpdate = view.findViewById(R.id.update);
        ImageButton imageDelete = view.findViewById(R.id.delete);

        CourseModel courseModel = ArrayListCourse.get(position);

        textViewCourseName.setText(courseModel.getCourseName());
        textViewCredit.setText(courseModel.getNumberOfCredit()+"");
        //textViewCredit.setText(courseModel.getNumberOfCredit());
        //textViewCredit.setText("3");
        int id = courseModel.getId();

        imageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });

        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });
        return view;
    }


}
