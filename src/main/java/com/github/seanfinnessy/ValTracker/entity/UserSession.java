package com.github.seanfinnessy.ValTracker.entity;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

@Component
public class UserSession {
    private String sessionLoopState;
    private String partyId;
    private String isPartyOwner;
    private String queueId;
    private String partySize;
    @SerializedName("MatchID")
    private String matchId;

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

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionLoopState='" + sessionLoopState + '\'' +
                ", partyId='" + partyId + '\'' +
                ", isPartyOwner='" + isPartyOwner + '\'' +
                ", queueId='" + queueId + '\'' +
                ", partySize='" + partySize + '\'' +
                ", matchId='" + matchId + '\'' +
                '}';
    }
}
