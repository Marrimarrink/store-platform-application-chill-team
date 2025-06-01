package ru.itgirl.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.itgirl.core.service.EmailCoreService;

@Service
@RequiredArgsConstructor
public class EmailCoreServiceImpl implements EmailCoreService {
    private final JavaMailSender mailSender;

    @Override
    public void sendActivationEmail(String toEmail, String activationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("chill.team.store.app@yandex.ru");
        message.setTo(toEmail);
        message.setSubject("Активация аккаунта");
        message.setText("Для активации вашего аккаунта перейдите по ссылке: " + activationLink);
        mailSender.send(message);
    }
}
