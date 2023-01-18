package com.example.demospringmvc.controller;


import java.util.Date;

import javax.validation.constraints.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Student {    @NotEmpty(message="Ten khong dc de trong")
    @Size(min=3, max=20, message="Name length must be b/w 3 and 20")
    private String name;

    @Min(value=18, message="Age must be over 18")
    private int age;

    @Pattern(regexp="^[0-9]{10,11}$", message="Phone must be sequence of 10-11 digits")
    private String phone;

    @Past(message="The first day in school must be in the past")
    private Date firstDayInSchool;



    public Student(String name, int age, String phone, Date firstDayInSchool){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.firstDayInSchool = firstDayInSchool;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getFirstDayInSchool() {
        return firstDayInSchool;
    }

    public void setFirstDayInSchool(Date firstDayInSchool) {
        this.firstDayInSchool = firstDayInSchool;
    }

}