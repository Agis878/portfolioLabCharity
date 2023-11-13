package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserView(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
        model.addAttribute("loggedUser", loggedUser);
        return "user/user";
    }
}
