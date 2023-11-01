package com.github.seanfinnessy.ValTracker.entity;

import org.springframework.stereotype.Component;

@Component
public class Entitlements {
    private String accessToken;
    private String jsonWebToken;
    private String loggedInPlayerUuid;

    public Entitlements() {};

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getJsonWebToken() {
        return jsonWebToken;
    }

    public void setJsonWebToken(String jsonWebToken) {
        this.jsonWebToken = jsonWebToken;
    }

    public String getLoggedInPlayerUuid() {
        return loggedInPlayerUuid;
    }

    public void setLoggedInPlayerUuid(String loggedInPlayerUuid) {
        this.loggedInPlayerUuid = loggedInPlayerUuid;
    }

    @Override
    public String toString() {
        return "EntitlementsService{" +
                "accessToken='" + accessToken + '\'' +
                ", jsonWebToken='" + jsonWebToken + '\'' +
                ", loggedInPlayerUuid='" + loggedInPlayerUuid + '\'' +
                '}';
    }
}
