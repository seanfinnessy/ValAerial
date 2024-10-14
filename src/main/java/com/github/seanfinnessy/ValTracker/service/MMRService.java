package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.ClientRegion;
import com.github.seanfinnessy.ValTracker.entity.Entitlements;
import com.github.seanfinnessy.ValTracker.model.Rank;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class MMRService {
    private final ClientRegion clientRegion;
    private final Entitlements entitlements;
    private final HttpUtilityService httpUtilityService;

    public MMRService(ClientRegion clientRegion,
                      Entitlements entitlements,
                      HttpUtilityService httpUtilityService
    ) {
        this.clientRegion = clientRegion;
        this.entitlements = entitlements;
        this.httpUtilityService = httpUtilityService;
    }

    public String getPlayerRank(String puuid, String seasonId) {
        String shard = clientRegion.getRegion().toLowerCase();
        String url = "https://pd." + shard + ".a.pvp.net/mmr/v1/players/" + puuid;
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(url, entitlements);
        if (response != null && response.statusCode() == 200) {
            int rank = JsonPath.read(response.body(), "$.QueueSkills.competitive.SeasonalInfoBySeasonID." + seasonId + ".Rank");
            return Rank.getRankByValue(rank).getDisplayName();
        } else {
            // Handle the case where the response is null or the status code isn't 200
            System.err.println("Failed to fetch data from API");
            return Rank.getRankByValue(0).getDisplayName();
        }
    }
}
