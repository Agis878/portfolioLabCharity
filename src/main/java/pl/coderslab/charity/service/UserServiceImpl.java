package pl.coderslab.charity.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.mapper.UserMapper;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
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
                oldUser.setFirstName(user.getFirstName());
                oldUser.setLastName(user.getLastName());
                oldUser.setRole(user.getRole());
                oldUser.setActive(user.getActive());
                userRepository.save(oldUser);
            }
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            user.setActive(false);
            userRepository.save(user);
        }
    }

    public void update(User user, User userUpdated) {

        userUpdated.setUsername(user.getUsername());
        userUpdated.setFirstName(user.getFirstName());
        userUpdated.setLastName(user.getLastName());
        userRepository.save(userUpdated);
    }

    public void changePassword(User user, User userUpdated) {
        userUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userUpdated);
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
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

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = authentication.getName();

            if (currentUsername.equals(userToDelete.getUsername())) {
                return false;
            }

            if (userToDelete.getRole().equals("ROLE_ADMIN")) {
                Long adminCount = userRepository.countAllByRole("ROLE_ADMIN");
                if (adminCount > 1) {
                    userRepository.deleteById(id);
                } else {
                    return false;
                }
            } else {
                userRepository.deleteById(id);
            }
            return true;
        }
        return false;
    }

    @Override
    public Long countAllByRole(String role) {
        return userRepository.countAllByRole(role);
    }

    public String generateActivationCode() {
        return UUID.randomUUID().toString();
    }

    @Override
    public User findByActivationCode(String activationCode) {
        return userRepository.findByActivationCode(activationCode);
    }

    @Override
    public boolean userExist(String username) {
        return userRepository.getByUsername(username) != null;
    }

    @Override
    public User findByResetPasswordCode(String resetPasswordCode) {
        return userRepository.findByResetPasswordCode(resetPasswordCode);
    }

    public void saveUserWithToken(User user, String resetPasswordCode) {
        user.setResetPasswordCode(resetPasswordCode);
        userRepository.save(user);
    }

}
