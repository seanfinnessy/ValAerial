package com.github.seanfinnessy.ValTracker.controller;

import com.github.seanfinnessy.ValTracker.entity.ClientRegion;
import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import com.github.seanfinnessy.ValTracker.exception.ValorantNotRunningException;
import com.github.seanfinnessy.ValTracker.service.ClientRegionService;
import com.github.seanfinnessy.ValTracker.service.EntitlementsService;
import com.github.seanfinnessy.ValTracker.service.LockfileService;
import com.github.seanfinnessy.ValTracker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrackerController {

    private final LockfileService lockfileService;
    private final Lockfile lockfile;
    private final ClientRegion clientRegion;
    private final EntitlementsService entitlementsService;
    private final ClientRegionService clientRegionService;
    private final MatchService matchService;

    @Autowired
    public TrackerController(
            LockfileService theLockfileService,
            Lockfile theLockfile,
            ClientRegion theClientRegion,
            EntitlementsService theEntitlementsService,
            ClientRegionService theClientRegionService,
            MatchService theMatchService
    ) {
        lockfileService = theLockfileService;
        lockfile = theLockfile;
        clientRegion = theClientRegion;
        entitlementsService = theEntitlementsService;
        clientRegionService = theClientRegionService;
        matchService = theMatchService;
    }

    @GetMapping("/init")
    public String initiateApplication() {
        // get lockfile contents
        if (!lockfileService.getLockfileContents()) {
            throw new ValorantNotRunningException("Failed retrieving lockfile data. Please make sure Valorant is running.");
        }
        System.out.println(lockfile);

        // get entitlements
        if (!entitlementsService.getEntitlements()) {
            throw new ValorantNotRunningException("Failed retrieving entitlements. Please make sure Valorant is running.");
        }

        // get client region
        if (!clientRegionService.getClientRegion()) {
            throw new ValorantNotRunningException("Failed retrieving client region. Please make sure Valorant is running.");
        }

        if (!matchService.getMatchHistory(0, 20)) {
            throw new ValorantNotRunningException("Could not retrieve matches.");
        }


        return clientRegion.toString();
    }
}
