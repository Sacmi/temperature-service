package ru.sacmi.temperatureservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.sacmi.temperatureservice.dto.sensor.SensorUpdateDto;
import ru.sacmi.temperatureservice.entity.SensorEntity;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SensorEntity updateSensorEntityFromDto(SensorUpdateDto sensorUpdateDto, @MappingTarget SensorEntity sensorEntity);
}
