package com.github.seanfinnessy.ValTracker.responses;

import com.github.seanfinnessy.ValTracker.service.SeasonIdService;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SeasonIdApiResponse {
    private String currentSeasonId;
    @SerializedName("Seasons")
    private ArrayList<Season> seasons;

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public String getCurrentSeasonId() {
        for (Season season: seasons) {
            if (season.isActive) {
                return season.getSeasonId();
            }
        }
        return "inactive-season";
    }

    static class Season {
        @SerializedName("IsActive")
        private boolean isActive;
        @SerializedName("ID")
        private String seasonId;

        public String getSeasonId() {
            return seasonId;
        }
    }
}
