package ru.sacmi.temperatureservice.service.impl;

import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.sacmi.temperatureservice.entity.PushEntity;
import ru.sacmi.temperatureservice.entity.SensorEntity;
import ru.sacmi.temperatureservice.exception.NotFoundException;
import ru.sacmi.temperatureservice.repository.PushRepository;
import ru.sacmi.temperatureservice.service.PushService;
import ru.sacmi.temperatureservice.service.SensorService;
import ru.sacmi.temperatureservice.task.PushTask;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class PushServiceImpl implements PushService {

    PushRepository pushRepository;
    SensorService sensorService;
    RqueueMessageEnqueuer rqueueMessageEnqueuer;

    @Override
    public PushEntity registerToken(String token) {
        PushEntity pushEntity = PushEntity.builder().token(token).build();
        pushRepository.save(pushEntity);

        return pushEntity;
    }

    @Override
    public void sendMessage(Long sensorId, String message) throws NotFoundException {
        final String broadcast = "/topic/main";

        SensorEntity sensor = sensorService.getSensor(sensorId);
        List<PushEntity> targets = pushRepository.findAll();

        List<String> tokenList =
            targets.stream().map(PushEntity::getToken).collect(Collectors.toList());

        // TODO: придумать поизящнее
        this.createTask(
            PushTask.builder()
                .title("Уведомление от " + sensor.getLabel())
                .message(message)
                .target(broadcast)
                .build());

        for (String token : tokenList) {
            createTask(
                PushTask.builder()
                    .title("Уведомление от " + sensor.getLabel())
                    .message(message)
                    .target(token)
                    .build());
          }
    }

    private void createTask(PushTask pushTask) {
        rqueueMessageEnqueuer.enqueue("push-queue", pushTask);
    }
}
