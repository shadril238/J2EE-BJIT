package com.example.userservice.service.Implementation;

import com.example.userservice.Entity.HealthDataEntity;
import com.example.userservice.Entity.UserEntity;
import com.example.userservice.constants.AppConstants;
import com.example.userservice.dto.HealthDataDto;
import com.example.userservice.repository.HealthDataRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.HealthDataService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class HealthDataServiceImplementation implements HealthDataService {
    @Autowired
    private HealthDataRepository healthDataRepository;
    @Autowired
    private UserRepository userRepository;


    public void createHealthData(HealthDataDto healthDataDto) throws Exception {
        HealthDataEntity healthDataEntity = new HealthDataEntity();
        healthDataEntity.setUserEntity(getCurrentUser());
        healthDataEntity.setHeight(healthDataDto.getHeight());
        healthDataEntity.setWeight(healthDataDto.getWeight());
        healthDataEntity.setSleepTime(healthDataDto.getSleepTime());
        healthDataEntity.setBloodPressure(healthDataDto.getBloodPressure());
        healthDataEntity.setTimestamp(LocalDateTime.now());
        healthDataRepository.save(healthDataEntity);
    }



    public HealthDataDto getHealthData() throws Exception {
        HealthDataEntity healthDataEntity = healthDataRepository.findTopByUserEntityOrderByTimestampDesc(getCurrentUser());;
        return new ModelMapper().map(healthDataEntity, HealthDataDto.class);
    }


    public List<HealthDataDto> getAllHealthData() throws Exception {
        List <HealthDataEntity> allHealthDataEntity = healthDataRepository.findAllByUserEntity(getCurrentUser());
        List <HealthDataDto> allHealthDataDto = new ArrayList<>();
        for(HealthDataEntity healthDataEntity : allHealthDataEntity) {
            allHealthDataDto.add(new ModelMapper().map(healthDataEntity, HealthDataDto.class));
        }
        return allHealthDataDto;
    }






    private UserEntity getCurrentUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new Exception(AppConstants.TOKEN_INVALID));
    }


}
