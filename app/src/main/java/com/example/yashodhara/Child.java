package com.example.yashodhara;

public class Child {
    private String name;

    public Child(String name, String dob, String gender, double age) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    private String dob;
    private  String gender;
    private  double age;
}
