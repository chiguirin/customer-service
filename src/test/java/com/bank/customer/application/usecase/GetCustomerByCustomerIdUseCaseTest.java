package com.bank.customer.application.usecase;

import com.bank.customer.domain.model.Customer;
import com.bank.customer.domain.model.Person;
import com.bank.customer.domain.repository.CustomerRepository;
import com.bank.customer.infrastructure.controller.dto.CustomerResponseDTO;
import com.bank.customer.shared.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GetCustomerByCustomerIdUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GetCustomerByCustomerIdUseCaseTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private GetCustomerByCustomerIdUseCase getCustomerByCustomerIdUseCase;

    @Test
    void testExecute() {
        // Arrange
        Customer customer = new Customer("42", "iloveyou", new Person("Name", "Gender", 1, "42 Main St", "6625550144"));
        customer.setActive(true);
        customer.setCustomerId("42");
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByCustomerId(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CustomerResponseDTO actualExecuteResult = getCustomerByCustomerIdUseCase.execute("42");

        // Assert
        verify(customerRepository).findByCustomerId(eq("42"));
        assertEquals("42 Main St", actualExecuteResult.address());
        assertEquals("42", actualExecuteResult.customerId());
        assertEquals("6625550144", actualExecuteResult.phone());
        assertEquals("Gender", actualExecuteResult.gender());
        assertEquals("Name", actualExecuteResult.name());
        assertEquals(1, actualExecuteResult.age().intValue());
        assertTrue(actualExecuteResult.active());
    }


    @Test
    void testExecute2() {
        // Arrange
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByCustomerId(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(BusinessException.class, () -> getCustomerByCustomerIdUseCase.execute("42"));
        verify(customerRepository).findByCustomerId(eq("42"));
    }


    @Test
    void testExecute3() {
        // Arrange
        when(customerRepository.findByCustomerId(Mockito.<String>any()))
                .thenThrow(new BusinessException("An error occurred"));

        // Act and Assert
        assertThrows(BusinessException.class, () -> getCustomerByCustomerIdUseCase.execute("42"));
        verify(customerRepository).findByCustomerId(eq("42"));
    }
}
