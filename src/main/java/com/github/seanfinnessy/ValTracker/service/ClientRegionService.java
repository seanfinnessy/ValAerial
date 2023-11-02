package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.ClientRegion;
import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class ClientRegionService {
    private final HttpUtilityService httpUtilityService;
    private final ClientRegion clientRegion;
    private final Lockfile lockfile;

    @Autowired
    public ClientRegionService(Lockfile theLockfile, HttpUtilityService theHttpUtilityService, ClientRegion theClientRegion) {
        httpUtilityService = theHttpUtilityService;
        lockfile = theLockfile;
        clientRegion = theClientRegion;
    }

    public boolean getClientRegion() {
        // generate url for client region
        String port = lockfile.getPort();
        String clientRegionUrl = "https://127.0.0.1:" + port + "/riotclient/region-locale";
        HttpResponse<String> response = httpUtilityService.httpGetLocalRequest(clientRegionUrl, lockfile);
        Gson gson = new Gson();


        if (response != null) {
            ClientRegion tempClientRegion = gson.fromJson(response.body(), ClientRegion.class);
            clientRegion.setRegion(tempClientRegion.getRegion());
            clientRegion.setLocale(tempClientRegion.getLocale());
            clientRegion.setWebRegion(tempClientRegion.getWebRegion());
            clientRegion.setWebLanguage(tempClientRegion.getWebLanguage());
        }
        return response != null;
    }
}
