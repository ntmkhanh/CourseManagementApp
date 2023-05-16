package com.example.coursemanagementapp;

public class CourseModel {
    private int id;
    private String courseName;
    private int numberOfCredit;
    private String time;
    private String place;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public CourseModel(String courseName, int numberOfCredit, String time, String place) {
        this.courseName = courseName;
        this.numberOfCredit = numberOfCredit;
        this.time = time;
        this.place = place;
    }

    public CourseModel(int id, String courseName, int numberOfCredit, String time, String place) {
        this.id = id;
        this.courseName = courseName;
        this.numberOfCredit = numberOfCredit;
        this.time = time;
        this.place = place;
    }

}