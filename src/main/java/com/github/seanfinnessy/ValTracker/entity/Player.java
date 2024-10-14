package com.github.seanfinnessy.ValTracker.entity;

import com.github.seanfinnessy.ValTracker.model.Agents;
import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Player {
    @SerializedName("CharacterID")
    private String agentUUID;
    @SerializedName("TeamID")
    private String teamId;
    private String currentRank;

    @SerializedName("PlayerIdentity")
    private PlayerStats playerStats;

    public class PlayerStats {
        @SerializedName("Subject")
        private String playerUUID;

        @SerializedName("Incognito")
        private boolean incognito;

        public String getPlayerUUID() {
            return playerUUID;
        }

        public boolean isIncognito() {
            return incognito;
        }


        @Override
        public String toString() {
            if (incognito) {
                return "PlayerStats{" +
                        "incognito=" + true +
                        '}';
            }
            return "PlayerStats{" +
                    "playerUUID='" + playerUUID + '\'' +
                    ", incognito=" + false +
                    '}';
        }
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    private String agentName;

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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(String currentRank) {
        this.currentRank = currentRank;
    }

    @Override
    public String toString() {
        return "Player{" +
                "agentUUID='" + agentUUID + '\'' +
                ", teamId='" + teamId + '\'' +
                ", currentRank='" + currentRank + '\'' +
                ", playerStats=" + playerStats +
                ", agentName='" + agentName + '\'' +
                '}';
    }
}
