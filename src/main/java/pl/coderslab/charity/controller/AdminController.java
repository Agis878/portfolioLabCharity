package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Feedback;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    @Value("${spring.mail.username}")
    private String emailAddress;

    public AdminController(UserService userService, InstitutionService institutionService, DonationService donationService) {
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
    public String getUserDeleteView(Model model, @PathVariable Long id) {
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
    public String deleteUser(@PathVariable Long id, Model model, Principal principal) {
        Optional<User> deletedUser = userService.findById(id);
        if (deletedUser.isPresent()) {
            User userToDelete = deletedUser.get();

            String username = principal.getName();
            if (username.equals(userToDelete.getUsername())) {
                model.addAttribute("error", "Nie możesz usunąć samego siebie");
                return "error-page";
            }

            boolean deleteSuccess = userService.deleteUserById(id);
            if (!deleteSuccess) {
                model.addAttribute("error", "Nie można usunąć ostatniego administratora");
                return "error-page";
            }
        }
        String redirectPath = deletedUser.get().getRole().equals("ROLE_ADMIN") ? "redirect:/admin/admins" : "redirect:/admin/users";
        return redirectPath;
    }

    @GetMapping("/update/{id}")
    public String getUserUpdateView(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user_update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid User user, BindingResult bindingResult, Model model) {

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

    @GetMapping("/institutions")
    public String showInstitutionsList(Model model) {
        model.addAttribute("institutionList", institutionService.findAll());
        model.addAttribute("institutionQty", institutionService.countAll());
        return "admin/table_institutions";
    }

    @GetMapping("/institutions/update/{id}")
    public String getUpdateInstitutionView(Model model, @PathVariable Long id) {
        model.addAttribute("institutionToUpdate", institutionService.findById(id));
        return "admin/institution_update";
    }

    @PostMapping("/institutions/update")
    public String updateInstitution(@Valid Institution institution, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            return "admin/institution_update";
        } else {
            institutionService.save(institution);
            return "redirect:/admin/institutions";
        }
    }

    @GetMapping("/institutions/delete/{id}")
    public String getInstitutionDeleteView(Model model, @PathVariable Long id) {
        Optional<Institution> institutionToDelete = institutionService.findById(id);
        if (institutionToDelete.isPresent()) {
            model.addAttribute("institutionToDelete", institutionToDelete.get());
            return "admin/institution_delete";
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping("institutions/delete/{id}")
    public String deleteInstitution(@PathVariable Long id, Model model) {
        boolean deleteSuccess = institutionService.deleteById(id);
        if (!deleteSuccess) {
            model.addAttribute("error", "Nie można usunąć ostatniego administratora");
        }
        return "redirect:/admin/institutions";
    }

    @GetMapping("/institutions/add")
    public String addInstitutionForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institution_create";
    }

    @PostMapping("/institutions/add")
    public String addInstitution(@Valid Institution institution, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            return "admin/institution_create";
        }
        institutionService.save(institution);
        return "redirect:/admin";
    }
}





