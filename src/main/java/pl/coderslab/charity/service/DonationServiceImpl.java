package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repositories.DonationRepository;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
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
    public Donation save(Donation donation) {
        return donationRepository.save(donation);
    }

}
