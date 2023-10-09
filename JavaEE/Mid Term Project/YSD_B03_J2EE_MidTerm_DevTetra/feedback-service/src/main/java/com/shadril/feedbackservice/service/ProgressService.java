package com.shadril.feedbackservice.service;

import com.shadril.feedbackservice.dto.ProgressInsightsDto;
import com.shadril.feedbackservice.dto.ProgressTrackDto;

import java.util.Optional;

public interface ProgressService {
    Optional<ProgressTrackDto> trackProgress();
    Optional<ProgressInsightsDto> progressInsights();
}
