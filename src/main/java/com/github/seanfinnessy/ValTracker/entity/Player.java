package com.github.seanfinnessy.ValTracker.entity;

public class Player {
    private String subject;
    private String gameName;
    private String teamId;
    private String competitiveTier;


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

    @Override
    public String toString() {
        return "Player{" +
                "subject='" + subject + '\'' +
                ", gameName='" + gameName + '\'' +
                ", teamId='" + teamId + '\'' +
                ", competitiveTier='" + competitiveTier + '\'' +
                '}';
    }
}
