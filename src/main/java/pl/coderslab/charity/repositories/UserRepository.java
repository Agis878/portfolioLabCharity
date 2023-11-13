package pl.coderslab.charity.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their username.
     */
    User getByUsername(String username);


    /**
     * Retrieves a user by their username along with associated reservations using EntityGraph.
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"donations"})
    User getUserWithDonationsByUsername(String username);

    List<User> findAllByRole(String role);

    Long countAllByRole(String role);

}
