package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.logging.Logger;

@Service
public class EntitlementsService {

    Logger logger = Logger.getLogger(EntitlementsService.class.getName());
    public EntitlementsService() {};

    private static TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
    };

    public boolean getEntitlements(Lockfile lockfile) {
        // generate url for entitlements
        String port = lockfile.getPort();
        String entitlementsUrl = "https://127.0.0.1:" + port + "/entitlements/v1/token";

        // create request for entitlements
        try {
            // code for ignoring the riot ssl cert, Riot docs mentioned to ignore it
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(entitlementsUrl))
                    .header("Authorization", lockfile.getEncodedPassword())
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .sslContext(sslContext)
                    .build()
                    .send(getRequest, BodyHandlers.ofString());

            System.out.println(response);
            return true;
        } catch (IOException | InterruptedException | URISyntaxException | NoSuchAlgorithmException | KeyManagementException e) {
            logger.warning("Exception occurred in EntitlementsService while connecting to: " + entitlementsUrl + ". Root cause: " + e.getCause());
            return false;
        }
    }
}
