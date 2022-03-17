package com.example.demo.model.data;

import java.time.LocalDate;
import java.util.UUID;


public class PostData {

    private String username;
    private String bio;
    private String birthday;

    public PostData(String username, String bio,String birthday) {
        this.username = username;
        this.bio = bio;
        this.birthday = birthday;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "PostData{" +
                "username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
