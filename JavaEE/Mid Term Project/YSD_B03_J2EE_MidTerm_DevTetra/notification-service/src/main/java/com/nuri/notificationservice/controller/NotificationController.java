package com.nuri.notificationservice.controller;

import com.nuri.notificationservice.dto.NotificationDto;
import com.nuri.notificationservice.dto.NotificationPreferenceDto;
import com.nuri.notificationservice.exception.AlreadyExistsException;
import com.nuri.notificationservice.exception.ValueNotFoundException;
import com.nuri.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/preference")
    public String addNotificationPreference(@RequestBody NotificationPreferenceDto notificationPreferenceDto) throws AlreadyExistsException {
        notificationService.addNotificationPreference(notificationPreferenceDto);
        return "Preference added to database.";
    }

    @PutMapping("/preference/update")
    public String updateNotificationPreference(@RequestBody NotificationPreferenceDto notificationPreferenceDto) throws ValueNotFoundException {
        notificationService.updateNotificationPreference(notificationPreferenceDto);
        return "Preference updated.";
    }

    @PostMapping("/send")
    public String sendNotification(){
        notificationService.sendNotification();
        return "Notification has been sent to user.";
    }

    @GetMapping("/view/{notificationId}")
    public NotificationDto viewNotification(@PathVariable Long notificationId ) throws ValueNotFoundException {
        return notificationService.viewNotification(notificationId);
    }

}
