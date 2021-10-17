package ru.sacmi.temperatureservice.repository;

import java.time.Instant;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sacmi.temperatureservice.entity.ReadingEntity;
import ru.sacmi.temperatureservice.entity.SensorEntity;

public interface ReadingRepository extends JpaRepository<ReadingEntity, Long> {

    Collection<ReadingEntity> findBySensorOrderById(SensorEntity sensor);

    @Query("SELECT r FROM ReadingEntity r\n" +
           "WHERE r.sensor = :sensor\n" +
           "AND (CAST(:fromTime AS date) IS NULL OR r.timestamp >= :fromTime)\n" +
           "AND (CAST(:toTime AS date) IS NULL OR r.timestamp <= :toTime)\n" +
           "ORDER BY r.id")
    Collection<ReadingEntity> findByFilter(
        @NotNull SensorEntity sensor, Instant fromTime, Instant toTime);
}