package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

@Controller
public class ActivationController {
    private final UserService userService;

    public ActivationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/activate")
    public String activateAccount(@RequestParam("code") String activationCode, Model model) {
        User user = userService.findByActivationCode(activationCode);
        if (user != null && !user.getActive().equals(true) && activationCode.equals(user.getActivationCode())) {
            user.setActive(true);
            userService.save(user);
            model.addAttribute("activationSuccess", true);
        } else {
            model.addAttribute("activationError", true);
        }
        return "activate";
    }
}
