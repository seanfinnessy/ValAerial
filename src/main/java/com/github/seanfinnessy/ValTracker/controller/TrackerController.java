package com.github.seanfinnessy.ValTracker.controller;

import com.github.seanfinnessy.ValTracker.entity.MMRMovement;
import com.github.seanfinnessy.ValTracker.entity.MatchHistory;
import com.github.seanfinnessy.ValTracker.exception.ValorantNotRunningException;
import com.github.seanfinnessy.ValTracker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrackerController {

    private final MatchHistory matchHistory;
    private final MatchService matchService;
    private final MMRMovement mmrMovement;

    @Autowired
    public TrackerController(MatchHistory matchHistory, MatchService matchService, MMRMovement mmrMovement) {
        this.matchHistory = matchHistory;
        this.matchService = matchService;
        this.mmrMovement = mmrMovement;
    }

    @GetMapping("/match-history")
    public String initiateApplication() {
        if (!matchService.getMatchHistory(0, 20)) {
            throw new ValorantNotRunningException("Could not retrieve matches.");
        }

        return matchHistory.toString();
    }

    // TODO: We maybe use the matchID returned from here to get kills/death/assist from that game and return.
    @GetMapping("/competitive-updates")
    public ResponseEntity<?> getCompUpdates() {
        if (!matchService.getCompHistory(0, 20)) {
            throw new ValorantNotRunningException("Could not get mmr history");
        }
        return ResponseEntity.ok(mmrMovement);
    }
}
