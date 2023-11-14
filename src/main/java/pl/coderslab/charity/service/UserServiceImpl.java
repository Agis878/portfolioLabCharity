package pl.coderslab.charity.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public List<User> findAllByRole(String role) {
        return userRepository.findAllByRole(role);
    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        if (user.getId() != null) {
            // If user has an ID, it means it already exists, so update it
            Optional<User> existingUser = userRepository.findById(user.getId());
            if (existingUser.isPresent()) {
                User oldUser = existingUser.get();
                oldUser.setUsername(user.getUsername());
                oldUser.setRole(user.getRole());
                oldUser.setActive(user.getActive());


                userRepository.save(oldUser);
            }
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            user.setActive(true);
            userRepository.save(user);
        }
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    /**
     * Retrieves a user by their username along with associated reservations.
     */
    public User getUserWithDonationsByUserName(String name) {
        return userRepository.getUserWithDonationsByUsername(name);
    }

    /**
     * Checks if a username is unique.
     *
     * @param username The username to be checked.
     * @return True if the username is unique; otherwise, false.
     */
    public boolean isUsernameUnique(String username) {
        User existingUser = userRepository.getByUsername(username);
        return existingUser == null;
    }

    @Override
    public boolean deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userToDelete = user.get();
            if (userToDelete.getRole().equals("ROLE_ADMIN")) {
                Long adminCount = userRepository.countAllByRole("ROLE_ADMIN");
                if (adminCount > 1) {
                    userRepository.deleteById(id);
                } else {
                    return false; // Nie usuwaj ostatniego administratora
                }
            } else {
                userRepository.deleteById(id);
            }
            return true; // Usunięcie zakończone sukcesem
        }
        return false;
    }

    @Override
    public Long countAllByRole(String role) {
        return userRepository.countAllByRole(role);
    }
}
