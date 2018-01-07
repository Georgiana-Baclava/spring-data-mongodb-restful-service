package com.example.tasks;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Task {

    @Id private String id;

    private String user;
    private String name;
    private Date createdDate;
    private Date endDate;
    private Long duration;
    private String status;

    public Task() {
    }

    public Task(String user, String name, Long createdDate, Long endDate, Long duration, String status) {
        this.user  = user;
        this.name = name;
        this.createdDate = (createdDate == null ? new Date(System.currentTimeMillis()) : new Date(createdDate));
        this.endDate = (endDate == null ? new Date(System.currentTimeMillis()) : new Date(endDate));
        this.duration = duration;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!id.equals(task.id)) return false;
        if (!user.equals(task.user)) return false;
        if (!name.equals(task.name)) return false;
        if (createdDate != null ? !createdDate.equals(task.createdDate) : task.createdDate != null)
            return false;
        if (endDate != null ? !endDate.equals(task.endDate) : task.endDate != null) return false;
        if (duration != null ? !duration.equals(task.duration) : task.duration != null)
            return false;
        return status != null ? status.equals(task.status) : task.status == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                '}';
    }
}
