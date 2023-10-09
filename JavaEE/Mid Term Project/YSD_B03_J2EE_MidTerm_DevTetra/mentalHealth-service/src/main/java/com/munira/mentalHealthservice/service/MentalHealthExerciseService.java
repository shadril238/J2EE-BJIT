package com.munira.mentalHealthservice.service;

import com.munira.mentalHealthservice.dto.MentalHealthExerciseDTO;

import java.util.List;

public interface MentalHealthExerciseService {
    void createMentalHealthExercise(MentalHealthExerciseDTO mentalHealthExerciseDTO) throws Exception;

    List<MentalHealthExerciseDTO> getAllMentalHealthExercise() throws Exception;
}
