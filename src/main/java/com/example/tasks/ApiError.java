package com.example.tasks;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiError {

    private int code;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;
    private String message;
    private String path;

    private ApiError() {
        date = new Date();
    }

    ApiError(HttpStatus status) {
        this();
        this.code = status.value();
        this.status = status.getReasonPhrase();
    }

    ApiError(HttpStatus status, String message, String path) {
        this(status);
        this.message = message;
        this.path = path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
