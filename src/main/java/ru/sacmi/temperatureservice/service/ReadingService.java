package ru.sacmi.temperatureservice.service;

import java.time.Instant;
import java.util.Collection;
import ru.sacmi.temperatureservice.entity.ReadingEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;

public interface ReadingService {

    ReadingEntity registerTemperature(Long sensorId, Float temperature)
        throws NotFoundException;

    Collection<ReadingEntity> getTemperaturesBySensorId(Long sensorId)
        throws NotFoundException;

    Collection<ReadingEntity> getTemperaturesBySensorIdAndFilter(Long sensorId, Instant from,
        Instant to)
        throws NotFoundException;
}
