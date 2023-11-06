package com.github.seanfinnessy.ValTracker.entity;

import org.springframework.stereotype.Component;

@Component
public class UserSession {
    private boolean isValid;
    private String sessionLoopState;
    private String partyId;
    private String isPartyOwner;
    private String partyState;
    private String partyAccessibility;
    private String queueId;
    private String partyClientVersion;
    private String partySize;
    private String playerCardId;
    private String playerTitleId;
    private int accountLevel;
    private int competitiveTier;
    private int leaderboardPosition;
    private boolean isIdle;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

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

    public String getPartyState() {
        return partyState;
    }

    public void setPartyState(String partyState) {
        this.partyState = partyState;
    }

    public String getPartyAccessibility() {
        return partyAccessibility;
    }

    public void setPartyAccessibility(String partyAccessibility) {
        this.partyAccessibility = partyAccessibility;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getPartyClientVersion() {
        return partyClientVersion;
    }

    public void setPartyClientVersion(String partyClientVersion) {
        this.partyClientVersion = partyClientVersion;
    }

    public String getPartySize() {
        return partySize;
    }

    public void setPartySize(String partySize) {
        this.partySize = partySize;
    }

    public String getPlayerCardId() {
        return playerCardId;
    }

    public void setPlayerCardId(String playerCardId) {
        this.playerCardId = playerCardId;
    }

    public String getPlayerTitleId() {
        return playerTitleId;
    }

    public void setPlayerTitleId(String playerTitleId) {
        this.playerTitleId = playerTitleId;
    }

    public int getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(int accountLevel) {
        this.accountLevel = accountLevel;
    }

    public int getCompetitiveTier() {
        return competitiveTier;
    }

    public void setCompetitiveTier(int competitiveTier) {
        this.competitiveTier = competitiveTier;
    }

    public int getLeaderboardPosition() {
        return leaderboardPosition;
    }

    public void setLeaderboardPosition(int leaderboardPosition) {
        this.leaderboardPosition = leaderboardPosition;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "isValid=" + isValid +
                ", sessionLoopState='" + sessionLoopState + '\'' +
                ", partyId='" + partyId + '\'' +
                ", isPartyOwner='" + isPartyOwner + '\'' +
                ", partyState='" + partyState + '\'' +
                ", partyAccssibility='" + partyAccessibility + '\'' +
                ", queueId='" + queueId + '\'' +
                ", partyClientVersion='" + partyClientVersion + '\'' +
                ", partySize='" + partySize + '\'' +
                ", playerCardId='" + playerCardId + '\'' +
                ", playerTitleId='" + playerTitleId + '\'' +
                ", accountLevel=" + accountLevel +
                ", competitiveTier=" + competitiveTier +
                ", leaderboardPosition=" + leaderboardPosition +
                ", isIdle=" + isIdle +
                '}';
    }
}
