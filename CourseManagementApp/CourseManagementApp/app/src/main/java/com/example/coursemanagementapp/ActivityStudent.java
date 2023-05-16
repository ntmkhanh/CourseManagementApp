package com.example.coursemanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityStudent extends AppCompatActivity {
    private Toolbar tbarStudent;
    private ListView listviewStudent;
    private ArrayList<StudentModel> arrayListStudent;
    private DBHandler dbHandler;
    private AdapterStudent adapterStudent;
    int count = 0;
    int id_course = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tbarStudent = findViewById(R.id.tolbarStudent);
        listviewStudent = findViewById(R.id.listViewStudent);

        Intent intent = getIntent();
        id_course = intent.getIntExtra("id_course", 0);

        //setSupportActionBar(tbarStudent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHandler = new DBHandler(this);

        arrayListStudent = new ArrayList<>();
        arrayListStudent.clear();

        Cursor cursor = dbHandler.getDataStudent(id_course);
        while (cursor.moveToNext()) {
            int id_course = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String code = cursor.getString(2);
            String gender = cursor.getString(3);
            String birthday = cursor.getString(4);

            arrayListStudent.add(new StudentModel(id, name, gender, code, birthday, id_course));
        }
        adapterStudent = new AdapterStudent(ActivityStudent.this, arrayListStudent);
        //hien thi listview
        listviewStudent.setAdapter(adapterStudent);
        cursor.moveToFirst();
        cursor.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.munuaddstudent:
                Intent intentaddst = new Intent(ActivityStudent.this, ActivityAddStudent.class);
                intentaddst.putExtra("id_course", id_course);
                startActivity(intentaddst);
                break;
            default:
                Intent intent = new Intent(ActivityStudent.this, ActivityCourse.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void infomation(final int pos){
        Cursor cursor = dbHandler.getDataStudent(id_course);
        while ((cursor.moveToNext())){
            int id = cursor.getInt(0);
            if(id == pos){
                Intent intent = new Intent(ActivityStudent.this, ActivityStudentInfo.class);
                intent.putExtra("id", pos);
                String name = cursor.getString(1);
                String code = cursor.getString(2);
                String birthday = cursor.getString(3);
                String gender = cursor.getString(4);
                int id_course = cursor.getInt(5);

                intent.putExtra("name", name);
                intent.putExtra("gender", gender);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                intent.putExtra("id_course", id_course);

                startActivity(intent);
            }
        }
    }

    public void delete(final int pos){
        dbHandler.DeleteStudent(pos);
        dbHandler.DeleteCourseStudent(pos);
        Toast.makeText(this, "Course Deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ActivityStudent.this, ActivityStudent.class);
        intent.putExtra("id_course", id_course);
        startActivity(intent);

    }

    public void updateStudent(final int pos){
        Cursor cursor = dbHandler.getDataStudent(id_course);

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == pos){
                Intent intent = new Intent(ActivityStudent.this, ActivityUpdateStudent.class);
                intent.putExtra("id", id_course);
                String name = cursor.getString(1);
                String code = cursor.getString(2);
                String birthday = cursor.getString(3);
                String gender = cursor.getString(4);
                int id_course = cursor.getInt(5);

                intent.putExtra("name", name);
                intent.putExtra("gender", gender);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);

                startActivity(intent);

            }
        }
        cursor.close();
    }

}