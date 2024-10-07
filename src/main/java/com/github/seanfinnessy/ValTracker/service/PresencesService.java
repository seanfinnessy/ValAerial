package com.github.seanfinnessy.ValTracker.service;

import com.github.seanfinnessy.ValTracker.entity.*;
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
    private final MatchService matchService;

    Logger logger = Logger.getLogger(PresencesService.class.getName());


    @Autowired
    public PresencesService(HttpUtilityService httpUtilityService, Lockfile lockfile, Presences presences, Entitlements entitlements, UserSession userSession, MatchService matchService) {
        this.httpUtilityService = httpUtilityService;
        this.lockfile = lockfile;
        this.presences = presences;
        this.entitlements = entitlements;
        this.userSession = userSession;
        this.matchService = matchService;
    }

    @Scheduled(fixedRate = 10000)
    public void getPresences() {
        // check initial state
        String initialGameState = null;
        if (userSession.getSessionLoopState() != null) {
            initialGameState = userSession.getSessionLoopState();
        }

        // generate url for presences
        if (lockfile.getPort() != null) {
            String port = lockfile.getPort();
            String presencesUrl = "https://127.0.0.1:" + port + "/chat/v4/presences";
            HttpResponse<String> response = httpUtilityService.httpGetLocalRequest(presencesUrl, lockfile);

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                Presences tempPresences = gson.fromJson(response.body(), Presences.class);
                presences.setPresences(tempPresences.getPresences());

                // get the users presence out of the list of presences.
                Presences.Presence usersPresence = getUserPresence(presences.getPresences());

                // set the users game state
                assert usersPresence != null;
                setUsersGameState(usersPresence);

                // executes if our current session changes
                if (!userSession.getSessionLoopState().equalsIgnoreCase(initialGameState)) {
                    // IF INGAME, GET CURRENT MATCH ID
                    if (userSession.getSessionLoopState().equalsIgnoreCase("INGAME")) {
                        matchService.getCurrentMatchId();

                        // IF WE GET A MATCH ID, GET MATCH DETAILS
                        if (!userSession.getMatchId().isEmpty()) {
                            matchService.getCurrentMatch();
                        }
                    }
                }
                System.out.println(userSession.toString());
            }
        } else logger.warning("Could not get presences...");
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
        userSession.setIsPartyOwner(tempUserSession.getIsPartyOwner());
        userSession.setQueueId(tempUserSession.getQueueId());
        userSession.setPartyId(tempUserSession.getPartyId());
        userSession.setPartySize(tempUserSession.getPartySize());
    }
}
