package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.Entitlements;
import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import com.github.seanfinnessy.ValTracker.entity.Version;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${b64EncodedJSONForXRiotClientPlatform}")
    public String b64EncodedXRiotClientPlatform;

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

    public HttpResponse<String> httpGet3rdPartyRequest(String url) {
        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            return HttpClient
                    .newBuilder()
                    .build()
                    .send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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

            // get version every time we getRiotRequest
            VersionService versionService = new VersionService(this);
            String clientVersion = versionService.getClientVersion("https://valorant-api.com/v1/version");

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Authorization", "Bearer "+entitlements.getAccessToken())
                    .header("X-Riot-Entitlements-JWT", entitlements.getToken())
                    .header("X-Riot-ClientPlatform", b64EncodedXRiotClientPlatform)
                    .header("X-Riot-ClientVersion", clientVersion)
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
