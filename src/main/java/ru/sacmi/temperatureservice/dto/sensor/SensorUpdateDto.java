package ru.sacmi.temperatureservice.dto.sensor;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SensorUpdateDto {

    @Null
    Long id;

    Float maxTemp;

    Float minTemp;

    @Positive
    Integer sendDelay;

    @Positive
    Integer updateDelay;

    String label;
}
