package com.digitopia.digitopiacasestudy.service;

import com.digitopia.digitopiacasestudy.exception.UserNotFoundException;
import com.digitopia.digitopiacasestudy.model.User;
import com.digitopia.digitopiacasestudy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        if (userCreateControl(user)) {
            normalizedString(user);
            user.setCreatedDate(new Date());
            this.userRepository.save(user);
            Optional<User> byEmail = this.userRepository.findByEmail(user.getEmail());
            user.setCreatedBy(byEmail.get().getId());
            this.userRepository.save(user);
            return user;
        }
        throw new IllegalArgumentException("An unexpected error occurred, please try again later or contact the system administrator.");
    }

    public void normalizedString(User user) {
        user.setNormalizedName(user.getFullName().toLowerCase()
                .replace('ı', 'i')
                .replace('ö', 'o')
                .replace('ü', 'u')
                .replace('ç', 'c')
                .replace('ş', 's')
                .replace('ğ', 'g')
                .replace(" ", "")
                .replaceAll("[^a-z]", "")
        );
    }

    public User updateByUserID(UUID id, String newEmail) {
        User user = userRepository.findById(id).get();
        user.setEmail(newEmail);
        userRepository.save(user);
        return userRepository.findById(id).get();
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public User deleteByEmail(String email)
    {
        User user = findByUserEmail(email);
        this.userRepository.deleteById(user.getId());
        return user;
    }



    public User findByID(UUID userID) {
        if (userID != null) {
            User user = this.userRepository.findById(userID)
                    .orElseThrow(
                            () -> new UserNotFoundException(String
                                    .format("The user with the requested ID %s could not be found.", userID.toString()))
                    );
            return user;
        } else
            throw new IllegalArgumentException
                    ("An unexpected error occurred, please try again later or contact the system administrator.");
    }

    public User findByUserEmail(String email) {
        if (email != null) {
            User user = this.userRepository.findByEmail(email)
                    .orElseThrow(
                            () -> new UserNotFoundException(String
                                    .format("The user with the requested email %s could not be found.", email))
                    );
            return user;
        } else
            throw new IllegalArgumentException
                    ("An unexpected error occurred, please try again later or contact the system administrator.");
    }

    private boolean userCreateControl(User user) {
        if (user != null && user.getFullName() != null && !user.getFullName().isEmpty()) {
            User controlledUser = this.userRepository.findByFullName(user.getFullName())
                    .orElse(null);
            return controlledUser == null ? true : false;
        }
        throw new IllegalArgumentException("An unexpected error occurred, please try again later or contact the system administrator.");
    }
}
