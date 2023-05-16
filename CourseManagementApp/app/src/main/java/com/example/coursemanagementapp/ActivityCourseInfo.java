package com.example.coursemanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityCourseInfo extends AppCompatActivity {
    TextView infoCourseName, infoCourseCredit, infoCourseTime, infoCoursePlace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoCourseName = findViewById(R.id.txtCourseName);
        infoCourseCredit = findViewById(R.id.txtCourseCredit);
        infoCourseTime = findViewById(R.id.txtCourseTime);
        infoCoursePlace = findViewById(R.id.txtCoursePlace);
        //lay du lieu
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int credit = intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        //gan du lieu
        infoCourseName.setText(name);
        infoCourseCredit.setText(credit+"");
        infoCourseTime.setText(time);
        infoCoursePlace.setText(place);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(ActivityCourseInfo.this, ActivityCourse.class);
        startActivity(intent);
        return true;
    }
}
