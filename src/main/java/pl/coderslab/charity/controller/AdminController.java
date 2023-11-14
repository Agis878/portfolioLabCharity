package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationService donationService;


    public AdminController(UserService userService, InstitutionService institutionService, DonationService donationService) {
        this.userService = userService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping
    public String getAdminView(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        model.addAttribute("adminQty", userService.countAllByRole("ROLE_ADMIN"));
        model.addAttribute("userQty", userService.countAllByRole("ROLE_USER"));
        User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
        model.addAttribute("loggedUser", loggedUser);
        return "admin/admin";
    }

    @GetMapping("/admins")
    public String showAdminsList(Model model) {
        model.addAttribute("adminList", userService.findAllByRole("ROLE_ADMIN"));

        return "admin/table_admins";
    }

    @GetMapping("/users")
    public String showUsersList(Model model) {
        model.addAttribute("userList", userService.findAllByRole("ROLE_USER"));
        return "admin/table_users";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteView(Model model, @PathVariable Long id) {
        Optional<User> userToDelete = userService.findById(id);
        if (userToDelete.isPresent()) {
            model.addAttribute("userToDelete", userToDelete.get());
            model.addAttribute("userRole", userToDelete.get().getRole());
            return "admin/user_delete";
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        Optional<User> deletedUser = userService.findById(id);
        boolean deleteSuccess = userService.deleteUserById(id);
        if (!deleteSuccess) {
            model.addAttribute("error", "Nie można usunąć ostatniego administratora");
        }
        String redirectPath = deletedUser.get().getRole().equals("ROLE_ADMIN") ? "redirect:/admin/admins" : "redirect:/admin/users";
        return redirectPath;
    }

    @GetMapping("/update/{id}")
    public String getUpdateView(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user_update";
    }

    @PostMapping("/update")
    public String updateAdmin(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            return "admin/user_update";
        } else {

            userService.save(user);
            if (user.getRole().equals("ROLE_ADMIN")) {
                return "redirect:/admin/admins";
            } else {
                return "redirect:/admin/users";
            }
        }
    }
//    @GetMapping("/update/{id}")
//    public String getUpdateView(Model model, @PathVariable Long id) {
//        model.addAttribute("user", userService.findById(id));
//        return "admin/user_update";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateAdmin(@Valid User user, BindingResult bindingResult, Model model, @PathVariable Long id) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("user", user);
//            model.addAttribute("errors", bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
//            return "admin/user_update";
//        }
//
//        userService.save(user);
//
//            if (user.getRole().equals("ROLE_ADMIN")) {
//                model.addAttribute("adminList", userService.findAllByRole("ROLE_ADMIN"));
//                return "redirect:/admin/admins";
//            } else {
//                model.addAttribute("userList", userService.findAllByRole("ROLE_USER"));
//                return "redirect:/admin/users";
//            }
//        }


    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user_create";

    }

    @PostMapping("/users/add")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            return "admin/user_create";
        } else {
            userService.save(user);
            return "redirect:/admin";
        }
    }


}

