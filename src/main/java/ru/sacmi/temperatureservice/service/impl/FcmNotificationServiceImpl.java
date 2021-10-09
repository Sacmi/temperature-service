package ru.sacmi.temperatureservice.service.impl;

import com.sun.istack.Nullable;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.sacmi.temperatureservice.dto.fcm.FcmRequestDto;
import ru.sacmi.temperatureservice.service.NotificationService;

import java.util.Collection;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class FcmNotificationServiceImpl implements NotificationService {

    final WebClient client = WebClient.builder().baseUrl("https://fcm.googleapis.com/fcm/send")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

    @Value("${firebase.cloud-messaging.server-key}")
    String serverKey;

    String getAuthorization() {
        return "key=" + serverKey;
    }

    @Override
    public void send(String title, String message, String target) {
        ResponseEntity<Void> response = client.post()
                .header(HttpHeaders.AUTHORIZATION, getAuthorization())
                .body(Mono.just(FcmRequestDto.create(target, title, message)), FcmRequestDto.class)
                .retrieve().toBodilessEntity().block();

        assert response != null;
        log.info("FCM Status is " + response.getStatusCodeValue());
    }

    @Override
    public String getBroadcastTarget() {
        return "main";
    }
}
