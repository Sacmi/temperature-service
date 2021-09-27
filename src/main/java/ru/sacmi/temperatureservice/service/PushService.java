package ru.sacmi.temperatureservice.service;

import ru.sacmi.temperatureservice.entity.PushEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;

public interface PushService {

    PushEntity registerToken(String token);

    void sendMessage(Long sensorId, String message) throws NotFoundException;
}
