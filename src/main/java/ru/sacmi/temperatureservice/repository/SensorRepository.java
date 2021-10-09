package ru.sacmi.temperatureservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sacmi.temperatureservice.entity.SensorEntity;

public interface SensorRepository extends JpaRepository<SensorEntity, Long> {

    Optional<SensorEntity> findByUuid(String uuid);
}