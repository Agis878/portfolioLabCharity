package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Donation;

public interface DonationService {
    Integer sumAllQuantities();

    Long countAllDonations();

    Donation save(Donation donation);
}
