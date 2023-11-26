package pl.coderslab.charity.mail;


public interface EmailService {
    void sendMessageFromContactForm(Feedback feedback);

    void sendActivationEmail(String username, String activationCode);
}
