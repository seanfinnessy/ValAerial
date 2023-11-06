package com.github.seanfinnessy.ValTracker.entity;

public class Player {
    private String subject;
    private String gameName;
    private String tagLine;
    private String teamId;
    private String competitiveTier;
    private String playerCard;
    private String playerTitle;
    private int accountLevel;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getCompetitiveTier() {
        return competitiveTier;
    }

    public void setCompetitiveTier(String competitiveTier) {
        this.competitiveTier = competitiveTier;
    }

    public String getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(String playerCard) {
        this.playerCard = playerCard;
    }

    public String getPlayerTitle() {
        return playerTitle;
    }

    public void setPlayerTitle(String playerTitle) {
        this.playerTitle = playerTitle;
    }

    public int getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(int accountLevel) {
        this.accountLevel = accountLevel;
    }

    @Override
    public String toString() {
        return "Player{" +
                "subject='" + subject + '\'' +
                ", gameName='" + gameName + '\'' +
                ", tagLine='" + tagLine + '\'' +
                ", teamId='" + teamId + '\'' +
                ", competitiveTier='" + competitiveTier + '\'' +
                ", playerCard='" + playerCard + '\'' +
                ", playerTitle='" + playerTitle + '\'' +
                ", accountLevel=" + accountLevel +
                '}';
    }
}
