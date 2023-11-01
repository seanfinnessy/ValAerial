package com.github.seanfinnessy.ValTracker.entity;

import org.springframework.stereotype.Component;

@Component
public class Entitlements {
    private String accessToken;

    private String subject; // player uuid
    private String token; // used as entitlement in requests

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Entitlements{" +
                "accessToken='" + accessToken + '\'' +
                ", subject='" + subject + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
