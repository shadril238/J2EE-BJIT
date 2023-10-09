package com.munira.mentalHealthservice.service.Implementation;

import com.munira.mentalHealthservice.dto.MentalHealthExerciseDTO;
import com.munira.mentalHealthservice.entity.MentalHealthExerciseEntity;
import com.munira.mentalHealthservice.repository.MentalHealthExerciseRepository;
import com.munira.mentalHealthservice.service.MentalHealthExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentalHealthExerciseServiceImpl implements MentalHealthExerciseService {

    @Autowired
    private MentalHealthExerciseRepository mentalHealthExerciseRepository;

    @Override
    public void createMentalHealthExercise(MentalHealthExerciseDTO mentalHealthExerciseDTO) {
        MentalHealthExerciseEntity mentalHealthExerciseEntity = new ModelMapper().map(mentalHealthExerciseDTO, MentalHealthExerciseEntity.class);
        mentalHealthExerciseRepository.save(mentalHealthExerciseEntity);
    }

    @Override
    public List<MentalHealthExerciseDTO> getAllMentalHealthExercise() {
        List<MentalHealthExerciseEntity> mentalHealthExerciseEntities = mentalHealthExerciseRepository.findAll();
        List<MentalHealthExerciseDTO> mentalHealthExerciseDTOList = new ArrayList<>();
        for (MentalHealthExerciseEntity mentalHealthExerciseEntity : mentalHealthExerciseEntities) {
            mentalHealthExerciseDTOList.add(new ModelMapper()
                    .map(mentalHealthExerciseEntity, MentalHealthExerciseDTO.class));
        }
        return mentalHealthExerciseDTOList;
    }
}
