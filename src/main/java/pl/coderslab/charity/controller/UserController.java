package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Value;
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
import pl.coderslab.charity.model.dto.UpdateDto;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

import static pl.coderslab.charity.model.mapper.UpdateMapper.mapToUpdateUser;
import static pl.coderslab.charity.model.mapper.UserPasswordMapper.mapToUserPassword;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @Value("${spring.mail.username}")
    private String emailAddress;

    public UserController(UserService userService, InstitutionService institutionService, DonationService donationService) {
        this.userService = userService;
        this.institutionService = institutionService;
        this.donationService = donationService;
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
    public String getUserView(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("institutionList", institutionService.findAll());
        model.addAttribute("donationQty", donationService.sumAllQuantities());
        model.addAttribute("donationSum", donationService.countAllDonations());
        return "user/user";
    }

    @GetMapping("/update")
    public String updateUserForm(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User userToUpdate = userService.getByUsername(authenticatedUser.getUsername());
        model.addAttribute("user", userToUpdate);
        model.addAttribute("updateDTO", new UpdateDto(null, null, null));
        return "user/loggedUser-update";
    }

    @PostMapping("/update")
    public String updateUser(@AuthenticationPrincipal UserDetails authenticatedUser,
                             @Valid @ModelAttribute("updateDTO") UpdateDto updateDto,
                             Errors errors
    ) {
        if (errors.hasErrors()) {
            return "user/loggedUser-update";
        }
        User userUpdated = userService.getByUsername(authenticatedUser.getUsername());

        User user = mapToUpdateUser(updateDto);
        if (!userService.isUsernameUnique(user.getUsername()) && !user.getUsername().equals(userUpdated.getUsername())) {
            errors.rejectValue("username", "error.username.exists", "Username already exists");
            return "user/loggedUser-update";
        }
        userService.update(user, userUpdated);
        return "redirect:/user";
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

    @GetMapping("/donations")
    public String donationListView(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("donationList", donationService.findAllByUser(loggedUser));
        return "user/donation-view";
    }
}

