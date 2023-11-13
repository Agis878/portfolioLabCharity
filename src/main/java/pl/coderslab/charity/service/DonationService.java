package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Donation;

import java.util.List;

public interface DonationService {

    List<Donation> findAll();
    Integer sumAllQuantities();

    Long countAllDonations();

    Donation save(Donation donation);
}
