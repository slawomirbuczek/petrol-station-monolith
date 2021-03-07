package com.pk.petrolstationmonolith.controllers.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.petrolstationmonolith.auth.UserDetailsServiceImpl;
import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.models.account.CustomerCredentials;
import com.pk.petrolstationmonolith.properties.auth.JwtProperties;
import com.pk.petrolstationmonolith.services.account.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
class AuthenticationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @SpyBean
    private JwtProperties jwtProperties;

    private static CustomerCredentials credentials;

    @BeforeAll
    static void setUp() {
        credentials = new CustomerCredentials("1", "password");
    }

    @Test
    void shouldReturnStatusUnauthorizedWhenCredentialsAreIncorrect() throws Exception {
        mvc.perform(
                post("/account/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(credentials)))

                .andExpect(status().isUnauthorized())
                .andExpect(header().doesNotExist(HttpHeaders.AUTHORIZATION));
    }

    @Test
    void shouldReturnStatusOkWhenCredentialsAreCorrect() throws Exception {
        Customers customers = new Customers(1L, passwordEncoder.encode("password"), "email@email.com", Roles.CUSTOMER_INDIVIDUAL);

        given(userDetailsService.loadUserByUsername("1")).willReturn(customers);

        mvc.perform(
                post("/account/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(credentials)))

                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION));
    }

}
