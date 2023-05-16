package com.example.coursemanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityAddStudent extends AppCompatActivity {
    EditText studentName, studentCode, birthday;
    DatePicker edtdate;
    RadioButton btnMale, btnFemale;
    Button btnAddStudent;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        btnAddStudent = findViewById(R.id.btnStudentAdd);
        studentName = findViewById(R.id.editTextStudentName);
        studentCode = findViewById(R.id.editTextStudentCode);
        //birthday = findViewById(R.id.editTextBrithday);
        btnMale = findViewById(R.id.radioBtnMale);
        btnFemale = findViewById(R.id.radioBtnFemale);
        edtdate = findViewById(R.id.txtDate);

        //lay id course
        Intent intent = getIntent();
        int id_course = intent.getIntExtra("id_course", 0);

        dbHandler = new DBHandler(this);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = studentName.getText().toString().trim();
                String gender ="";
                if(btnMale.isChecked()){
                    gender="Male";
                } else if(btnFemale.isChecked()){
                    gender="Female";
                }
                String code = studentCode.getText().toString().trim();
               // String birth = birthday.getText().toString().trim();
                int date = edtdate.getDayOfMonth();
                int month = edtdate.getMonth()+1;
                int year = edtdate.getYear();
                String stdate, stmonth, styear;
                if (date < 10)
                    stdate = "0"+String.valueOf(date);
                else stdate = String.valueOf(date);
                if (month < 10)
                    stmonth = "0"+String.valueOf(month);
                else stmonth = String.valueOf(month);
                styear = String.valueOf(year);
                String biday = stdate+"/" +stmonth + "/"+ styear;

                if(name.equals("") || code.equals("") || gender.equals("")){
                    Toast.makeText(ActivityAddStudent.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
                } else{
                    StudentModel student = CreateStudent(id_course);
                    dbHandler.AddStudent(student);
                    Intent intent = new Intent(ActivityAddStudent.this, ActivityStudent.class);
                    intent.putExtra("id_course", id_course);
                    startActivity(intent);

                    Toast.makeText(ActivityAddStudent.this, "Add Student Success", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private StudentModel CreateStudent(int id_course){
        String name = studentName.getText().toString().trim();
        String gender = "";
        if(btnMale.isChecked()){
            gender="Male";
        } else if(btnFemale.isChecked()){
            gender="Female";
        }
        String code = studentCode.getText().toString().trim();
        //String birth = birthday.getText().toString().trim();
        int date = edtdate.getDayOfMonth();
        int month = edtdate.getMonth()+1;
        int year = edtdate.getYear();
        String stdate, stmonth, styear;
        if (date < 10)
            stdate = "0"+String.valueOf(date);
        else stdate = String.valueOf(date);
        if (month < 10)
            stmonth = "0"+String.valueOf(month);
        else stmonth = String.valueOf(month);
        styear = String.valueOf(year);
        String biday = stdate+"/" +stmonth + "/"+ styear;
        StudentModel student = new StudentModel(name, code, biday, gender, id_course);
        return student;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(ActivityAddStudent.this, ActivityStudent.class);
        startActivity(intent);
        return true;
    }
}