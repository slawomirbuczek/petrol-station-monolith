package com.pk.petrolstationmonolith.controllers.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.petrolstationmonolith.auth.UserDetailsServiceImpl;
import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.properties.auth.JwtProperties;
import com.pk.petrolstationmonolith.services.account.PasswordService;
import com.pk.petrolstationmonolith.services.account.RegistrationService;
import com.pk.petrolstationmonolith.services.account.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegistrationService registrationService;

    @MockBean
    private PasswordService passwordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private static JwtProperties jwtProperties;

    private static UserCredentials credentials;

    @BeforeAll
    static void setUp() {
        credentials = new UserCredentials("1", "password");
    }

    @Test
    void shouldReturnStatusUnauthorizedWhenCredentialsAreIncorrect() throws Exception {
        mvc.perform(
                post("/account/login")
                        .content(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(credentials)))

                .andExpect(status().isUnauthorized())
                .andExpect(header().doesNotExist(HttpHeaders.AUTHORIZATION));
    }

    @Test
    void shouldReturnStatusOkWhenCredentialsAreCorrect() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setPassword(passwordEncoder.encode("password"));

        given(userDetailsService.loadUserByUsername("1")).willReturn(user);
        given(jwtProperties.getExpirationTime()).willReturn(10L);

        mvc.perform(
                post("/account/login")
                        .content(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(credentials)))

                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldReturnUserDtoWhenUserHasRoleAdmin() throws Exception {
        UserDto userDto = new UserDto(1, "anon@gmail.com");

        given(userService.getUserDto(1)).willReturn(userDto);

        mvc.perform(
                get("/account/users/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldReturnStatusUnauthorizedWhenUserHasRoleUser() throws Exception {
        mvc.perform(
                get("/account/users/1")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isForbidden());
    }

}
