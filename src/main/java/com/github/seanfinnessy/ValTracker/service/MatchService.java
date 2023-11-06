package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.ClientRegion;
import com.github.seanfinnessy.ValTracker.entity.Entitlements;
import com.github.seanfinnessy.ValTracker.entity.MatchHistory;
import com.github.seanfinnessy.ValTracker.entity.MatchStats;
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
    private final MatchStats matchStats;

    @Autowired
    public MatchService(HttpUtilityService httpUtilityService, Entitlements entitlements, ClientRegion clientRegion, MatchHistory matchHistory, MatchStats matchStats) {
        this.httpUtilityService = httpUtilityService;
        this.entitlements = entitlements;
        this.clientRegion = clientRegion;
        this.matchHistory = matchHistory;
        this.matchStats = matchStats;
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
            for (MatchHistory.Match match: matchHistory.getHistory()) {
                System.out.println(getMatchResults(match));
            }

        }
        return response != null;
    }

    private MatchHistory.Match getMatchResults(MatchHistory.Match match) {
        // generate url for match specifics
        String shard = clientRegion.getRegion();
        String matchId = match.getMatchID();
        String matchUrl = "https://pd."+shard+".a.pvp.net/match-details/v1/matches/"+matchId;
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(matchUrl, entitlements);
        Gson gson = new GsonBuilder()
                .create();
        if (response != null) {
            MatchStats tempMatchStats = gson.fromJson(response.body(), MatchStats.class);
            matchStats.setMatchInfo(tempMatchStats.getMatchInfo());
        }
    }
}
