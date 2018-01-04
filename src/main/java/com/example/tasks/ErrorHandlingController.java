package com.example.tasks;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class ErrorHandlingController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public ResponseEntity<Object> handleNotFound() {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage("Not found");
        apiError.setDebugMessage("Original URI not found");
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
