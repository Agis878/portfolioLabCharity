package pl.coderslab.charity.service;


import pl.coderslab.charity.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    List<User> findAllByRole(String role);

    Optional<User> findById(Long id);

    void save(User user);

    void update(User user, User newUser);

    User getByUsername(String name);

    boolean isUsernameUnique(String username);

    boolean deleteUserById(Long id);

    Long countAllByRole(String role);

    void changePassword(User user, User userUpdated);
}
