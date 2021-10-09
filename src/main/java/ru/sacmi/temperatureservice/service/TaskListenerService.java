package ru.sacmi.temperatureservice.service;

import ru.sacmi.temperatureservice.task.PushTask;

public interface TaskListenerService {

    void onPushTask(PushTask pushTask);
}
