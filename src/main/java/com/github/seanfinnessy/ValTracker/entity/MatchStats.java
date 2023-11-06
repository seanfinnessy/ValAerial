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
        private String gameLengthMillis;
        private String gameStartMillis;
        private boolean isCompleted;
        private String queueID;
        private boolean isRanked;
        private String seasonId;
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

        public String getGameLengthMillis() {
            return gameLengthMillis;
        }

        public void setGameLengthMillis(String gameLengthMillis) {
            this.gameLengthMillis = gameLengthMillis;
        }

        public String getGameStartMillis() {
            return gameStartMillis;
        }

        public void setGameStartMillis(String gameStartMillis) {
            this.gameStartMillis = gameStartMillis;
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

        public String getSeasonId() {
            return seasonId;
        }

        public void setSeasonId(String seasonId) {
            this.seasonId = seasonId;
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
                    ", gameLengthMillis='" + gameLengthMillis + '\'' +
                    ", gameStartMillis='" + gameStartMillis + '\'' +
                    ", isCompleted=" + isCompleted +
                    ", queueId='" + queueID + '\'' +
                    ", isRanked=" + isRanked +
                    ", seasonId='" + seasonId + '\'' +
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
