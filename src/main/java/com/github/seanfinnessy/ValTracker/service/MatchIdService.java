package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.ClientRegion;
import com.github.seanfinnessy.ValTracker.entity.Entitlements;
import com.github.seanfinnessy.ValTracker.entity.UserSession;
import com.github.seanfinnessy.ValTracker.responses.MatchIdApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@Service
public class MatchIdService {
    private final ClientRegion clientRegion;
    private final Entitlements entitlements;
    private final HttpUtilityService httpUtilityService;
    private final Gson gson;


    public MatchIdService(ClientRegion clientRegion,
                          Entitlements entitlements,
                          HttpUtilityService httpUtilityService
                          ) {
        this.clientRegion = clientRegion;
        this.entitlements = entitlements;
        this.httpUtilityService = httpUtilityService;
        this.gson = new Gson();
    }



    public MatchIdApiResponse getCurrentMatchId() {
        String shard = clientRegion.getRegion().toLowerCase();
        String puuid = entitlements.getSubject();
        String currentMatchIdUrl = "https://glz-" + shard + "-1." + shard + ".a.pvp.net/core-game/v1/players/" + puuid;
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(currentMatchIdUrl, entitlements);
        if (response != null && response.statusCode() == 200) {
            // Parse JSON response using Gson
            return gson.fromJson(response.body(), MatchIdApiResponse.class);
        } else {
            // Handle the case where the response is null or the status code isn't 200
            System.err.println("Failed to fetch data from API");
            return null;
        }

    }
}
