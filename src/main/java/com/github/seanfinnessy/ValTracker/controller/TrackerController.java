package com.github.seanfinnessy.ValTracker.controller;

import com.github.seanfinnessy.ValTracker.entity.Lockfile;
import com.github.seanfinnessy.ValTracker.exception.ValorantNotRunningException;
import com.github.seanfinnessy.ValTracker.service.EntitlementsService;
import com.github.seanfinnessy.ValTracker.service.LockfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrackerController {

    private final LockfileService lockfileService;
    private final Lockfile lockfile;
    private final EntitlementsService entitlementsService;

    @Autowired
    public TrackerController(
            LockfileService theLockfileService,
            Lockfile theLockfile,
            EntitlementsService theEntitlementsService) {
        lockfileService = theLockfileService;
        lockfile = theLockfile;
        entitlementsService = theEntitlementsService;
    }

    @GetMapping("/init")
    public String initiateApplication() {
        // get lockfile contents
        if (!lockfileService.getLockfileContents(lockfile)) {
            throw new ValorantNotRunningException("Failed retrieving lockfile data. Please make sure Valorant is running.");
        }

        // get entitlements
        if (!entitlementsService.getEntitlements(lockfile)) {
            throw new ValorantNotRunningException("Failed retrieving entitlements. Please make sure Valorant is running.");
        }

        return lockfile.toString();
    }
}
