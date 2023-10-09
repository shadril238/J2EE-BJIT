package com.nuri.notificationservice.service;

import com.nuri.notificationservice.dto.NotificationDto;
import com.nuri.notificationservice.dto.NotificationPreferenceDto;
import com.nuri.notificationservice.exception.AlreadyExistsException;
import com.nuri.notificationservice.exception.ValueNotFoundException;

public interface NotificationService {

    void addNotificationPreference(NotificationPreferenceDto notificationPreferenceDto) throws AlreadyExistsException;
    void updateNotificationPreference(NotificationPreferenceDto notificationPreferenceDto) throws ValueNotFoundException;
    void sendNotification();
    NotificationDto viewNotification(Long notificationId) throws ValueNotFoundException;

}
