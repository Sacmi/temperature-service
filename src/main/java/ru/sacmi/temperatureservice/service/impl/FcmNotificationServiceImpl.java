package ru.sacmi.temperatureservice.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.sacmi.temperatureservice.dto.fcm.FcmRequestDto;
import ru.sacmi.temperatureservice.service.NotificationService;

import java.util.Collection;

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
    public void send(String target, String title, String message) {
        client.post()
            .header(HttpHeaders.AUTHORIZATION, getAuthorization())
            .body(Mono.just(FcmRequestDto.create(target, title, message)), FcmRequestDto.class)
            .retrieve().toBodilessEntity().block();
    }

    @Override
    public void sendMultiple(Collection<String> targets, String title, String message) {
        for (String token : targets) {
            send(token, title, message);
        }
    }
}
