package com.example.PP;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class PP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private String createdBy;
    private String text;
//    private String originCountry;
    private Timestamp dateCreated;

    public PP(){
        this.dateCreated = new Timestamp(new Date().getTime());
    }


    public PP(int id, String createdBy, String text, String originCountry) {
        this.id = id;
//        this.createdBy = createdBy;
        this.text = text;
//        this.originCountry = originCountry;
        this.dateCreated = new Timestamp(new Date().getTime());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}