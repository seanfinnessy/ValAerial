package com.github.seanfinnessy.ValTracker.entity;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Presences {
    private ArrayList<Presence> presences;

    public ArrayList<Presence> getPresences() {
        return presences;
    }

    public void setPresences(ArrayList<Presence> presences) {
        this.presences = presences;
    }

    public static class Presence {
        @SerializedName("game_name")
        private String gameName;

        @SerializedName("game_tag")
        private String gameTag;
        private String puuid;
        private String state;
        private String time;

        @SerializedName("private")
        private String privateB64;

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getGameTag() {
            return gameTag;
        }

        public void setGameTag(String gameTag) {
            this.gameTag = gameTag;
        }

        public String getPuuid() {
            return puuid;
        }

        public void setPuuid(String puuid) {
            this.puuid = puuid;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPrivateB64() {
            return privateB64;
        }

        public void setPrivateB64(String privateB64) {
            this.privateB64 = privateB64;
        }

        @Override
        public String toString() {
            return "Presence{" +
                    "gameName='" + gameName + '\'' +
                    ", gameTag='" + gameTag + '\'' +
                    ", puuid='" + puuid + '\'' +
                    ", state='" + state + '\'' +
                    ", time='" + time + '\'' +
                    ", privateB64='" + privateB64 + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Presences{" +
                "presences=" + presences +
                '}';
    }
}
