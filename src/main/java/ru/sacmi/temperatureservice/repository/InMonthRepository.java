package ru.sacmi.temperatureservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sacmi.temperatureservice.entity.InMonthEntity;

public interface InMonthRepository extends JpaRepository<InMonthEntity, Long> {

}