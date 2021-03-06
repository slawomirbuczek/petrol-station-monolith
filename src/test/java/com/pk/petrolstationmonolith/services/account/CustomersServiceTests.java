package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.exceptions.account.user.UserNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomersServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static Customers customers;

    @BeforeAll
    static void setUp() {
        customers = new Customers(1L, "password", "email@email.com", Roles.USER_INDIVIDUAL);
    }

    @Test
    void shouldReturnUserWhenUserExists() {
        given(userRepository.findById(1L)).willReturn(Optional.of(CustomersServiceTests.customers));

        Customers customers = userService.getUser(1L);

        assertThat(customers.getId()).isEqualTo(1L);
        assertThat(customers.getPassword()).isEqualTo("password");
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        given(userRepository.findById(2L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUser(2L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User with id: 2 not found");
    }

    @Test
    void shouldReturnUserDtoWhenUserExists() {
        given(userRepository.findById(1L)).willReturn(Optional.of(customers));

        UserDto userDto = userService.getUserDto(1L);

        assertThat(userDto.getId()).isEqualTo(1L);
        assertThat(userDto.getEmail()).isEqualTo("email@email.com");
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenDtoUserDoesNotExist() {
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserDto(1L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User with id: 1 not found");
    }

    @Test
    void shouldReturnUserWhenUserWithEmailExists() {
        given(userRepository.findByEmail("email@email.com")).willReturn(Optional.of(CustomersServiceTests.customers));

        Customers customers = userService.getUserByEmail("email@email.com");

        assertThat(customers.getId()).isEqualTo(1L);
        assertThat(customers.getPassword()).isEqualTo("password");
        assertThat(customers.getEmail()).isEqualTo("email@email.com");
    }

    @Test
    void shouldThrowUseNotFoundExceptionWhenUserWithEmailDoesNotExist() {
        given(userRepository.findByEmail("email@email.com")).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserByEmail("email@email.com"))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User with id: email@email.com not found");
    }

}
