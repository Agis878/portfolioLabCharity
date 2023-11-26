package pl.coderslab.charity.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    //    private final SpringTemplateEngine templateEngine;
    private final SimpleMailMessage template;

    @Value("${spring.mail.username}")
    private String emailAddress;

    @Value("${email.subject}")
    private String subject;

    public EmailServiceImpl(JavaMailSender mailSender, SimpleMailMessage template) {
        this.mailSender = mailSender;
        this.template = template;
    }

    public void sendMessageFromContactForm(Feedback feedback) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(feedback.getEmail());
            helper.setTo("agistest.85@gmail.com");
            helper.setSubject(subject);
            String messageText = "Adress Email:" + feedback.getEmail() + " Nazwa użytkownika: " + feedback.getName() + "\n\n" + feedback.getMessage();
            helper.setText(messageText);

            mailSender.send(message);
        } catch (MessagingException | MailException e) {

            e.printStackTrace();
        }
    }

    public void sendActivationEmail(String toEmail, String activationLink) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("agistest.85@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Account Activation");


            String messageText = "Dziękujemy za rejestrację! Aktywuj swoje konto klikając w poniższy link:<br/>"
                    + "<a href=\"" + activationLink + "\">Aktywuj konto</a>";

            helper.setText(messageText, true);

            mailSender.send(message);
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
    }
}
