package com.nuri.notificationservice.service.Implementation;

import com.nuri.notificationservice.constants.AppConstants;
import com.nuri.notificationservice.dto.NotificationDto;
import com.nuri.notificationservice.dto.NotificationPreferenceDto;
import com.nuri.notificationservice.dto.UserDto;
import com.nuri.notificationservice.entity.Notification;
import com.nuri.notificationservice.entity.NotificationPreference;
import com.nuri.notificationservice.exception.AlreadyExistsException;
import com.nuri.notificationservice.exception.ValueNotFoundException;
import com.nuri.notificationservice.networkmanager.UserFeingClient;
import com.nuri.notificationservice.repository.NotificationPreferenceRepository;
import com.nuri.notificationservice.repository.NotificationRepository;
import com.nuri.notificationservice.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImplementation implements NotificationService {

    @Autowired
    private NotificationPreferenceRepository notificationPreferenceRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserFeingClient userFeingClient;
    @Autowired
    private EmailService emailService;

    private UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        return userFeingClient.userDetailsByEmail(userMail);
    }

    private Long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    public void addNotificationPreference(NotificationPreferenceDto notificationPreferenceDto) throws AlreadyExistsException {
        Optional<NotificationPreference> checkedPreferenceExists = notificationPreferenceRepository
                .findByUserId(getCurrentUserId());

        if(checkedPreferenceExists.isPresent()){
            throw new AlreadyExistsException("Already you have preference. You can update it.");
        }

        NotificationPreference notificationPreference = getNotificationPreference(notificationPreferenceDto);

        notificationPreferenceRepository.save(notificationPreference);
    }

    private NotificationPreference getNotificationPreference(NotificationPreferenceDto notificationPreferenceDto) {
        NotificationPreference notificationPreference = new NotificationPreference();

        notificationPreference.setUserId(getCurrentUserId());
        notificationPreference.setUserEmail(getCurrentUser().getEmail());
        notificationPreference.setDiet(notificationPreferenceDto.isDiet());
        notificationPreference.setExercise(notificationPreferenceDto.isExercise());
        notificationPreference.setMentalHealth(notificationPreferenceDto.isMentalHealth());
        notificationPreference.setSleep(notificationPreferenceDto.isSleep());
        return notificationPreference;
    }

    public void updateNotificationPreference(NotificationPreferenceDto notificationPreferenceDto) throws ValueNotFoundException {
        NotificationPreference notificationPreference = notificationPreferenceRepository
                .findByUserId(getCurrentUserId())
                .orElseThrow(() -> new ValueNotFoundException("Please add your preference first."));

        notificationPreference.setDiet(notificationPreferenceDto.isDiet());
        notificationPreference.setExercise(notificationPreferenceDto.isExercise());
        notificationPreference.setMentalHealth(notificationPreferenceDto.isMentalHealth());
        notificationPreference.setSleep(notificationPreferenceDto.isSleep());

        notificationPreferenceRepository.save(notificationPreference);
    }

    public void sendNotification(){
        List<NotificationPreference> notificationPreference = notificationPreferenceRepository.findAll();

        for (NotificationPreference preference:notificationPreference) {
            Notification notification = new Notification();

            String recipientEmail = preference.getUserEmail();
            if(preference.isDiet()){
                String emailSubject = "Diet Notice";
                String emailText = AppConstants.DIET_NOTIFICATION;
                emailService.sendEmail(recipientEmail, emailSubject, emailText);
            }
            if(preference.isExercise()){
                String emailSubject = "Exercise Notice";
                String emailText = AppConstants.EXERCISE_NOTIFICATION;
                emailService.sendEmail(recipientEmail, emailSubject, emailText);
            }
            if(preference.isMentalHealth()){
                String emailSubject = "Mental Health Notice";
                String emailText = AppConstants.MENTAL_HEALTH_NOTIFICATION;
                emailService.sendEmail(recipientEmail, emailSubject, emailText);
            }
            if(preference.isSleep()){
                String emailSubject = "Sleep Notice";
                String emailText = AppConstants.SLEEP_NOTIFICATION;
                emailService.sendEmail(recipientEmail, emailSubject, emailText);
            }

            notification.setUserId(preference.getUserId());
            notification.setDateTime(new Date());
            notification.setStatus("send");

            notificationRepository.save(notification);
        }
    }

    public NotificationDto viewNotification(Long notificationId) throws ValueNotFoundException {
        Notification notification = notificationRepository
                .findByUserIdAndId(getCurrentUserId(), notificationId)
                .orElseThrow(() -> new ValueNotFoundException("Value not found."));

        notification.setStatus("seen");
        notificationRepository.save(notification);

        return new ModelMapper().map(notification, NotificationDto.class);
    }

}
