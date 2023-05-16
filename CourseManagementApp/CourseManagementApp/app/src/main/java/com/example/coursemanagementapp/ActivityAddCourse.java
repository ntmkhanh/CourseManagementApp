package com.example.coursemanagementapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class ActivityAddCourse extends AppCompatActivity {
    EditText courseName, courseCredit, courseTime, coursePlace;
    Button btnAddCourse;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        btnAddCourse = findViewById(R.id.btnCourseAdd);
        courseName = findViewById(R.id.editTextCourseName);
        courseCredit = findViewById(R.id.editTextCredit);
        courseTime = findViewById(R.id.editTextTime);
        coursePlace = findViewById(R.id.editTextPlace);
        dbHandler = new DBHandler(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = courseName.getText().toString().trim();
                String creadit = courseCredit.getText().toString().trim();
                String time = courseTime.getText().toString().trim();
                String place = coursePlace.getText().toString().trim();

                if (name.isEmpty() &&
                        creadit.equals("") &&
                        time.isEmpty() &&
                        place.isEmpty()) {
                    Toast.makeText(ActivityAddCourse.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    CourseModel courseModel = CreateCourse();
                    dbHandler.addCourse(courseModel);
                    //add thanh cong chuyen qua giao dien course
                        Intent intentback = new Intent(ActivityAddCourse.this, ActivityCourse.class);
                        startActivity(intentback);

                    Toast.makeText(getApplicationContext(),"Course saved",Toast.LENGTH_SHORT).show();
                }
                //

            }
        });
    }


    private CourseModel CreateCourse(){
        String name = courseName.getText().toString().trim();
        int creadit = Integer.parseInt(courseCredit.getText().toString().trim());
        String time = courseTime.getText().toString().trim();
        String place = coursePlace.getText().toString().trim();



        CourseModel courseModel = new CourseModel(name, creadit, time, place);
        return courseModel;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(ActivityAddCourse.this, ActivityCourse.class);
        startActivity(intent);
        return true;
    }
}