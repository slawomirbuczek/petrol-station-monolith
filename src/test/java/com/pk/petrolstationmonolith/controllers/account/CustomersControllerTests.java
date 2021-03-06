package com.pk.petrolstationmonolith.controllers.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.petrolstationmonolith.auth.UserDetailsServiceImpl;
import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.RequestUpdateEmail;
import com.pk.petrolstationmonolith.properties.auth.JwtProperties;
import com.pk.petrolstationmonolith.services.account.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class CustomersControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @SpyBean
    private JwtProperties jwtProperties;

    private static UserDto userDto;
    private static Customers customers;

    @BeforeAll
    private static void setUp() {
        customers = new Customers(1L, "password", "mail@mail.com", Roles.USER_INDIVIDUAL);
        userDto = new UserDto(1L, "mail@mail.com", Roles.USER_INDIVIDUAL);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUserDtoOfTheGivenUserWhenUserIsAuthorizedToMakeRequest() throws Exception {
        given(userService.getUserDto(1)).willReturn(userDto);

        mvc.perform(
                get("/account/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(userDto)));

    }

    @Test
    @WithMockUser(roles = "USER_INDIVIDUAL")
    void shouldReturnStatusForbiddenWhenUserIsNotAuthorizedToMakeRequest() throws Exception {
        mvc.perform(
                get("/account/users/1"))
                .andExpect(status().isForbidden())
                .andExpect(content().json(toJson(new ResponseMessage("Access is denied"))));
    }

    @Test
    @WithMockUser("1")
    void shouldReturnUserDtoOfTheUser() throws Exception {
        given(userService.getUserDto(1)).willReturn(userDto);

        mvc.perform(
                get("/account/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(userDto)));

    }

    @Test
    @WithMockUser("1")
    void shouldReturnStatusOkWhenRequestingSendUpdateEmailMail() throws Exception {
        mvc.perform(post("/account/email"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("1")
    void shouldReturnStatusOkWhenUpdatingEmail() throws Exception {
        RequestUpdateEmail request = new RequestUpdateEmail(UUID.randomUUID().toString(), "email@email.com");

        mvc.perform(put("/account/email")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(request)))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser("1")
    void shouldReturnDtoOfDisabledUser() throws Exception {
        given(userService.disableUser(1)).willReturn(userDto);

        mvc.perform(delete("/account/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(userDto)));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnDtoOfDisabledUserGivenByUserId() throws Exception {
        given(userService.disableUser(1)).willReturn(userDto);

        mvc.perform(delete("/account/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(userDto)));
    }

    @Test
    @WithMockUser(roles = "USER_INDIVIDUAL")
    void shouldReturnStatusForbiddenWhenUserIsNotAuthorizedToMakeDisableUserRequest() throws Exception {
        mvc.perform(delete("/account/users/1"))
                .andExpect(status().isForbidden())
                .andExpect(content().json(toJson(new ResponseMessage("Access is denied"))));
    }

    private String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}
