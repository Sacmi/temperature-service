package ru.sacmi.temperatureservice.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.sacmi.temperatureservice.entity.SensorEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;
import ru.sacmi.temperatureservice.repository.SensorRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class SensorService {

    SensorRepository sensorRepository;

    public SensorEntity registerSensor(String uuid) {
        Optional<SensorEntity> sensor = sensorRepository.findByUuid(uuid);

        return sensor.orElseGet(() -> createSensor(uuid));
    }

    SensorEntity createSensor(String uuid) {
        SensorEntity sensorEntity = SensorEntity.builder()
            .uuid(uuid)
            .label("[" + LocalDateTime.now() + "] Сенсор")
            .build();

        sensorRepository.save(sensorEntity);
        return sensorEntity;
    }

    public SensorEntity getSensor(Long id) throws NotFoundException {
        return sensorRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Такой сенсор не зарегистрирован."));
    }

    public Collection<SensorEntity> getAllSensors() {
        return sensorRepository.findAll();
    }

    public void changeSensorName(Long id, String label) throws NotFoundException {
        SensorEntity sensor = getSensor(id);
        sensor.setLabel(label);

        sensorRepository.save(sensor);
    }
}