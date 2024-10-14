package com.github.seanfinnessy.ValTracker.entity;

import com.github.seanfinnessy.ValTracker.model.Agents;
import com.github.seanfinnessy.ValTracker.service.MatchService;
import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserSession {
    private String sessionLoopState;
    private String seasonId;
    private String partyId;
    private String isPartyOwner;
    private String queueId;
    private String partySize;
    @SerializedName("MatchID")
    private String matchId;

    @SerializedName("Players")
    private ArrayList<Player> players;

    public String getSessionLoopState() {
        return sessionLoopState;
    }

    public void setSessionLoopState(String sessionLoopState) {
        this.sessionLoopState = sessionLoopState;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getIsPartyOwner() {
        return isPartyOwner;
    }

    public void setIsPartyOwner(String isPartyOwner) {
        this.isPartyOwner = isPartyOwner;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getPartySize() {
        return partySize;
    }

    public void setPartySize(String partySize) {
        this.partySize = partySize;
    }


    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    // We need to explicitly call setAgentName because GSON bypasses setter methods inside our Player class
    public void setPlayers(ArrayList<Player> players) {
        for (Player player: players) {
            player.setAgentName(Agents.getAgentNameWithUUID(player.getAgentUUID()).getAgentName());
        }
        this.players = players;
    }

    public void clear() {
        this.setMatchId("");
        this.setPlayers(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionLoopState='" + sessionLoopState + '\'' +
                ", seasonId='" + seasonId + '\'' +
                ", partyId='" + partyId + '\'' +
                ", isPartyOwner='" + isPartyOwner + '\'' +
                ", queueId='" + queueId + '\'' +
                ", partySize='" + partySize + '\'' +
                ", matchId='" + matchId + '\'' +
                ", players=" + players +
                '}';
    }
}
