package ru.sacmi.temperatureservice.controller;

import java.time.Instant;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sacmi.temperatureservice.entity.ReadingEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;
import ru.sacmi.temperatureservice.service.ReadingService;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestController()
@RequestMapping("reading")
public class ReadingController {

    ReadingService readingService;

    @PostMapping("/{id}")
    public ResponseEntity<ReadingEntity> registerTemperature(@PathVariable long id,
        @RequestParam float temperature) throws NotFoundException {
        return ResponseEntity.ok(readingService.registerTemperature(id, temperature));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection<ReadingEntity>> getTemperaturesBySensorId(
        @PathVariable long id)
        throws NotFoundException {
        return ResponseEntity.ok(readingService.getTemperaturesBySensorId(id));
    }

    @GetMapping("/{id}/filter")
    public ResponseEntity<Collection<ReadingEntity>> getTemperaturesBySensorIdAndFilter(
        @PathVariable long id,
        @RequestParam(defaultValue = "", name = "from") String fromString,
        @RequestParam(defaultValue = "", name = "to") String toString)
        throws NotFoundException {
        Instant from = fromString.isEmpty() ? null : Instant.parse(fromString),
            to = toString.isEmpty() ? null : Instant.parse(toString);

        return ResponseEntity.ok(readingService.getTemperaturesBySensorIdAndFilter(id, from, to));
    }

}
