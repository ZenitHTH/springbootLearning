package com.example.demo.model.data;

import com.example.demo.model.users.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="DataTable")
public class Data{

    @javax.persistence.Id
    @Column(name = "id",nullable = false)
    private long id;
    @SequenceGenerator(
            name = "dataTable",
            sequenceName = "dataTable",
            allocationSize =50
    )

    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "dataTable"
    )
    @Column(name="UUID")
    private UUID uuid;
    @Column(name="Name")
    private String name;
    @Column(name="BirtDay")
    private LocalDate birthday;
    @Column(name="Bio")
    private String bio;

    public Data() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Data(Long id,UUID uuid, String name, LocalDate _birthday, String bio) {
        this.uuid = uuid;
        this.name = name;
        this.bio = bio;
        this.birthday = _birthday;
        this.id = id;
    }
}
