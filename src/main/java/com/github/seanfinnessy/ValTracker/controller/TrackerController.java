package com.github.seanfinnessy.ValTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrackerController {


    @Autowired
    public TrackerController() {}

    @GetMapping("/hello")
    public String getCompUpdates() {
        return "Will do something eventually";
    }
}
