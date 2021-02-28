package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.EmailAlreadyTakenException;
import com.pk.petrolstationmonolith.exceptions.account.user.UserNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, ModelMapper mapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User getUser(String userId) {
        try {
            return getUser(Long.parseLong(userId));
        } catch (Exception e) {
            throw new UserNotFoundException(userId);
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public UserDto getUserDto(long userId) {
        return mapUserToDto(getUser(userId));
    }

    public User addUser(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyTakenException(user.getEmail());
        }
        return userRepository.save(user);
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(encodePassword(newPassword));
        userRepository.save(user);
    }

    public boolean passwordMatches(String encodedPassword, String password) {
        return encoder.matches(password, encodedPassword);
    }

    private String encodePassword(String password) {
        return encoder.encode(password);
    }

    private UserDto mapUserToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

}
