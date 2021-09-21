package ru.sacmi.temperatureservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sacmi.temperatureservice.entity.InDayEntity;
import ru.sacmi.temperatureservice.entity.SensorEntity;

import java.util.Collection;

public interface InDayRepository extends JpaRepository<InDayEntity, Long> {

    Collection<InDayEntity> findBySensorOrderByDate(SensorEntity sensor);
}