package com.github.seanfinnessy.ValTracker.entity;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Lockfile {
    private String name;
    private String pid;
    private String port;
    private String password;
    private String encodedPassword;
    private String protocol;

    public Lockfile() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        String riotPrefixPassword = "riot:" + encodedPassword;
        this.encodedPassword = "Basic " + Base64.getEncoder().encodeToString(riotPrefixPassword.getBytes());
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "Lockfile{" +
                "name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", port='" + port + '\'' +
                ", password='" + password + '\'' +
                ", encodedPassword='" + encodedPassword + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
