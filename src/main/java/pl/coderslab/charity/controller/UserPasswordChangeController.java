package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Feedback;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.dto.PasswordUpdateDto;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

import static pl.coderslab.charity.model.mapper.UserPasswordMapper.mapToUserPassword;

@Controller
@RequestMapping("user")
public class UserPasswordChangeController {
    private final UserService userService;

    public UserPasswordChangeController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void EmailForm(Model model) {
        model.addAttribute("feedback", new Feedback());
    }

    @GetMapping("/update/passwordChange")
    public String updatePasswordForm(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("passwordUpdateDTO", new PasswordUpdateDto(null, null));
        return "user/loggedUser-password-update";
    }

    @PostMapping("/update/passwordChange")
    public String updatePassword(@AuthenticationPrincipal UserDetails authenticatedUser,
                                 @Valid @ModelAttribute("passwordUpdateDTO") PasswordUpdateDto passwordUpdateDto,
                                 Errors errors
    ) {
        if (errors.hasErrors()) {
            return "user/loggedUser-password-update";
        }
        if (!passwordUpdateDto.password().equals(passwordUpdateDto.passwordConfirm())) {
            errors.rejectValue("passwordConfirm", "error.password.mismatch", "Passwords do not match");
            return "user/loggedUser-password-update";
        }
        User userUpdated = userService.getByUsername(authenticatedUser.getUsername());
        User user = mapToUserPassword(passwordUpdateDto);

        userService.changePassword(user, userUpdated);
        return "redirect:/user";
    }
}

