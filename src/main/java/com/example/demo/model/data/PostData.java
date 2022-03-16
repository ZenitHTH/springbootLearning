package com.example.demo.model.data;

import java.time.LocalDate;
import java.util.UUID;

public class PostData {

    private String username;
    private String bio;
    private LocalDate birthday;



    public PostData(String username, String bio,LocalDate birthday) {
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}
