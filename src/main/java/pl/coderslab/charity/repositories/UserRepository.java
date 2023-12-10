package pl.coderslab.charity.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"donations"})
    User getUserWithDonationsByUsername(String username);

    List<User> findAllByRole(String role);

    Long countAllByRole(String role);

    User findByActivationCode(String activationCode);

    User findByResetPasswordCode(String resetPasswordCode);
}
