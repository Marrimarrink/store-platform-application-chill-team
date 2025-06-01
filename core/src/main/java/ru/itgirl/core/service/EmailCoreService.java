package ru.itgirl.core.service;

public interface EmailCoreService {
    void sendActivationEmail(String toEmail, String activationLink);
}
