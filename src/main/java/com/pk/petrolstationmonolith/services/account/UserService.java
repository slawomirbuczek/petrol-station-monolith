package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.UserNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User getUserFromAuth() {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }

}
