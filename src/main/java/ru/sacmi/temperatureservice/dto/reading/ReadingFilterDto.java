package ru.sacmi.temperatureservice.dto.reading;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReadingFilterDto {

    LocalDateTime from;
    LocalDateTime to;
}
