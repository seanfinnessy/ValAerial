package com.github.seanfinnessy.ValTracker.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MatchHistory {
    private String subject;
    private String total;
    private ArrayList<Match> history;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<Match> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Match> history) {
        this.history = history;
    }

    public static class Match {
        private String matchID;
        private long gameStartTime;
        private String queueID;

        public String getMatchID() {
            return matchID;
        }

        public void setMatchID(String matchID) {
            this.matchID = matchID;
        }

        public long getGameStartTime() {
            return gameStartTime;
        }

        public void setGameStartTime(long gameStartTime) {
            this.gameStartTime = gameStartTime;
        }

        public String getQueueID() {
            return queueID;
        }

        public void setQueueID(String queueID) {
            this.queueID = queueID;
        }

        @Override
        public String toString() {
            return "Match{" +
                    "matchID='" + matchID + '\'' +
                    ", gameStartTime=" + gameStartTime +
                    ", queueID='" + queueID + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MatchHistory{" +
                "subject='" + subject + '\'' +
                ", total='" + total + '\'' +
                ", history=" + history +
                '}';
    }
}
