package com.example.tasks;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Task {

    @Id private String id;

    private String name;
    private Date date;
    private String status;

    public Task() {
    }

    public Task(String name, Date date, String status) {
        this.name = name;
        this.date = (date == null ? new Date() : date);
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
