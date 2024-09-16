package com.github.seanfinnessy.ValTracker.entity;

import com.github.seanfinnessy.ValTracker.model.Rank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MMRMovement {

    private ArrayList<Matches> Matches;
    private static class Matches {
        private String MatchID;
        private String MapID;
        private int RankedRatingAfterUpdate;
        private int RankedRatingEarned;
        private int TierAfterUpdate;
        private String currentRank;

        public String getCurrentRank() {
            return Rank.getRankByValue(this.TierAfterUpdate).getDisplayName();
        }

        public String getMatchID() {
            return MatchID;
        }

        public void setMatchID(String matchID) {
            MatchID = matchID;
        }

        public String getMapID() {
            return MapID;
        }

        public void setMapID(String mapID) {
            MapID = mapID;
        }

        public int getRankedRatingAfterUpdate() {
            return RankedRatingAfterUpdate;
        }

        public void setRankedRatingAfterUpdate(int rankedRatingAfterUpdate) {
            RankedRatingAfterUpdate = rankedRatingAfterUpdate;
        }

        public int getRankedRatingEarned() {
            return RankedRatingEarned;
        }

        public void setRankedRatingEarned(int rankedRatingEarned) {
            RankedRatingEarned = rankedRatingEarned;
        }

        public int getTierAfterUpdate() {
            return TierAfterUpdate;
        }

        public void setTierAfterUpdate(int tierAfterUpdate) {
            TierAfterUpdate = tierAfterUpdate;
        }

        @Override
        public String toString() {
            return "Matches{" +
                    "MatchID='" + MatchID + '\'' +
                    ", MapID='" + MapID + '\'' +
                    ", RankedRatingAfterUpdate=" + RankedRatingAfterUpdate +
                    ", RankedRatingEarned=" + RankedRatingEarned +
                    ", TierAfterUpdate=" + TierAfterUpdate +
                    ", currentRank='" + currentRank + '\'' +
                    '}';
        }
    }

    public ArrayList<MMRMovement.Matches> getMatches() {
        return Matches;
    }

    public void setMatches(ArrayList<MMRMovement.Matches> matches) {
        Matches = matches;
    }

    @Override
    public String toString() {
        return "MMRMovement{" +
                "Matches=" + Matches +
                '}';
    }
}
