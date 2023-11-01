package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.Entitlements;
import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class EntitlementsService {
    private final HttpUtilityService httpUtilityService;
    private final Lockfile lockfile;
    private final Entitlements entitlements;

    @Autowired
    public EntitlementsService(HttpUtilityService theHttpUtilityService, Lockfile theLockfile, Entitlements theEntitlements) {
        httpUtilityService = theHttpUtilityService;
        lockfile = theLockfile;
        entitlements = theEntitlements;
    };

    public boolean getEntitlements() {
        // generate url for entitlements
        String port = lockfile.getPort();
        String entitlementsUrl = "https://127.0.0.1:" + port + "/entitlements/v1/token";

        HttpResponse<String> response = httpUtilityService.httpGetRequest(entitlementsUrl, lockfile);

        Gson gson = new Gson();
        String jsonString = "{'accessToken': 'asdasdasd', 'entitlements': '[]','issuer': 'asdasdasd', subject: '123', 'token': 'token'}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonString);

        Entitlements tempEntitlements = gson.fromJson(jsonObject, Entitlements.class);
        entitlements.setAccessToken(tempEntitlements.getAccessToken());
        entitlements.setSubject(tempEntitlements.getSubject());
        entitlements.setToken(tempEntitlements.getToken());

//        if (response != null) {
//            Entitlements tempEntitlements = gson.fromJson(jsonObject, Entitlements.class);
//            entitlements.setAccessToken(tempEntitlements.getAccessToken());
//            entitlements.setSubject(tempEntitlements.getSubject());
//            entitlements.setToken(tempEntitlements.getToken());
//        }

        return response != null;
    }
}
