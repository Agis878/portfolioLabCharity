package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.coderslab.charity.model.Feedback;
import pl.coderslab.charity.model.dto.RegisterDto;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

import static pl.coderslab.charity.model.mapper.UserMapper.mapToUser;


@Controller
@RequestMapping
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final EmailService emailService;
    @Value("${spring.mail.username}")
    private String emailAddress;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService, EmailService emailService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @ModelAttribute
    public void mailAddress(Model model) {
        model.addAttribute("emailAddress", emailAddress);
    }

    @ModelAttribute
    public void EmailForm(Model model) {
        model.addAttribute("feedback", new Feedback());
    }

    @GetMapping
    public String homeAction(Model model) {
        model.addAttribute("institutionList", institutionService.findAll());
        model.addAttribute("donationQty", donationService.sumAllQuantities());
        model.addAttribute("donationSum", donationService.countAllDonations());
        return "index";
    }

    @GetMapping("/register")
    public String registrationUserForm(Model model) {
        model.addAttribute("userDTO", new RegisterDto(null, null, null, null, null));
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@Valid @ModelAttribute("userDTO") RegisterDto userDto, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        }
        if (!userService.isUsernameUnique(userDto.username())) {
            errors.rejectValue("username", "error.username.exists", "Username already exists");
            return "register";
        }
        if (!userDto.password().equals(userDto.passwordConfirm())) {
            errors.rejectValue("passwordConfirm", "error.password.mismatch", "Passwords do not match");
            return "register";
        }
        String activationCode = userService.generateActivationCode();
        userService.save(mapToUser(userDto, activationCode));

        String activationLink = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/activate")
                .queryParam("code", activationCode)
                .toUriString();
        emailService.sendActivationEmail(userDto.username(), activationLink);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/logout")
    public String getLogoutView() {
        return "redirect:/login";
    }
}
