package com.pk.petrolstationmonolith.auth;

import com.pk.petrolstationmonolith.repositories.account.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return customerRepository.findByEmail(username)
                .orElseGet(
                        () -> {
                            long id;
                            try {
                                id = Long.parseLong(username);
                            } catch (NumberFormatException e) {
                                throw new UsernameNotFoundException("User with username: " + username + " not found.");
                            }
                            return customerRepository.findById(id)
                                    .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found."));

                        }
                );

    }

}
