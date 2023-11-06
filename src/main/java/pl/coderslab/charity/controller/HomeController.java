package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;


@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }


    @GetMapping
    public String homeAction(Model model){
        model.addAttribute("institutionList", institutionService.findAll());
        model.addAttribute("donationQty", donationService.sumAllQuantities());
        model.addAttribute("doantionSum", donationService.countAllDonations());
        return "index";
    }


}
