package com.github.seanfinnessy.ValTracker.exception;

public class ValorantNotRunningException extends RuntimeException {

    public ValorantNotRunningException(String message) {
        super(message);
    }

    public ValorantNotRunningException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValorantNotRunningException(Throwable cause) {
        super(cause);
    }
}
