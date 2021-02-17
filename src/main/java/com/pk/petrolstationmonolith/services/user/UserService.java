package com.pk.petrolstationmonolith.services.user;

import com.pk.petrolstationmonolith.repositories.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




}
