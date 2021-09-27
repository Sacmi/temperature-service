package ru.sacmi.temperatureservice.service;

import ru.sacmi.temperatureservice.entity.InDayEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;

import java.util.Collection;

public interface InDayService {

    InDayEntity registerTemperature(Long sensorId, Float temperature)
        throws NotFoundException;

    Collection<InDayEntity> getTemperaturesBySensorId(Long sensorId)
        throws NotFoundException;
}
