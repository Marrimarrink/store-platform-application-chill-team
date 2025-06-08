package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.itgirl.core.service.EmailCoreService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailCoreServiceImpl implements EmailCoreService {
    private final JavaMailSender mailSender;

    @Override
    public void sendActivationEmail(String toEmail, String activationLink) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("chill.team.store.app@yandex.ru");
            message.setTo(toEmail);
            message.setSubject("Активация аккаунта");
            message.setText("Для активации вашего аккаунта перейдите по ссылке: " + activationLink);
            mailSender.send(message);
        } catch (MailSendException e) {
            log.error("Error sending email: ", e);
        }
    }
}
