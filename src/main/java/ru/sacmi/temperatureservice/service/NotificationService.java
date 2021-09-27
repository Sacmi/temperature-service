package ru.sacmi.temperatureservice.service;

import java.util.Collection;

public interface NotificationService {

    void send(String target, String title, String message);

    void sendMultiple(Collection<String> targets, String title, String message);
}
