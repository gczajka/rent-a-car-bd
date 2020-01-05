package com.backend.rentacarbd.scheduler;

import com.backend.rentacarbd.domain.Email;
import com.backend.rentacarbd.repository.UserRepository;
import com.backend.rentacarbd.scheduler.config.AdminConfig;
import com.backend.rentacarbd.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderScheduler {
    private static final String SUBJECT = "Rent-a-car-service: Once a day email";

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminConfig adminConfig;

    public EmailSenderScheduler(EmailSenderService emailSenderService, UserRepository userRepository) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
    }

//    @Autowired
//    private AdminConfig adminConfig;

    @Scheduled(cron = "0 * * * * *")
    public void sendInformationEmail() {
        long size = userRepository.count();
        String user = (size == 1) ? " user" : " users";
        emailSenderService.send(new Email(adminConfig.getAdminMail(), SUBJECT, "Currently in database you got: " + size + user));
    }
}
