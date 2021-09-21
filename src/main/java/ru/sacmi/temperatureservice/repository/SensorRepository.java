package ru.sacmi.temperatureservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sacmi.temperatureservice.entity.SensorEntity;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<SensorEntity, Long> {

    Optional<SensorEntity> findByUuid(String uuid);
}