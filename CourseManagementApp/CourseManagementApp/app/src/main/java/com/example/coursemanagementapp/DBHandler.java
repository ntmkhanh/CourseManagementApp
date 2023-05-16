package com.example.coursemanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHandler extends SQLiteOpenHelper {

    //Tên database
    private static String DATABASE_NAME = "CourseManagement";
    //Bảng Course
    private static String TABLE_COURSE = "course";
    private static String ID_COURSE = "idCourse";
    private static String COURSE_NAME = "courseName";
    private static String CREDITS = "credits";
    private static String TIME = "time";
    private static String PLACE = "place";
    private static int VERSION = 1;

    //Bảng sinh viên
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDENT_NAME = "sudentname";
    private static String GENDER = "gender";
    private static String STUDENT_CODE = "studentcode";
    private static String BIRTHDAY = "brithday";


    //Tạo bảng khóa học
    private String SQLcreateTableCourse = "CREATE TABLE "+ TABLE_COURSE +" ( "+ID_COURSE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COURSE_NAME+" TEXT, "
            +CREDITS+" INTEGER, "
            +TIME+" TEXT, "
            + PLACE+" TEXT) ";

    //Tạo bảng sinh viên
    private String SQLcreateTableStudent = "CREATE TABLE "+ TABLE_STUDENT +" ( "+ID_STUDENT+" integer primary key AUTOINCREMENT, "
            +STUDENT_NAME+" TEXT, "
            +GENDER+" TEXT, "
            +STUDENT_CODE+" TEXT, "
            +BIRTHDAY+" TEXT, "
            +ID_COURSE+" INTEGER , FOREIGN KEY ( "+ ID_COURSE +" ) REFERENCES "+
            TABLE_COURSE+"("+ID_COURSE+"))";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLcreateTableCourse);
        db.execSQL(SQLcreateTableStudent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCourse(CourseModel courseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_NAME, courseModel.getCourseName());
        values.put(CREDITS, courseModel.getNumberOfCredit());
        values.put(TIME, courseModel.getTime());
        values.put(PLACE, courseModel.getPlace());

        db.insert(TABLE_COURSE, null, values);
        db.close();
    }

    public boolean updateCourse(CourseModel upCourse, int idCourse){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_NAME, upCourse.getCourseName());
        values.put(CREDITS, upCourse.getNumberOfCredit());
        values.put(TIME, upCourse.getTime());
        values.put(PLACE, upCourse.getPlace());

        db.update(TABLE_COURSE, values, ID_COURSE+"="+idCourse, null);
        return true;
    }

    public Cursor getDataCourse(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+ TABLE_COURSE, null);
        return cursor;
    }

    public int deleteCourse(int idCourse){
        SQLiteDatabase db = this.getWritableDatabase();
        int res;
        res = db.delete(TABLE_COURSE, ID_COURSE+"="+idCourse, null);
        return res;
    }
    //xoa cac student cuar course da bi xoa
    public int DeleteCourseStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT, ID_COURSE+" = "+ i, null);
        return res;
    }

    public void AddStudent(StudentModel student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getNameStudent());
        values.put(GENDER, student.getGender());
        values.put(STUDENT_CODE, student.getCodeStudent());
        values.put(BIRTHDAY, student.getBirthday());
        values.put(ID_COURSE, student.getIdCourse());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    //lay tat ca sinh vien cua mon hoc do
    public Cursor getDataStudent(int id_course){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(" Select * from " + TABLE_STUDENT + " where " + ID_COURSE + "=" +id_course, null);
        return res;
    }
    //xoa student
    public int DeleteStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT, ID_STUDENT+"="+i, null);
        return res;
    }
    //update student
    public boolean updateStudent(StudentModel student, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getNameStudent());
        values.put(GENDER, student.getGender());
        values.put(STUDENT_CODE, student.getCodeStudent());
        values.put(BIRTHDAY, student.getBirthday());

        db.update(TABLE_STUDENT, values, ID_STUDENT+ " = " + id, null);
        return true;
    }
}
