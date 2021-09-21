package ru.sacmi.temperatureservice.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sacmi.temperatureservice.entity.InDayEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;
import ru.sacmi.temperatureservice.service.InDayService;

import java.util.Collection;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestController()
@RequestMapping("day")
public class InDayController {

    InDayService inDayService;

    @PostMapping("/{id}")
    public ResponseEntity<InDayEntity> registerTemperature(@PathVariable long id,
        @RequestParam float temperature) throws NotFoundException {
        return ResponseEntity.ok(inDayService.registerTemperature(id, temperature));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collection<InDayEntity>> getTemperaturesBySensorId(@PathVariable long id)
        throws NotFoundException {
        return ResponseEntity.ok(inDayService.getTemperaturesBySensorId(id));
    }

}
