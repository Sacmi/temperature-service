package ru.sacmi.temperatureservice.service.impl;

import com.github.sonus21.rqueue.annotation.RqueueListener;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sacmi.temperatureservice.service.NotificationService;
import ru.sacmi.temperatureservice.service.TaskListenerService;
import ru.sacmi.temperatureservice.task.PushTask;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskListenerServiceImpl implements TaskListenerService {

    NotificationService notificationService;

    @Override
    @RqueueListener(value = "push-queue", concurrency = "2")
    public void onPushTask(PushTask pushTask) {
        log.info("Received push task to target " + pushTask.getTarget() + " with title \""
            + pushTask.getTitle() + "\"");
        notificationService.send(pushTask.getTitle(), pushTask.getMessage(), pushTask.getTarget());
        log.info("Push sent");
    }
}
