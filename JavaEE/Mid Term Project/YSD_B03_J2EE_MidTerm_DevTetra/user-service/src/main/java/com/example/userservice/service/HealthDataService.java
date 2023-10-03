package com.example.userservice.service;

import com.example.userservice.dto.HealthDataDto;

import java.util.List;

public interface HealthDataService {
    public void createHealthData(HealthDataDto healthDataDto) throws Exception;
    public HealthDataDto getHealthData() throws Exception;

    public List<HealthDataDto> getAllHealthData() throws Exception;
}

