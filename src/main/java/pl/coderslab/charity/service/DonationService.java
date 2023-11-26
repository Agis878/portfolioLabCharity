package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;

import java.util.List;

public interface DonationService {

    List<Donation> findAll();

    List<Donation> findAllByUser(User user);
    Integer sumAllQuantities();
    Long countAllDonations();

    void save(Donation donation);

    List<Category> getCategoriesForDonation(Long donationId);

}
