package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.UserNotFoundException;
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
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static User user;

    @BeforeAll
    static void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("anon@mail.com");
        user.setPassword("password");
    }

    @Test
    void shouldReturnUserWhenUserExists() {
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        User user = userService.getUser(1L);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getPassword()).isEqualTo("password");
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
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        UserDto userDto = userService.getUserDto(1L);

        assertThat(userDto.getId()).isEqualTo(1L);
        assertThat(userDto.getEmail()).isEqualTo("anon@mail.com");
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenDtoUserDoesNotExist() {
        given(userRepository.findById(2L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserDto(2L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User with id: 2 not found");
    }

}