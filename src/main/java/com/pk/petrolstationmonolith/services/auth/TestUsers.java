package com.pk.petrolstationmonolith.services.auth;

import com.pk.petrolstationmonolith.entities.user.User;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestUsers {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TestUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void addTestUsers() {

        userRepository.save(new User(1L, "jan", passwordEncoder.encode("kowalski"), Roles.ADMIN));

        userRepository.save(new User(2L, "tomasz", passwordEncoder.encode("nowak"), Roles.CASHIER));
        userRepository.save(new User(3L, "karol", passwordEncoder.encode("kowalczyk"), Roles.CASHIER));

        userRepository.save(new User(4L, "wincenty", passwordEncoder.encode("raczek"), Roles.MONITORING));
        userRepository.save(new User(5L, "eugeniusz", passwordEncoder.encode("zuczek"), Roles.MONITORING));

        userRepository.save(new User(6L, "alojzy", passwordEncoder.encode("mydlak"), Roles.CAR_WASH));
        userRepository.save(new User(7L, "baltazar", passwordEncoder.encode("gabka"), Roles.CAR_WASH));

        userRepository.save(new User(8L, "czeslaw", passwordEncoder.encode("ratajczak"), Roles.LPG_SERVICE));
        userRepository.save(new User(9L, "radoslaw", passwordEncoder.encode("rataj"), Roles.LPG_SERVICE));

        userRepository.save(new User(10L, "Anon", passwordEncoder.encode("password"), Roles.USER));

    }

}
