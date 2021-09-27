package ru.sacmi.temperatureservice.dto.fcm;


public class FcmRequestDto {

    public static FcmRequestDto create(String token, String title, String body) {
        FcmNotificationDto fcmNotificationDto = new FcmNotificationDto();
        fcmNotificationDto.title = title;
        fcmNotificationDto.body = body;

        FcmRequestDto fcmRequestDto = new FcmRequestDto();
        fcmRequestDto.notification = fcmNotificationDto;
        fcmRequestDto.to = token;

        return fcmRequestDto;
    }



    public FcmNotificationDto notification;
    public String to;
}
