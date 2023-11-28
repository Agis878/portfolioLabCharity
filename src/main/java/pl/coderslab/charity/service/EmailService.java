package pl.coderslab.charity.service;


import pl.coderslab.charity.model.Feedback;

public interface EmailService {
    void sendMessageFromContactForm(Feedback feedback);

    void sendActivationEmail(String username, String activationCode);

    void sendResetPasswordActivationEmail(String toEmail, String activationLink);
}
