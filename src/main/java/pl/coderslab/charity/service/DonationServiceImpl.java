package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.DonationRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public List<Donation> findAllByUser(User user) {
        return donationRepository.findAllByUser(user);
    }

    @Override
    public Integer sumAllQuantities() {
        return donationRepository.sumAllQuantities();
    }

    @Override
    public Long countAllDonations() {
        return donationRepository.count();
    }

    @Override
    public void save(Donation donation) {
    }

    public List<Category> getCategoriesForDonation(Long donationId) {
        Optional<Donation> optionalDonation = donationRepository.findById(donationId);

        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            return donation.getCategories();
        } else {
            // Obsłuż sytuację, gdy donacja o danym ID nie istnieje
            return Collections.emptyList();
        }
    }

}
