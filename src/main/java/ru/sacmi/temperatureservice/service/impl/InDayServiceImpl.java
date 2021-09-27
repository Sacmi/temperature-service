package ru.sacmi.temperatureservice.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.sacmi.temperatureservice.entity.InDayEntity;
import ru.sacmi.temperatureservice.entity.SensorEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;
import ru.sacmi.temperatureservice.repository.InDayRepository;
import ru.sacmi.temperatureservice.service.InDayService;
import ru.sacmi.temperatureservice.service.SensorService;

import java.util.Collection;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class InDayServiceImpl implements InDayService {

    InDayRepository inDayRepository;
    SensorService sensorService;

    @Override
    public InDayEntity registerTemperature(Long sensorId, Float temperature)
        throws NotFoundException {
        SensorEntity sensor = sensorService.getSensor(sensorId);

        InDayEntity inDayEntity = InDayEntity.builder()
            .sensor(sensor)
            .temperature(temperature)
            .build();

        inDayRepository.save(inDayEntity);

        return inDayEntity;
    }

    @Override
    public Collection<InDayEntity> getTemperaturesBySensorId(Long sensorId)
        throws NotFoundException {
        SensorEntity sensor = sensorService.getSensor(sensorId);

        return inDayRepository.findBySensorOrderByDate(sensor);
    }
}
