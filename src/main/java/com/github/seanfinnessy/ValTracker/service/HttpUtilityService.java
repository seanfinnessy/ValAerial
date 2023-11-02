package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.Entitlements;
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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.logging.Logger;

@Service
public class HttpUtilityService {
    Logger logger = Logger.getLogger(EntitlementsService.class.getName());

    // Ignore all certs when connection to riot APIs. Specifically mentioned in docs to do this.
    private static final TrustManager[] trustAllCerts = new TrustManager[]{
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

    public HttpResponse<String> httpGetLocalRequest(String url, Lockfile lockfile) {
        // code for ignoring the riot ssl cert, Riot docs mentioned to ignore it
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());


            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Authorization", lockfile.getEncodedPassword())
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            return HttpClient
                    .newBuilder()
                    .sslContext(sslContext)
                    .build()
                    .send(getRequest, HttpResponse.BodyHandlers.ofString());


        } catch (NoSuchAlgorithmException | URISyntaxException | IOException | InterruptedException |
                 KeyManagementException e) {
            logger.warning("Exception occurred while connecting to: " + url + ". Root cause: " + e.getCause());
            return null;
        }
    }

    public HttpResponse<String> httpGetRiotRequest(String url, Entitlements entitlements) {
        // code for ignoring the riot ssl cert, Riot docs mentioned to ignore it
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());


            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Authorization", "Bearer "+entitlements.getAccessToken())
                    .header("X-Riot-Entitlements-JWT", entitlements.getToken())
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            return HttpClient
                    .newBuilder()
                    .sslContext(sslContext)
                    .build()
                    .send(getRequest, HttpResponse.BodyHandlers.ofString());


        } catch (NoSuchAlgorithmException | URISyntaxException | IOException | InterruptedException |
                 KeyManagementException e) {
            logger.warning("Exception occurred while connecting to: " + url + ". Root cause: " + e.getCause());
            return null;
        }
    }
}
