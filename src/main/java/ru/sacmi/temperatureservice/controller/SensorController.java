package ru.sacmi.temperatureservice.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sacmi.temperatureservice.entity.SensorEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;
import ru.sacmi.temperatureservice.service.SensorService;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestController()
@RequestMapping("sensor")
public class SensorController {

    SensorService sensorService;

    @GetMapping
    public ResponseEntity<Collection<SensorEntity>> getAllSensors() {
        return ResponseEntity.ok(sensorService.getAllSensors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorEntity> getSensor(@PathVariable long id) throws NotFoundException {
        return ResponseEntity.ok(sensorService.getSensor(id));
    }

    @PostMapping("/{id}")
    public void changeSensorLabel(@PathVariable long id, @NotEmpty @RequestParam String label)
        throws NotFoundException {
        sensorService.changeSensorName(id, label);
    }

    @PostMapping("/")
    public ResponseEntity<Long> registerSensor(@NotEmpty @RequestParam String uuid) {
        return ResponseEntity.ok(sensorService.registerSensor(uuid).getId());
    }
}
