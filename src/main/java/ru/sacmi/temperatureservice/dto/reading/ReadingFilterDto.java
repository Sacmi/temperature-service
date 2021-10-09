package ru.sacmi.temperatureservice.dto.reading;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReadingFilterDto {

    LocalDateTime from;
    LocalDateTime to;
}
