package com.github.seanfinnessy.ValTracker.entity;

import org.springframework.stereotype.Component;

@Component
public class Version {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String riotClientVersion;

        public String getRiotClientVersion() {
            return riotClientVersion;
        }

        public void setRiotClientVersion(String riotClientVersion) {
            this.riotClientVersion = riotClientVersion;
        }
    }
}
