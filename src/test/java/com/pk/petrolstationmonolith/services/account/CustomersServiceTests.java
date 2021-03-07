package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.CustomerDto;
import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.exceptions.account.customer.CustomerNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.CustomerRepository;
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
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private static Customers customers;

    @BeforeAll
    static void setUp() {
        customers = new Customers(1L, "password", "email@email.com", Roles.CUSTOMER_INDIVIDUAL);
    }

    @Test
    void shouldReturnCustomerWhenCustomerExists() {
        given(customerRepository.findById(1L)).willReturn(Optional.of(CustomersServiceTests.customers));

        Customers customers = customerService.getCustomer(1L);

        assertThat(customers.getId()).isEqualTo(1L);
        assertThat(customers.getPassword()).isEqualTo("password");
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionWhenCustomerDoesNotExist() {
        given(customerRepository.findById(2L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.getCustomer(2L))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer with id: 2 not found");
    }

    @Test
    void shouldReturnCustomerDtoWhenCustomerExists() {
        given(customerRepository.findById(1L)).willReturn(Optional.of(customers));

        CustomerDto customerDto = customerService.getCustomerDto(1L);

        assertThat(customerDto.getId()).isEqualTo(1L);
        assertThat(customerDto.getEmail()).isEqualTo("email@email.com");
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionWhenDtoCustomerDoesNotExist() {
        given(customerRepository.findById(1L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.getCustomerDto(1L))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer with id: 1 not found");
    }

    @Test
    void shouldReturnCustomerWhenCustomerWithEmailExists() {
        given(customerRepository.findByEmail("email@email.com")).willReturn(Optional.of(CustomersServiceTests.customers));

        Customers customers = customerService.getCustomerByEmail("email@email.com");

        assertThat(customers.getId()).isEqualTo(1L);
        assertThat(customers.getPassword()).isEqualTo("password");
        assertThat(customers.getEmail()).isEqualTo("email@email.com");
    }

    @Test
    void shouldThrowUseNotFoundExceptionWhenCustomerWithEmailDoesNotExist() {
        given(customerRepository.findByEmail("email@email.com")).willReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.getCustomerByEmail("email@email.com"))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer with id: email@email.com not found");
    }

}
