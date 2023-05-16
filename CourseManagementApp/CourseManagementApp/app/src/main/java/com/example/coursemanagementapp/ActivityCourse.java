package com.example.coursemanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityCourse extends AppCompatActivity {
    private Toolbar tbarCourse;
    private ListView listviewCourse;
    private ArrayList<CourseModel> arrayListCourse;
    private DBHandler dbHandler;
    private AdapterCourse adapterCourse;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);


        //listviewCourse = findViewById(R.id.delete);
        tbarCourse = findViewById(R.id.tolbarCourse);
        listviewCourse = findViewById(R.id.listViewCourse);

        //setSupportActionBar(tbarCourse);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHandler = new DBHandler(this);

        arrayListCourse = new ArrayList<>();

        Cursor cursor = dbHandler.getDataCourse();
        registerForContextMenu(listviewCourse);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nameCourse = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            arrayListCourse.add(new CourseModel(id, nameCourse, credit, time, place));
        }
        adapterCourse = new AdapterCourse(ActivityCourse.this, arrayListCourse);
        listviewCourse.setAdapter(adapterCourse);
        cursor.moveToFirst();
        cursor.close();

        //su kien click vao item course
        listviewCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityCourse.this, ActivityStudent.class);
                int id_course = arrayListCourse.get(position).getId();
                //
                intent.putExtra("id_course", id_course);
                startActivity(intent);
            }

        });

    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        //MenuInflater inflater = getMenuInflater();
//        getMenuInflater().inflate(R.menu.context_menu, menu);
//    }


//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//
//        switch(item.getItemId()){
//            case R.id.delete:
//                dbHandler = new DBHandler(ActivityCourse.this);
//                //xoa course
//                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//                CourseModel courseModel = listviewCourse.get(menuInfo.position);
//
//                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ActivityCourse.this, ActivityCourse.class);
//                startActivity(intent);
//                return true;
//            case R.id.update:
//                Toast.makeText(this,"Update", Toast.LENGTH_SHORT).show();
//
//        }
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.munuadd:
                Intent intentaddst = new Intent(ActivityCourse.this, ActivityAddCourse.class);
                startActivity(intentaddst);
                break;
            default:
                Intent intent = new Intent(ActivityCourse.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void information(final int pos){
        Cursor cursor = dbHandler.getDataCourse();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == pos){
                Intent intentinfo = new Intent(ActivityCourse.this, ActivityCourseInfo.class);

                intentinfo.putExtra("id", id);
                String name = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intentinfo.putExtra("name", name);
                intentinfo.putExtra("credit", credit);
                intentinfo.putExtra("time", time);
                intentinfo.putExtra("place", place);

                startActivity(intentinfo);
            }
        }
    }

    public void delete(final int pos){
            dbHandler.deleteCourse(pos);
            dbHandler.DeleteCourseStudent(pos);
            Toast.makeText(this, "Course Deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ActivityCourse.this, ActivityCourse.class);
            startActivity(intent);

    }

    public void update(final int pos){
        Cursor cursor = dbHandler.getDataCourse();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == pos){
                Intent intent = new Intent(ActivityCourse.this, ActivityCourseUpdate.class);
                intent.putExtra("id", id);

                String name = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);
                //gui du lieu qua activity
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("credit", credit);
                intent.putExtra("time", time);
                intent.putExtra("place", place);

                startActivity(intent);
            }
        }
    }
}