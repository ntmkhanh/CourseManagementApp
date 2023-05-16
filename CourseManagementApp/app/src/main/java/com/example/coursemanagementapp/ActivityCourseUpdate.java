package com.example.coursemanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCourseUpdate extends AppCompatActivity {
    EditText upCourseName, upCourseCredit, upCourseTime, upCoursePlace;
    Button btnUpCourse;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        upCourseName = findViewById(R.id.edTextUpdateCourseName);
        upCourseCredit = findViewById(R.id.edTextUpdateCourseCredit);
        upCourseTime = findViewById(R.id.edTextUpdateCourseTime);
        upCoursePlace = findViewById(R.id.edTextUpdateCoursePlace);
        btnUpCourse = findViewById(R.id.btnCourseUpdate);

        //lay du lieu intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        int credit = intent.getIntExtra("credit", 0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        upCourseName.setText(name);
        upCourseCredit.setText(credit+"");
        upCourseTime.setText(time);
        upCoursePlace.setText(place);

        dbHandler = new DBHandler(this);

        btnUpCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = upCourseName.getText().toString().trim();
                String credit = upCourseCredit.getText().toString().trim();
                String time = upCourseTime.getText().toString().trim();
                String place = upCoursePlace.getText().toString().trim();

                if(courseName.equals("") || credit.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityCourseUpdate.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
                } else{
                    CourseModel courseModel = updateCourseModel();
                    dbHandler.updateCourse(courseModel, id);
                    Intent intent = new Intent(ActivityCourseUpdate.this, ActivityCourse.class);
                    startActivity(intent);
                    Toast.makeText(ActivityCourseUpdate.this, "Update sucess", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // luu du lieu edit update
    public CourseModel updateCourseModel(){
        String courseName = upCourseName.getText().toString().trim();
        int credit = Integer.parseInt(upCourseCredit.getText().toString().trim());
        String time = upCourseTime.getText().toString().trim();
        String place = upCoursePlace.getText().toString().trim();

        CourseModel courseModel = new CourseModel(courseName, credit, time, place);
        return courseModel;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(ActivityCourseUpdate.this, ActivityCourse.class);
        startActivity(intent);
        return true;
    }
}