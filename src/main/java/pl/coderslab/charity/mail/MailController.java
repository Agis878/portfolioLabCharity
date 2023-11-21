package pl.coderslab.charity.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;


@Controller
public class MailController {

    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String emailAddress;
    private final UserService userService;

    @Autowired
    public MailController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @ModelAttribute
    public void mailAddress(Model model) {
        model.addAttribute("emailAddress", emailAddress);
    }

    @ModelAttribute
    public void EmailForm(Model model) {
        model.addAttribute("feedback", new Feedback());
    }

    @GetMapping("/send-email-form")
    public String showEmailForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "header_footer/footer";
    }

    @PostMapping("/send-email-form")
    public String sendEmail(@AuthenticationPrincipal UserDetails authenticatedUser,
                            @Valid @ModelAttribute Feedback feedback,
                            BindingResult bindingResult,
                            Model model) {

        if (authenticatedUser != null) {
            User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
            model.addAttribute("loggedUser", loggedUser);
        }

        if (bindingResult.hasErrors()) {
            return "header_footer/footer";
        }
        try {
            emailService.sendSimpleMessage(feedback);
            model.addAttribute("successMessage", "Email sent successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error sending email: " + e.getMessage());
            return "email-result";
        }
        return "email-result";
    }
}
