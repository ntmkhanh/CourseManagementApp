package com.example.coursemanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityUpdateStudent extends AppCompatActivity {
    EditText upStudentName, upStudentCode, upStudentBirthday;
    RadioButton btnMale, btnFeMale;
    Button btnUpdateStudent;
    DBHandler dbHandler;
    DatePicker edtdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        upStudentName = findViewById(R.id.editTextStudentName);
        upStudentCode = findViewById(R.id.editTextStudentCode);
        upStudentBirthday = findViewById(R.id.editTextBrithday);
        //edtdate = findViewById(R.id.txtDate);
        btnMale = findViewById(R.id.radioBtnMale);
        btnFeMale = findViewById(R.id.radioBtnFemale);
        btnUpdateStudent = findViewById(R.id.btnStudentUpdate);


        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        //int idstu = intent.getIntExtra("idstudent", 0);
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        int id_course = intent.getIntExtra("id_course", 0);
        // String biday = intent.getStringExtra("birthday");
        //System.out.println("Biday: " + biday );
//        int id_course = intent.getIntExtra("id_course", 0);
//        System.out.println("idcourse: "+ String.valueOf(id_course));

        //gan gia tri leen edittext
        upStudentName.setText(name);
        upStudentCode.setText(code);
        if(gender.equals("Male")){
            btnMale.setChecked(true);
            btnFeMale.setChecked(false);
        } else {
            btnMale.setChecked(false);
            btnFeMale.setChecked(true);
        }

//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");;
//        try {
//            Date date = format.parse(biday);
//            edtdate.init(date.getYear()+1900,date.getMonth(), date.getDate(),null);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        dbHandler = new DBHandler(this);

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = upStudentName.getText().toString().trim();
                String code = upStudentCode.getText().toString().trim();
                String birthday = upStudentBirthday.getText().toString().trim();
//                int date = edtdate.getDayOfMonth();
//                int month = edtdate.getMonth()+1;
//                int year = edtdate.getYear();
//                String stdate, stmonth, styear;
//                if (date < 10)
//                    stdate = "0"+String.valueOf(date);
//                else stdate = String.valueOf(date);
//                if (month < 10)
//                    stmonth = "0"+String.valueOf(month);
//                else stmonth = String.valueOf(month);
//                styear = String.valueOf(year);
//                String biday = stdate+"/" +stmonth + "/"+ styear;
//
                StudentModel student = updateStudent();
                if(name.equals("") || code.equals("")){
                    Toast.makeText(ActivityUpdateStudent.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
                } else{
                    dbHandler.updateStudent(student, id);

                    //StudentModel student = new StudentModel(name, gender, code, biday);
                    Intent intent = new Intent(ActivityUpdateStudent.this, ActivityStudent.class);
                    intent.putExtra("id_course", id_course);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateStudent.this, "Update Student Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private StudentModel updateStudent() {
        String name = upStudentName.getText().toString().trim();
        String code = upStudentCode.getText().toString().trim();
        //String birthday = upStudentBirthday.getText().toString().trim();
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
        System.out.println("The upt date: " + biday);
        String gender = "";
        if (btnMale.isChecked()) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        StudentModel student = new StudentModel(name, gender, code, biday);
        return student;
    }
}