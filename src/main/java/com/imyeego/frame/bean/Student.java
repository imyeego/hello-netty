package com.imyeego.frame.bean;

import com.google.gson.annotations.Expose;

public class Student {

    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private int gender;
    @Expose
    private String grade;
    @Expose
    private String classTh;

    private String isUpload;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassTh() {
        return classTh;
    }

    public void setClassTh(String classTh) {
        this.classTh = classTh;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", grade='" + grade + '\'' +
                ", classTh='" + classTh + '\'' +
                ", isUpload='" + isUpload + '\'' +
                '}';
    }
}
