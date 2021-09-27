package ru.sacmi.temperatureservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sacmi.temperatureservice.entity.PushEntity;

public interface PushRepository extends JpaRepository<PushEntity, Long> {


}