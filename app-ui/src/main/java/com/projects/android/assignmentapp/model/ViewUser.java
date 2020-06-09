package com.projects.android.assignmentapp.model;

public class ViewUser {

    private int id;
    private String name;
    private String jobTitle;
    private int age;
    private int gender;

    public ViewUser(String name, String jobTitle, int age, int gender) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.age = age;
        this.gender = gender;
    }

    public ViewUser(int id, String name, String jobTitle, int age, int gender) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
