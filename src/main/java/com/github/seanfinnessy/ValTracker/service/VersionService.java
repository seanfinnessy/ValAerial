package com.github.seanfinnessy.ValTracker.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.seanfinnessy.ValTracker.entity.Version;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;

public class VersionService {
    private final HttpUtilityService httpUtilityService;

    @Autowired
    public VersionService(HttpUtilityService httpUtilityService) {
        this.httpUtilityService = httpUtilityService;
    }

    public String getClientVersion(String url) {
        HttpResponse<String> response = httpUtilityService.httpGet3rdPartyRequest(url);

        Gson gson = new GsonBuilder().create();

        Version version = gson.fromJson(response.body(), Version.class);
        return version.getData().getRiotClientVersion();
    }
}
