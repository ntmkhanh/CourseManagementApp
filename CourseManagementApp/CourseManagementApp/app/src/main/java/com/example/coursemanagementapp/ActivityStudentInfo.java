package com.example.coursemanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityStudentInfo extends AppCompatActivity {
    TextView txtName, txtGender, txtCode, txtBirthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_student);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtName = findViewById(R.id.txtStudentName);
        txtGender = findViewById(R.id.txtGender);
        txtCode = findViewById(R.id.txtStudentCode);
        txtBirthday = findViewById(R.id.txtBirthday);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");


        txtName.setText(name);
        txtGender.setText(gender);
        txtCode.setText(code);
        txtBirthday.setText(birthday);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(ActivityStudentInfo.this, ActivityStudent.class);
        //intent.putExtra("id_course", id_course);
        startActivity(intent);
        return true;
    }
}