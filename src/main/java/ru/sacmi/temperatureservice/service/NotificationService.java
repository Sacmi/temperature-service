package ru.sacmi.temperatureservice.service;

public interface NotificationService {

    void send(String title, String message, String target);

//    void broadcast(String title, String message, Collection<String> targets);

    String getBroadcastTarget();
}
