package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.InvalidPasswordException;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.password.RequestUpdatePassword;
import com.pk.petrolstationmonolith.repositories.account.EmailTokenRepository;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import com.pk.petrolstationmonolith.services.mail.MailService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private EmailTokenRepository emailTokenRepository;

    @Mock
    private MailService mailService;

    @Spy
    private static PasswordEncoder encoder;

    @InjectMocks
    private PasswordService passwordService;

    private static User user;

    private static Principal principal;

    @BeforeAll
    static void setUp() {
        encoder = new BCryptPasswordEncoder();
        user = new User();
        user.setId(1L);
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        principal = () -> "1";
    }

    @Test
    void shouldUpdatePasswordWhenUserOldPasswordIsCorrect() {
        given(userService.getUser("1")).willReturn(user);

        RequestUpdatePassword request = new RequestUpdatePassword("password", "newPassword");

        ResponseMessage response = passwordService.updatePassword(request, principal);

        assertThat(response.getMessage()).contains("Password successfully updated.");

    }

    @Test
    void shouldThrowInvalidPasswordExceptionWhenUserOldPasswordIsIncorrect() {
        given(userService.getUser("1")).willReturn(user);

        RequestUpdatePassword request = new RequestUpdatePassword("WRONG", "newPassword");

        assertThatThrownBy(() -> passwordService.updatePassword(request, principal))
                .isInstanceOf(InvalidPasswordException.class)
                .hasMessage("Invalid password");

    }


}
