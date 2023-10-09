package com.munira.mentalHealthservice.controller;

import com.munira.mentalHealthservice.dto.MentalHealthExerciseDTO;
import com.munira.mentalHealthservice.dto.MoodDTO;
import com.munira.mentalHealthservice.exception.ExerciseNotFoundException;
import com.munira.mentalHealthservice.exception.MoodNotFoundException;
import com.munira.mentalHealthservice.exception.RecommendationNotFoundException;
import com.munira.mentalHealthservice.service.MentalHealthExerciseService;
import com.munira.mentalHealthservice.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mental-health")
public class MentalHealthController {

    @Autowired
    private MentalHealthExerciseService mentalHealthExerciseService;

    @Autowired
    private MoodService moodService;

    @PostMapping("/exercises")
    public ResponseEntity<?> createMentalHealthExercise(@RequestBody MentalHealthExerciseDTO mentalHealthExerciseDTO) {
        try {
            mentalHealthExerciseService.createMentalHealthExercise(mentalHealthExerciseDTO);
            return new ResponseEntity<>("New exercise added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exercises")
    public ResponseEntity<?> getMentalHealthExercise() {
        try {
            List<MentalHealthExerciseDTO> mentalHealthExerciseDTOList = mentalHealthExerciseService.getAllMentalHealthExercise();
            if (mentalHealthExerciseDTOList.isEmpty()) {
                throw new ExerciseNotFoundException("No exercise is available.");
            }
            return new ResponseEntity<>(mentalHealthExerciseDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/mood-tracking")
    public ResponseEntity<?> createMood(@RequestBody MoodDTO moodDTO) {
        try {
            moodService.createMood(moodDTO);
            return new ResponseEntity<>("New mood added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mood-tracking")
    public ResponseEntity<?> getMood() {
        try {
            List<MoodDTO> moodDTOList = moodService.getMood();
            if (moodDTOList.isEmpty()) {
                throw new MoodNotFoundException("No mood track was found!!");
            }
            return new ResponseEntity<>(moodDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mood-tracking/last")
    public ResponseEntity<?> getLastMood() {
        try {
            Optional<MoodDTO> moodDTO = moodService.getLastMood();
            if (moodDTO.isEmpty()) {
                throw new MoodNotFoundException("You've not entered any mood still now!");
            }
            return new ResponseEntity<>(moodDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recommendations")
    public ResponseEntity<?> getRecommendedExercises() throws Exception {
        try {
            List<MentalHealthExerciseDTO> mentalHealthExerciseDTOList = moodService.getRecommendedExercises();
            if (mentalHealthExerciseDTOList.isEmpty()) {
                throw new RecommendationNotFoundException("There is no recommendation is for you.");
            }
            return new ResponseEntity<>(mentalHealthExerciseDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}