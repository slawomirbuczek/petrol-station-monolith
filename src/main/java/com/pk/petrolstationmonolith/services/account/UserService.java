package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.UserNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        modelMapper = new ModelMapper();
    }

    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User getUser(String userId) {
        return getUser(Long.parseLong(userId));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public UserDto getUserDto(long userId) {
        return modelMapper.map(getUser(userId), UserDto.class);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean emailAlreadyTaken(String email) {
        return userRepository.existsByEmail(email);
    }


}
