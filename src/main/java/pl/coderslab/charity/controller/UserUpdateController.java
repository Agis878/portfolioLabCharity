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
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.dto.UpdateDto;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

import static pl.coderslab.charity.model.mapper.UpdateMapper.mapToUpdateUser;

@Controller
@RequestMapping("user")
public class UserUpdateController {
    private final UserService userService;

    public UserUpdateController(UserService userService) {
        this.userService = userService;
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
}

