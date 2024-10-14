package com.github.seanfinnessy.ValTracker.responses;

import com.github.seanfinnessy.ValTracker.entity.Player;
import com.github.seanfinnessy.ValTracker.model.Agents;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MatchApiResponse {
    @SerializedName("Players")
    private ArrayList<Player> players;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    // We need to explicitly call setAgentName because GSON bypasses setter methods inside our Player class
    public void setPlayers(ArrayList<Player> players) {
        System.out.println("called");
        for (Player player: players) {
            player.setAgentName(Agents.getAgentNameWithUUID(player.getAgentUUID()).getAgentName());
        }
        this.players = players;
    }

    @Override
    public String toString() {
        return "MatchApiResponse{" +
                "players=" + players +
                '}';
    }
}
