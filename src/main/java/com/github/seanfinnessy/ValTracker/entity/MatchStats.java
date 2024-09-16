package com.github.seanfinnessy.ValTracker.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MatchStats {
    private MatchInfo matchInfo;
    private ArrayList<Player> players;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public MatchInfo getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(MatchInfo matchInfo) {
        this.matchInfo = matchInfo;
    }

    public static class MatchInfo {
        private String matchId;
        private String mapId;
        private boolean isCompleted;
        private String queueID;
        private boolean isRanked;
        private String completionState;

        public String getMatchId() {
            return matchId;
        }

        public void setMatchId(String matchId) {
            this.matchId = matchId;
        }

        public String getMapId() {
            return mapId;
        }

        public void setMapId(String mapId) {
            this.mapId = mapId;
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public void setCompleted(boolean completed) {
            isCompleted = completed;
        }

        public String getQueueID() {
            return queueID;
        }

        public void setQueueID(String queueID) {
            this.queueID = queueID;
        }

        public boolean isRanked() {
            return isRanked;
        }

        public void setRanked(boolean ranked) {
            isRanked = ranked;
        }

        public String getCompletionState() {
            return completionState;
        }

        public void setCompletionState(String completionState) {
            this.completionState = completionState;
        }

        @Override
        public String toString() {
            return "MatchInfo{" +
                    "matchId='" + matchId + '\'' +
                    ", mapId='" + mapId + '\'' +
                    ", isCompleted=" + isCompleted +
                    ", queueID='" + queueID + '\'' +
                    ", isRanked=" + isRanked +
                    ", completionState='" + completionState + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MatchStats{" +
                "matchInfo=" + matchInfo +
                ", players=" + players +
                '}';
    }
}
