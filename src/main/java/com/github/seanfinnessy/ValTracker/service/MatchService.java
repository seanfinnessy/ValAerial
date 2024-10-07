package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.*;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@Service
public class MatchService {
    private final HttpUtilityService httpUtilityService;
    private final Entitlements entitlements;
    private final ClientRegion clientRegion;
    private final MatchHistory matchHistory;
    private final MatchStats matchStats;
    private final MMRMovement mmrMovement;
    private final UserSession userSession;

    @Autowired
    public MatchService(
            HttpUtilityService httpUtilityService,
            Entitlements entitlements,
            ClientRegion clientRegion,
            MatchHistory matchHistory,
            MatchStats matchStats,
            MMRMovement mmrMovement,
            UserSession userSession
    ) {
        this.httpUtilityService = httpUtilityService;
        this.entitlements = entitlements;
        this.clientRegion = clientRegion;
        this.matchHistory = matchHistory;
        this.matchStats = matchStats;
        this.mmrMovement = mmrMovement;
        this.userSession = userSession;
    }

    // ------------------------------ MATCH HISTORY ------------------------------
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
                getMatchResults(match);
            }

        }
        return response != null;
    }

    private void getMatchResults(MatchHistory.Match match) {
        // generate url for match specifics
        String shard = clientRegion.getRegion();
        String matchId = match.getMatchID();
        String matchUrl = "https://pd."+shard+".a.pvp.net/match-details/v1/matches/"+matchId;

        // Create riot request
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(matchUrl, entitlements);
        Gson gson = new GsonBuilder()
                .create();

        // Print out each individual match metadata
        if (response != null) {
            MatchStats tempMatchStats = gson.fromJson(response.body(), MatchStats.class);
            matchStats.setMatchInfo(tempMatchStats.getMatchInfo());
            matchStats.setPlayers(tempMatchStats.getPlayers());
            System.out.println(matchStats);
        }
    }

    public boolean getCompHistory(int startIndex, int endIndex) {
        // generate url for match history
        String shard = clientRegion.getRegion();
        String puuid = entitlements.getSubject();
        String queue = "competitive";

        String compHistoryUrl = "https://pd."+shard+".a.pvp.net/mmr/v1/players/"+puuid+"/competitiveupdates?startIndex="+startIndex+"&endIndex="+endIndex +"&queue="+queue;

        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(compHistoryUrl, entitlements);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        if (response!= null) {
            MMRMovement tempMMRMovement = gson.fromJson(response.body(), MMRMovement.class);
            mmrMovement.setMatches(tempMMRMovement.getMatches());
        }
        return response != null;
    }

    // ------------------------------ CURRENT MATCH ------------------------------
    public void getCurrentMatchId() {
        String shard = clientRegion.getRegion().toLowerCase();
        String puuid = entitlements.getSubject();
        String currentMatchIdUrl = "https://glz-" + shard + "-1." + shard + ".a.pvp.net/core-game/v1/players/" + puuid;
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(currentMatchIdUrl, entitlements);

        Gson gson = new GsonBuilder()
                .create();

        if (response.statusCode() == 200) {
            UserSession tempUserSession = gson.fromJson(response.body(), UserSession.class);
            userSession.setMatchId(tempUserSession.getMatchId());
        }
        System.out.println(response.body());
    }

    public void getCurrentMatch() {
        String shard = clientRegion.getRegion().toLowerCase();
        String matchId = userSession.getMatchId();
        String currentMatchUrl = "https://glz-" + shard + "-1." + shard + ".a.pvp.net/core-game/v1/matches/" + matchId;
        HttpResponse<String> response = httpUtilityService.httpGetRiotRequest(currentMatchUrl, entitlements);

        Gson gson = new GsonBuilder()
                .create();

        if (response.statusCode() == 200) {
            UserSession tempUserSession = gson.fromJson(response.body(), UserSession.class);
            userSession.setPlayers(tempUserSession.getPlayers());
        }
        System.out.println(response.body());
    }
}
