package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.dto.PasswordUpdateDto;
import pl.coderslab.charity.service.EmailServiceImpl;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

import static pl.coderslab.charity.model.mapper.UserPasswordMapper.mapToUserPassword;

@Controller
public class ResetPasswordController {

    private final UserService userService;
    private final EmailServiceImpl emailService;

    public ResetPasswordController(UserService userService, EmailServiceImpl emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/forgotPassword")
    public String passwordResetForm() {
        return "reset-password-mail-form";
    }

    @PostMapping("/forgotPassword")
    public String processPasswordResetForm(@RequestParam String username, Model model) {
        User user = userService.getByUsername(username);
        if (user != null) {
            userService.saveUserWithToken(user, userService.generateActivationCode());

            String resetPasswordLink = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/resetPassword")
                    .queryParam("resetPasswordCode", user.getResetPasswordCode())
                    .toUriString();
            emailService.sendResetPasswordActivationEmail(username, resetPasswordLink);
            return "reset-password-link-sent";

        } else {
            model.addAttribute("error", "Użytkownik o danym mailu nie istnieje");
            return "error-page";
        }
    }

    @GetMapping("/resetPassword")
    public String ResetPasswordForm(Model model, @RequestParam String resetPasswordCode) {
        User existingUser = userService.findByResetPasswordCode(resetPasswordCode);
        model.addAttribute("resetPasswordCode", resetPasswordCode);
        model.addAttribute("existingUser", existingUser);
        if (existingUser != null) {
            model.addAttribute("resetPasswordDto", new PasswordUpdateDto(null, null));
            return "reset-password-form";
        } else {
            model.addAttribute("error", "Nieprawidłowy kod resetowania hasła");
            return "error-page";
        }
    }
    @PostMapping("/resetPassword")
    public String ResetPassword(@RequestParam String resetPasswordCode,
                                @Valid @ModelAttribute("passwordUpdateDTO") PasswordUpdateDto passwordUpdateDto,
                                Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "reset-password-form";
        }
        if (!passwordUpdateDto.password().equals(passwordUpdateDto.passwordConfirm())) {
            errors.rejectValue("passwordConfirm", "error.password.mismatch", "Passwords do not match");
            return "reset-password-form";
        }
        User userUpdated = userService.findByResetPasswordCode(resetPasswordCode);
        if (userUpdated != null) {
            User user = mapToUserPassword(passwordUpdateDto);
            userService.changePassword(user, userUpdated);
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Użytkownik o danym kodzie resetującym nie istnieje");
            return "error-page";
        }
    }
}
