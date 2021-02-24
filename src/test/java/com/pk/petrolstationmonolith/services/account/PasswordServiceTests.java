package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.EmailToken;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.InvalidEmailTokenException;
import com.pk.petrolstationmonolith.exceptions.account.InvalidPasswordException;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.password.RequestNewPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestResetPassword;
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
import java.util.Optional;
import java.util.UUID;

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

    @Test
    void shouldReturnResponseMessageWhenUserWithEmailExists() {
        given(userService.getUserByEmail("email@email.com")).willReturn(user);

        RequestResetPassword request = new RequestResetPassword("email@email.com");

        String message = passwordService.sendPasswordResetMail(request).getMessage();

        assertThat(message).isEqualTo("Reset password email has been sent.");
    }

    @Test
    void shouldReturnResponseMessageWhenCredentialsAreCorrect() {
        UUID uuid = UUID.randomUUID();
        EmailToken emailToken = new EmailToken(1L, uuid, user);

        given(userService.getUserByEmail("email@email.com")).willReturn(user);
        given(emailTokenRepository.findByToken(uuid)).willReturn(Optional.of(emailToken));

        RequestNewPassword request = new RequestNewPassword("newPassowrd");

        ResponseMessage responseMessage = passwordService.setNewPassword(
                request, uuid.toString(), "email@email.com");

        assertThat(responseMessage.getMessage()).isEqualTo("New password has been set.");
    }

    @Test
    void shouldThrowInvalidEmailTokenExceptionWhenTokenIsInvalid() {
        UUID uuid = UUID.randomUUID();
        EmailToken emailToken = new EmailToken(1L, uuid, user);

        given(userService.getUserByEmail("email@email.com")).willReturn(user);
        given(emailTokenRepository.findByToken(uuid)).willReturn(Optional.empty());

        RequestNewPassword request = new RequestNewPassword("newPassowrd");

        assertThatThrownBy(() -> passwordService.setNewPassword(request, uuid.toString(), "email@email.com"))
                .isInstanceOf(InvalidEmailTokenException.class)
                .hasMessage("Invalid email token");
    }

    @Test
    void shouldThrowInvalidEmailTokenExceptionWhenEmailTokenUserIsDifferent() {
        UUID uuid = UUID.randomUUID();
        User emailTokenUser = new User();
        emailTokenUser.setId(2L);
        EmailToken emailToken = new EmailToken(1L, uuid, emailTokenUser);

        given(userService.getUserByEmail("email@email.com")).willReturn(user);
        given(emailTokenRepository.findByToken(uuid)).willReturn(Optional.of(emailToken));

        RequestNewPassword request = new RequestNewPassword("newPassowrd");

        assertThatThrownBy(() -> passwordService.setNewPassword(request, uuid.toString(), "email@email.com"))
                .isInstanceOf(InvalidEmailTokenException.class)
                .hasMessage("Invalid email token");
    }

}
