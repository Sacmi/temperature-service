package ru.sacmi.temperatureservice.service;

import ru.sacmi.temperatureservice.entity.SensorEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;

import java.util.Collection;

public interface SensorService {

    SensorEntity registerSensor(String uuid);

    SensorEntity createSensor(String uuid);

    SensorEntity getSensor(Long id) throws NotFoundException;

    Collection<SensorEntity> getAllSensors();

    void changeSensorName(Long id, String label) throws NotFoundException;
}
