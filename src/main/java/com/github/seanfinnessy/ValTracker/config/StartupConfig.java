package com.github.seanfinnessy.ValTracker.config;

import com.github.seanfinnessy.ValTracker.exception.ValorantNotRunningException;
import com.github.seanfinnessy.ValTracker.service.ClientRegionService;
import com.github.seanfinnessy.ValTracker.service.EntitlementsService;
import com.github.seanfinnessy.ValTracker.service.LockfileService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class StartupConfig {
    Logger logger = Logger.getLogger(StartupConfig.class.getName());
    private final LockfileService lockfileService;
    private final EntitlementsService entitlementsService;
    private final ClientRegionService clientRegionService;

    @Autowired
    public StartupConfig(LockfileService lockfileService, EntitlementsService entitlementsService, ClientRegionService clientRegionService) {
        this.lockfileService = lockfileService;
        this.entitlementsService = entitlementsService;
        this.clientRegionService = clientRegionService;
    }


    /* This annotation is commonly used for tasks that need to happen once the bean
    is fully initialized, but before it starts serving requests or is actively used in the application. */
    @PostConstruct
    public void init() {
        logger.info("Grabbing required contents...");
        // get lockfile contents
        if (!lockfileService.getLockfileContents()) {
            throw new ValorantNotRunningException("Failed retrieving lockfile data. Please make sure Valorant is running.");
        } else logger.info("Lockfile contents retrieved");

        // get entitlements
        if (!entitlementsService.getEntitlements()) {
            throw new ValorantNotRunningException("Failed retrieving entitlements. Please make sure Valorant is running.");
        } else logger.info("Entitlements contents retrieved");

        // get client region
        if (!clientRegionService.getClientRegion()) {
            throw new ValorantNotRunningException("Failed retrieving client region. Please make sure Valorant is running.");
        } else logger.info("Client region retrieved");
    }
}
