package ru.sacmi.temperatureservice.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sacmi.temperatureservice.dto.push.PushDto;
import ru.sacmi.temperatureservice.entity.PushEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;
import ru.sacmi.temperatureservice.service.PushService;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestController()
@RequestMapping("push")
public class PushController {

    PushService pushService;

    @PostMapping
    public ResponseEntity<String> sendPush(@RequestBody PushDto pushDto) throws NotFoundException {
        pushService.sendMessage(pushDto.getId(), pushDto.getMessage());
        return ResponseEntity.ok("fine");
    }

    @PostMapping("{token}")
    public ResponseEntity<PushEntity> registerToken(@PathVariable String token) {
        return ResponseEntity.ok(pushService.registerToken(token));
    }
}
