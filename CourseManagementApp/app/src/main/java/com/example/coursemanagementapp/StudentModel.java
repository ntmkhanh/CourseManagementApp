package com.example.coursemanagementapp;

public class StudentModel {
    private int idStudent;
    private String nameStudent;
    private String gender;
    private String codeStudent;
    private String birthday;
    private int id_course;

    public StudentModel(String nameStudent, String gender, String codeStudent, String birthday) {
        this.nameStudent = nameStudent;
        this.gender = gender;
        this.codeStudent = codeStudent;
        this.birthday = birthday;
    }

    public StudentModel(String nameStudent, String gender, String codeStudent, String birthday, int id_course) {
        this.nameStudent = nameStudent;
        this.gender = gender;
        this.codeStudent = codeStudent;
        this.birthday = birthday;
        this.id_course = id_course;
    }

    public StudentModel(int idStudent, String nameStudent, String gender, String codeStudent, String birthday, int id_course) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.gender = gender;
        this.codeStudent = codeStudent;
        this.birthday = birthday;
        this.id_course = id_course;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getIdCourse() {
        return id_course;
    }

    public void setIdCourse(int idCourse) {
        this.id_course = idCourse;
    }





}
