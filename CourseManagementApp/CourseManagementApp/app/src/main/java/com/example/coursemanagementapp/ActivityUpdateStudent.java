package com.example.coursemanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActivityUpdateStudent extends AppCompatActivity {
    EditText upStudentName, upStudentCode, upStudentBirthday;
    RadioButton btnMale, btnFeMale;
    Button btnUpdateStudent;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        upStudentName = findViewById(R.id.editTextStudentName);
        upStudentCode = findViewById(R.id.editTextStudentCode);
        upStudentBirthday = findViewById(R.id.editTextBrithday);
        btnMale = findViewById(R.id.radioBtnMale);
        btnFeMale = findViewById(R.id.radioBtnFemale);
        btnUpdateStudent = findViewById(R.id.btnStudentUpdate);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        int id_course = intent.getIntExtra("id_course", 0);

        //gan gia tri leen edittext
        upStudentName.setText(name);
        upStudentCode.setText(code);
        upStudentBirthday.setText(birthday);
        if(gender.equals("Male")){
            btnMale.setChecked(true);
            btnFeMale.setChecked(false);
        } else {
            btnMale.setChecked(false);
            btnFeMale.setChecked(true);
        }

        dbHandler = new DBHandler(this);

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = upStudentName.getText().toString().trim();
                String code = upStudentCode.getText().toString().trim();
                String birthday = upStudentBirthday.getText().toString().trim();

                StudentModel student = updateStudent();
                if(name.equals("") || code.equals("") || birthday.equals("")){
                    Toast.makeText(ActivityUpdateStudent.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
                } else{
                    dbHandler.updateStudent(student, id);

                    Intent intent = new Intent(ActivityUpdateStudent.this, ActivityStudent.class);

                    intent.putExtra("id_course", id_course);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateStudent.this, "Add Student Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private StudentModel updateStudent() {
        String name = upStudentName.getText().toString().trim();
        String code = upStudentCode.getText().toString().trim();
        String birthday = upStudentBirthday.getText().toString().trim();
        String gender = "";
        if (btnMale.isChecked()) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        StudentModel student = new StudentModel(name, gender, code, birthday);
        return student;
    }
}