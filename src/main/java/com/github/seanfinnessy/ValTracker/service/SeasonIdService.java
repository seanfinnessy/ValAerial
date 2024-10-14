package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.ClientRegion;
import com.github.seanfinnessy.ValTracker.entity.Entitlements;
import com.github.seanfinnessy.ValTracker.responses.SeasonIdApiResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class SeasonIdService {
    private final ClientRegion clientRegion;
    private final Entitlements entitlements;
    private final HttpUtilityService httpUtilityService;
    private final Gson gson;

    public SeasonIdService(ClientRegion clientRegion,
                           Entitlements entitlements,
                           HttpUtilityService httpUtilityService
    ) {
        this.clientRegion = clientRegion;
        this.entitlements = entitlements;
        this.httpUtilityService = httpUtilityService;
        this.gson = new Gson();
    }

    public SeasonIdApiResponse getSeasonId() {
        String shard = clientRegion.getRegion().toLowerCase();
        String url = "https://shared." + shard + ".a.pvp.net/content-service/v3/content";
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(url, entitlements);
        if (response != null && response.statusCode() == 200) {
            // Parse JSON response using Gson
            return gson.fromJson(response.body(), SeasonIdApiResponse.class);
        } else {
            // Handle the case where the response is null or the status code isn't 200
            System.err.println("Failed to fetch data from API");
            return null;
        }
    }
}
