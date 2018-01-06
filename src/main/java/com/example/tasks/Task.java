package com.example.tasks;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Task {

    @Id private String id;

    private String user;
    private String name;
    private Date date;
    private String status;

    public Task() {
    }

    public Task(String user, String name, Date date, String status) {
        this.user  = user;
        this.name = name;
        this.date = (date == null ? new Date() : date);
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!id.equals(task.id)) return false;
        if (!user.equals(task.user)) return false;
        if (!name.equals(task.name)) return false;
        if (date != null ? !date.equals(task.date) : task.date != null) return false;
        return status != null ? status.equals(task.status) : task.status == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
