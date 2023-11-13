package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;


@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @GetMapping
    public String homeAction(Model model){
        model.addAttribute("institutionList", institutionService.findAll());
        model.addAttribute("donationQty", donationService.sumAllQuantities());
        model.addAttribute("donationSum", donationService.countAllDonations());
        return "index";
    }

    @GetMapping("/register")
    public String registrationUserForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@Validated User user, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        }

        // Sprawdź, czy hasło się zgadza z potwierdzeniem hasła
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "error.password.mismatch", "Passwords do not match");
            return "register";
        }

        // Sprawdź, czy nazwa użytkownika jest unikalna
        if (!userService.isUsernameUnique(user.getUsername())) {
            errors.rejectValue("username", "error.username.exists", "Username already exists");
            return "register";
        }

        userService.save(user);
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
