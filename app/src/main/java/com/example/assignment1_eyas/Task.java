package com.example.assignment1_eyas;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private String name;
    private String date;

    private byte status;
    private String note;


    public Task(String name, String date, String note) {
        this.name = name;
        this.date = date;
        this.status = 0;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return (String) this.name;
    }
}
