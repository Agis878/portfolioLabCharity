package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("user")
public class UserDonationController {
    private final UserService userService;
    private final DonationService donationService;

    public UserDonationController(UserService userService, DonationService donationService) {
        this.userService = userService;
        this.donationService = donationService;
    }

    @GetMapping("/donations")
    public String donationListView(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("donationList", donationService.findAllByUser(loggedUser));
        return "user/donation-view";
    }
}

