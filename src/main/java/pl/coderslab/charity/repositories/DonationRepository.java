package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("SELECT SUM (d.quantity) FROM Donation d")
    Integer sumAllQuantities();

    List<Donation> findAllByUser(User user);

}
