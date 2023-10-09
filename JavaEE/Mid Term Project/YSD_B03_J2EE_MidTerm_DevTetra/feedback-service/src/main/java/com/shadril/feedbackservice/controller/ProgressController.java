package com.shadril.feedbackservice.controller;

import com.shadril.feedbackservice.dto.ProgressInsightsDto;
import com.shadril.feedbackservice.dto.ProgressTrackDto;
import com.shadril.feedbackservice.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    private ProgressService progressService;

    @GetMapping("/track")
    public ResponseEntity<ProgressTrackDto> trackProgress() {
        return new ResponseEntity<>(progressService.trackProgress().get(), HttpStatus.OK);
    }

    @GetMapping("/insights")
    public ResponseEntity<ProgressInsightsDto> progressInsights() {
        return new ResponseEntity<>(progressService.progressInsights().get(), HttpStatus.OK);
    }
}
