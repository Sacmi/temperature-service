package ru.sacmi.temperatureservice.service.impl;

import java.time.Instant;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.sacmi.temperatureservice.entity.ReadingEntity;
import ru.sacmi.temperatureservice.entity.SensorEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;
import ru.sacmi.temperatureservice.repository.ReadingRepository;
import ru.sacmi.temperatureservice.service.ReadingService;
import ru.sacmi.temperatureservice.service.SensorService;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ReadingServiceImpl implements ReadingService {

    ReadingRepository inDayRepository;
    SensorService sensorService;

    @Override
    public ReadingEntity registerTemperature(Long sensorId, Float temperature)
        throws NotFoundException {
        SensorEntity sensor = sensorService.getSensor(sensorId);

        ReadingEntity readingEntity = ReadingEntity.builder()
            .sensor(sensor)
            .temperature(temperature)
            .build();

        inDayRepository.save(readingEntity);

        return readingEntity;
    }

    @Override
    public Collection<ReadingEntity> getTemperaturesBySensorId(Long sensorId)
        throws NotFoundException {
        SensorEntity sensor = sensorService.getSensor(sensorId);

        return inDayRepository.findBySensorOrderById(sensor);
    }

    @Override
    public Collection<ReadingEntity> getTemperaturesBySensorIdAndFilter(
        Long sensorId, Instant from, Instant to) throws NotFoundException {
        SensorEntity sensor = sensorService.getSensor(sensorId);

        return inDayRepository.findByFilter(sensor, from, to);
    }


}
