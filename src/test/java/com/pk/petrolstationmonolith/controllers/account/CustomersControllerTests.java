package com.pk.petrolstationmonolith.controllers.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.petrolstationmonolith.auth.UserDetailsServiceImpl;
import com.pk.petrolstationmonolith.dtos.account.CustomerDto;
import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.RequestUpdateEmail;
import com.pk.petrolstationmonolith.properties.auth.JwtProperties;
import com.pk.petrolstationmonolith.services.account.CustomerService;
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

@WebMvcTest(CustomerController.class)
class CustomersControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserDetailsServiceImpl customerDetailsService;

    @SpyBean
    private JwtProperties jwtProperties;

    private static CustomerDto customerDto;
    private static Customers customers;

    @BeforeAll
    private static void setUp() {
        customers = new Customers(1L, "password", "mail@mail.com", Roles.CUSTOMER_INDIVIDUAL);
        customerDto = new CustomerDto(1L, "mail@mail.com", Roles.CUSTOMER_INDIVIDUAL);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnCustomerDtoOfTheGivenCustomerWhenCustomerIsAuthorizedToMakeRequest() throws Exception {
        given(customerService.getCustomerDto(1)).willReturn(customerDto);

        mvc.perform(
                get("/account/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(customerDto)));

    }

    @Test
    @WithMockUser(roles = "CUSTOMER_INDIVIDUAL")
    void shouldReturnStatusForbiddenWhenCustomerIsNotAuthorizedToMakeRequest() throws Exception {
        mvc.perform(
                get("/account/customers/1"))
                .andExpect(status().isForbidden())
                .andExpect(content().json(toJson(new ResponseMessage("Access is denied"))));
    }

    @Test
    @WithMockUser("1")
    void shouldReturnCustomerDtoOfTheCustomer() throws Exception {
        given(customerService.getCustomerDto(1)).willReturn(customerDto);

        mvc.perform(
                get("/account/customers"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(customerDto)));

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
    void shouldReturnDtoOfDisabledCustomer() throws Exception {
        given(customerService.disableCustomer(1)).willReturn(customerDto);

        mvc.perform(delete("/account/customers"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(customerDto)));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnDtoOfDisabledCustomerGivenByCustomerId() throws Exception {
        given(customerService.disableCustomer(1)).willReturn(customerDto);

        mvc.perform(delete("/account/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(customerDto)));
    }

    @Test
    @WithMockUser(roles = "CUSTOMER_INDIVIDUAL")
    void shouldReturnStatusForbiddenWhenCustomerIsNotAuthorizedToMakeDisableCustomerRequest() throws Exception {
        mvc.perform(delete("/account/customers/1"))
                .andExpect(status().isForbidden())
                .andExpect(content().json(toJson(new ResponseMessage("Access is denied"))));
    }

    private String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}
