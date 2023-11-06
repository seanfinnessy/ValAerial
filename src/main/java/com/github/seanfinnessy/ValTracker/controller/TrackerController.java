package com.github.seanfinnessy.ValTracker.controller;

import com.github.seanfinnessy.ValTracker.entity.MatchHistory;
import com.github.seanfinnessy.ValTracker.exception.ValorantNotRunningException;
import com.github.seanfinnessy.ValTracker.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrackerController {

    private final MatchHistory matchHistory;
    private final MatchService matchService;

    @Autowired
    public TrackerController(MatchHistory matchHistory, MatchService matchService) {
        this.matchHistory = matchHistory;
        this.matchService = matchService;
    }

    @GetMapping("/match-history")
    public String initiateApplication() {
        if (!matchService.getMatchHistory(0, 20)) {
            throw new ValorantNotRunningException("Could not retrieve matches.");
        }

        return matchHistory.toString();
    }
}
