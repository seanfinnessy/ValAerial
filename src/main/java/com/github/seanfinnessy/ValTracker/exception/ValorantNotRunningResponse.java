package com.github.seanfinnessy.ValTracker.exception;

public class ValorantNotRunningResponse {
    private int status;
    private String timestamp;
    private String message;

    public ValorantNotRunningResponse() {};

    public ValorantNotRunningResponse(int status, String timestamp, String message) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
