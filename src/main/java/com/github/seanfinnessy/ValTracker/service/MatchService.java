package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.ClientRegion;
import com.github.seanfinnessy.ValTracker.entity.Entitlements;
import com.github.seanfinnessy.ValTracker.entity.MatchHistory;
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
    private final MatchHistory matchHistory;

    @Autowired
    public MatchService(HttpUtilityService httpUtilityService, Entitlements entitlements, ClientRegion clientRegion, MatchHistory matchHistory) {
        this.httpUtilityService = httpUtilityService;
        this.entitlements = entitlements;
        this.clientRegion = clientRegion;
        this.matchHistory = matchHistory;
    }

    public boolean getMatchHistory(int startIndex, int endIndex) {
        // generate url for match history
        String shard = clientRegion.getRegion();
        String puuid = entitlements.getSubject();
        String queue = "competitive";

        String matchHistoryUrl = "https://pd."+shard+".a.pvp.net/match-history/v1/history/"+puuid+"?startIndex="+startIndex+"&endIndex="+endIndex+"&queue="+queue;
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(matchHistoryUrl, entitlements);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        if (response != null) {
            MatchHistory tempMatchHistory = gson.fromJson(response.body(), MatchHistory.class);
            matchHistory.setSubject(tempMatchHistory.getSubject());
            matchHistory.setTotal(tempMatchHistory.getTotal());
            matchHistory.setHistory(tempMatchHistory.getHistory());
            System.out.println(matchHistory);
        }
        return response != null;
    }
}
