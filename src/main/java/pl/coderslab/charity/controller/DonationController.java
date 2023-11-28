package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/form")
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, UserService userService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @GetMapping
    public String showForm(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("institutionList", institutionService.findAll());
        model.addAttribute("donation", new Donation());

        if (authenticatedUser != null) {
            User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
            model.addAttribute("loggedUser", loggedUser);
        }
        return "form";
    }
    @PostMapping
    public String addDonation(@Valid Donation donation, Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        if (authenticatedUser != null) {
            User loggedUser = userService.getByUsername(authenticatedUser.getUsername());
            model.addAttribute("loggedUser", loggedUser);
        }
        donationService.save(donation);
        return "form-confirmation";
    }
}
