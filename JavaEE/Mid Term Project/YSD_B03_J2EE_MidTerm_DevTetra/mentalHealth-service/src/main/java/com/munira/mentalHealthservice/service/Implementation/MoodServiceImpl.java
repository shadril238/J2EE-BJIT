package com.munira.mentalHealthservice.service.Implementation;

import com.munira.mentalHealthservice.dto.MentalHealthExerciseDTO;
import com.munira.mentalHealthservice.dto.MoodDTO;
import com.munira.mentalHealthservice.dto.UserDTO;
import com.munira.mentalHealthservice.entity.MentalHealthExerciseEntity;
import com.munira.mentalHealthservice.entity.MoodEntity;
import com.munira.mentalHealthservice.exception.MoodNotFoundException;
import com.munira.mentalHealthservice.exception.RecommendationNotFoundException;
import com.munira.mentalHealthservice.repository.MentalHealthExerciseRepository;
import com.munira.mentalHealthservice.repository.MoodRepository;
import com.munira.mentalHealthservice.service.MoodService;
import com.munira.mentalHealthservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoodServiceImpl implements MoodService {
    @Autowired
    private MoodRepository moodRepository;

    @Autowired
    private MentalHealthExerciseRepository mentalHealthExerciseRepository;

    @Autowired
    private UserService userService;

    @Override
    public void createMood(MoodDTO moodDTO) throws Exception {
        MoodEntity moodEntity = new MoodEntity();
        UserDTO userDTO = userService.getCurrentUser();

        moodEntity.setUserId(userDTO.getUserId());
        moodEntity.setMoodState(moodDTO.getMoodState());
        moodEntity.setStressType(moodDTO.getStressType());
        moodEntity.setMoodNote(moodDTO.getMoodNote());
        moodEntity.setTimeStamp(LocalDateTime.now());

        moodRepository.save(moodEntity);
    }

    @Override
    public List<MoodDTO> getMood() throws Exception {
        UserDTO userDTO = userService.getCurrentUser();

        List<MoodEntity> moodEntityList = moodRepository.findAllByUserId(userDTO.getUserId());
        List<MoodDTO> moodDTOList = new ArrayList<>();
        for (MoodEntity moodEntity : moodEntityList) {
            moodDTOList.add(new ModelMapper().map(moodEntity, MoodDTO.class));
        }
        return moodDTOList;
    }

    @Override
    public List<MentalHealthExerciseDTO> getRecommendedExercises() throws Exception {
        Optional<MoodDTO> moodDTO = getLastMood();
        if (moodDTO.isEmpty()) {
            throw new MoodNotFoundException("You need to enter your mood state.");
        }

        int moodScore = getMoodScore(moodDTO.get());
        List<MentalHealthExerciseEntity> mentalHealthExerciseEntities = mentalHealthExerciseRepository.findByExerciseScore(moodScore);

        if (mentalHealthExerciseEntities.isEmpty()) {
            throw new RecommendationNotFoundException("There is no recommendation for you still now!");
        }

        return mentalHealthExerciseEntities.stream()
                .map(exercise -> {
                    return new ModelMapper()
                            .map(exercise, MentalHealthExerciseDTO.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MoodDTO> getLastMood() throws Exception {
        UserDTO userDTO = userService.getCurrentUser();
        MoodEntity moodEntity = moodRepository.findTopByUserIdOrderByTimeStampDesc(userDTO.getUserId());
        if (moodEntity != null) {
            MoodDTO moodDTO = new ModelMapper().map(moodEntity, MoodDTO.class);
            return Optional.of(moodDTO);
        } else {
            return Optional.empty();
        }
    }

    private int getMoodScore(MoodDTO moodDTO) {
        int moodStateScore = 0, stressTypeScore = 0;
        switch (moodDTO.getMoodState()) {
            case "happy" -> moodStateScore = 1;
            case "normal" -> moodStateScore = 2;
            case "sad" -> moodStateScore = 3;
        }
        switch (moodDTO.getStressType()) {
            case "low" -> stressTypeScore = 1;
            case "normal" -> stressTypeScore = 2;
            case "high" -> stressTypeScore = 3;
        }
        return moodStateScore + stressTypeScore;
    }
}
