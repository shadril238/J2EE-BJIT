package com.munira.mentalHealthservice.service;

import com.munira.mentalHealthservice.dto.MentalHealthExerciseDTO;
import com.munira.mentalHealthservice.dto.MoodDTO;

import java.util.List;
import java.util.Optional;

public interface MoodService {
    void createMood(MoodDTO moodDTO) throws Exception;

    List<MoodDTO> getMood() throws Exception;

    Optional<MoodDTO> getLastMood() throws Exception;

    List<MentalHealthExerciseDTO> getRecommendedExercises() throws Exception;
}
