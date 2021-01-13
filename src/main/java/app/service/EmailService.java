package app.service;

import app.entity.User;

public interface EmailService {
    void sendEmail(User user);
}
