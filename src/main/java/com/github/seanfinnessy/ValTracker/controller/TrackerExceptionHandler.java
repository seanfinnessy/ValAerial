package com.github.seanfinnessy.ValTracker.controller;

import com.github.seanfinnessy.ValTracker.exception.ValorantNotRunningResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class TrackerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ValorantNotRunningResponse> handleException(Exception e) {

        // create a valorant not running response
        ValorantNotRunningResponse error = new ValorantNotRunningResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
