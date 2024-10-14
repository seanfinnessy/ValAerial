package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.*;
import com.github.seanfinnessy.ValTracker.responses.MatchApiResponse;
import com.github.seanfinnessy.ValTracker.responses.MatchIdApiResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class MatchService {
    private final HttpUtilityService httpUtilityService;
    private final Entitlements entitlements;
    private final ClientRegion clientRegion;
    private final Gson gson;

    @Autowired
    public MatchService(HttpUtilityService httpUtilityService, Entitlements entitlements, ClientRegion clientRegion) {
        this.httpUtilityService = httpUtilityService;
        this.entitlements = entitlements;
        this.clientRegion = clientRegion;
        this.gson = new Gson();
    }

    // ------------------------------ CURRENT MATCH ------------------------------

    public MatchApiResponse getCurrentMatch(String matchId) {
        String shard = clientRegion.getRegion().toLowerCase();
        String currentMatchUrl = "https://glz-" + shard + "-1." + shard + ".a.pvp.net/core-game/v1/matches/" + matchId;
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(currentMatchUrl, entitlements);
        if (response != null && response.statusCode() == 200) {
            // Parse JSON response using Gson
            return gson.fromJson(response.body(), MatchApiResponse.class);
        } else {
            // Handle the case where the response is null or the status code isn't 200
            System.err.println("Failed to fetch data from API");
            return null;
        }
    }
}
