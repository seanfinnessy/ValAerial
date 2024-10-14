package com.github.seanfinnessy.ValTracker.responses;

import com.google.gson.annotations.SerializedName;

public class MatchIdApiResponse {
    @SerializedName("MatchID")
    private String matchId;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    @Override
    public String toString() {
        return "MatchIdApiResponse{" +
                "matchId='" + matchId + '\'' +
                '}';
    }
}
