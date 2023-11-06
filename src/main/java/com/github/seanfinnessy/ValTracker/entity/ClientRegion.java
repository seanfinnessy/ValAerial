package com.github.seanfinnessy.ValTracker.entity;

import org.springframework.stereotype.Component;

@Component
public class ClientRegion {
    private String locale;
    private String region;
    private String webLanguage;
    private String webRegion;


    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getWebLanguage() {
        return webLanguage;
    }

    public void setWebLanguage(String webLanguage) {
        this.webLanguage = webLanguage;
    }

    public String getWebRegion() {
        return webRegion;
    }

    public void setWebRegion(String webRegion) {
        this.webRegion = webRegion;
    }

    @Override
    public String toString() {
        return "ClientRegion{" +
                "locale='" + locale + '\'' +
                ", region='" + region + '\'' +
                ", webLanguage='" + webLanguage + '\'' +
                ", webRegion='" + webRegion + '\'' +
                '}';
    }
}