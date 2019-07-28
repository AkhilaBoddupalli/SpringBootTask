package com.stackroute.muzixassignment.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
public class Track {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String comments;

    public Track(int id,String name,String comments) {
        this.id = id;
        this.name = name;
        this.comments = comments;
    }

    public Track() {
    }


    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                '}';
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
