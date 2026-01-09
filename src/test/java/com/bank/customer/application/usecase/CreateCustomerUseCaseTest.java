package com.bank.customer.application.usecase;

import com.bank.customer.domain.model.Customer;
import com.bank.customer.domain.repository.CustomerRepository;
import com.bank.customer.shared.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CreateCustomerUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CreateCustomerUseCaseTest {
    @MockBean
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;

    @MockBean
    private CustomerRepository customerRepository;


    @Test
    void testExecute() {
        // Arrange
        Optional<Customer> ofResult = Optional
                .of(new Customer("42", "iloveyou", "Name", "Gender", 1, "42 Main St", "6625550144"));
        when(customerRepository.findByCustomerId(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> createCustomerUseCase.execute("42", "iloveyou", "Name", "Gender", 1, "42 Main St", "6625550144"));
        verify(customerRepository).findByCustomerId(eq("42"));
    }


    @Test
    void testExecute2() {
        // Arrange
        when(customerRepository.save(Mockito.<Customer>any()))
                .thenReturn(new Customer("42", "iloveyou", "Name", "Gender", 1, "42 Main St", "6625550144"));
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByCustomerId(Mockito.<String>any())).thenReturn(emptyResult);

        // Act
        createCustomerUseCase.execute("42", "iloveyou", "Name", "Gender", 1, "42 Main St", "6625550144");

        // Assert
        verify(customerRepository).findByCustomerId(eq("42"));
        verify(customerRepository).save(isA(Customer.class));
    }


    @Test
    void testExecute3() {
        // Arrange
        when(customerRepository.findByCustomerId(Mockito.<String>any()))
                .thenThrow(new BusinessException("An error occurred"));

        // Act and Assert
        assertThrows(BusinessException.class,
                () -> createCustomerUseCase.execute("42", "iloveyou", "Name", "Gender", 1, "42 Main St", "6625550144"));
        verify(customerRepository).findByCustomerId(eq("42"));
    }
}
