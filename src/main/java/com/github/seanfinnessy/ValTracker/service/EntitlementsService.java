package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class EntitlementsService {
    private final HttpUtilityService httpUtilityService;

    @Autowired
    public EntitlementsService(HttpUtilityService theHttpUtilityService) {
        httpUtilityService = theHttpUtilityService;
    };

    public boolean getEntitlements(Lockfile lockfile) {
        // generate url for entitlements
        String port = lockfile.getPort();
        String entitlementsUrl = "https://127.0.0.1:" + port + "/entitlements/v1/token";

        HttpResponse<String> response = httpUtilityService.httpGetRequest(entitlementsUrl, lockfile);

        System.out.println("Entitlements: " + response);
        return response != null;
    }
}
