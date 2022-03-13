package com.example.demo.model.data;

import java.util.UUID;

public class Data
{
    private UUID id;
    private String name;
    private Birthday birthday;
    private String bio;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Data(UUID id, String name, String _birthday, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        String[] dmy = _birthday.split("/");
        int d= new Integer(dmy[0]),m= new Integer(dmy[1]),y=new Integer(dmy[2]);
        this.birthday = new Birthday(d,m,y);
    }
}

class Birthday
{
    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Birthday(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
