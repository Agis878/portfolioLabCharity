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
    private final SimpleMailMessage template;

    @Value("${email.subject}")
    private String subject;

    public EmailServiceImpl(JavaMailSender mailSender, SimpleMailMessage template) {
        this.mailSender = mailSender;
        this.template = template;
    }

    public void sendSimpleMessage(Feedback feedback) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(feedback.getEmail());
            helper.setTo("agistest.85@gmail.com");
            helper.setSubject(subject);
            String messageText = "Adress Email:" + feedback.getEmail() + " Nazwa użytkownika: " + feedback.getName() + "\n\n" + feedback.getMessage();
            helper.setText(messageText);
            // FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            //  helper.addAttachment("Invoice", file);

            mailSender.send(message);
        } catch (MessagingException | MailException e) {

            e.printStackTrace();
        }
    }

//    public void sendSimpleMessage(Feedback feedback) {
//
//            SimpleMailMessage template = new SimpleMailMessage();
//            template.setTo("agistest.85@gmail.com");
//
//            template.setFrom(feedback.getEmail());
//            template.setSubject(subject);
//            template.setText(feedback.getMessage());
//            mailSender.send(template);
//          }
}
