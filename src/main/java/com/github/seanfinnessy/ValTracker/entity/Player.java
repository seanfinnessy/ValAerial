package com.github.seanfinnessy.ValTracker.entity;

import com.github.seanfinnessy.ValTracker.model.Agents;
import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

@Component
public class Player {
    @SerializedName("CharacterID")
    private String agentUUID;
    private String agentName;
    @SerializedName("TeamID")
    private String teamId;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getAgentUUID() {
        return agentUUID;
    }

    public void setAgentUUID(String agentUUID) {
        this.agentUUID = agentUUID;
    }

    // TODO: Get this to work so we can set agent names, and not just display in string
    public String getAgentName() {
        return Agents.getAgentName(this.agentUUID).getAgentName();
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "agentName='" + Agents.getAgentName(this.agentUUID).getAgentName() + '\'' +
                ", teamId='" + teamId + '\'' +
                ", agentId='" + agentUUID + '\'' +
                '}';
    }
}
