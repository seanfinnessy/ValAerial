package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.Entitlements;
import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import com.github.seanfinnessy.ValTracker.entity.Presences;
import com.github.seanfinnessy.ValTracker.entity.UserSession;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Logger;

@Service
public class PresencesService {
    private final HttpUtilityService httpUtilityService;
    private final Lockfile lockfile;
    private final Presences presences;
    private final Entitlements entitlements;

    private final UserSession userSession;

    Logger logger = Logger.getLogger(PresencesService.class.getName());


    @Autowired
    public PresencesService(HttpUtilityService httpUtilityService, Lockfile lockfile, Presences presences, Entitlements entitlements, UserSession userSession) {
        this.httpUtilityService = httpUtilityService;
        this.lockfile = lockfile;
        this.presences = presences;
        this.entitlements = entitlements;
        this.userSession = userSession;
    }

    @Scheduled(fixedRate = 10000)
    public void getPresences() {
        // generate url for presences
        if (lockfile.getPort() != null) {
            String port = lockfile.getPort();
            String presencesUrl = "https://127.0.0.1:" + port + "/chat/v4/presences";
            HttpResponse<String> response = httpUtilityService.httpGetLocalRequest(presencesUrl, lockfile);

            if (response != null) {
                Gson gson = new Gson();
                Presences tempPresences = gson.fromJson(response.body(), Presences.class);
                presences.setPresences(tempPresences.getPresences());

                // get the users presence out of the list of presences.
                Presences.Presence usersPresence = getUserPresence(presences.getPresences());

                // set the users game state
                assert usersPresence != null;
                setUsersGameState(usersPresence);

            }
        } else logger.info("Could not get presences...");
    }

    private Presences.Presence getUserPresence(ArrayList<Presences.Presence> presences) {
        for (Presences.Presence presence: presences) {
            if (presence.getPuuid().equalsIgnoreCase(entitlements.getSubject())) {
                return presence;
            }
        }
        return null;
    }

    private void setUsersGameState(Presences.Presence presence) {
        Gson gson = new Gson();

        // create json object from token
        String privateToken = presence.getPrivateB64();
        Base64.getDecoder().decode(privateToken);
        byte [] decodedBytes = Base64.getDecoder().decode(privateToken);
        String newString = new String(decodedBytes);
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(newString);

        // insert json into entity
        UserSession tempUserSession = gson.fromJson(jsonObject, UserSession.class);
        userSession.setSessionLoopState(tempUserSession.getSessionLoopState());
        userSession.setPartyState(tempUserSession.getPartyState());
        userSession.setPartyAccessibility(tempUserSession.getPartyAccessibility());
        userSession.setLeaderboardPosition(tempUserSession.getLeaderboardPosition());
        userSession.setIsPartyOwner(tempUserSession.getIsPartyOwner());
        userSession.setCompetitiveTier(tempUserSession.getCompetitiveTier());
        userSession.setIdle(tempUserSession.isIdle());
        userSession.setAccountLevel(tempUserSession.getAccountLevel());
        userSession.setQueueId(tempUserSession.getQueueId());
        userSession.setPlayerCardId(tempUserSession.getPlayerCardId());
        userSession.setPlayerTitleId(tempUserSession.getPlayerTitleId());
        userSession.setPartyId(tempUserSession.getPartyId());
        userSession.setValid(tempUserSession.isValid());
        userSession.setPartyAccessibility(tempUserSession.getPartyAccessibility());
        userSession.setPartySize(tempUserSession.getPartySize());
    }
}
