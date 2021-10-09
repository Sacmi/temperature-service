package ru.sacmi.temperatureservice.task;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PushTask {

    String title;
    String message;
    String target;
}
