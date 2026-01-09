package com.bank.customer.application.usecase;

import com.bank.customer.domain.model.Customer;
import com.bank.customer.domain.repository.CustomerRepository;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UpdateCustomerStatusUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UpdateCustomerStatusUseCaseTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private UpdateCustomerStatusUseCase updateCustomerStatusUseCase;


    @Test
    void testExecute() {
        // Arrange
        Customer customer = new Customer(
                "42",
                "iloveyou",
                "Jair Castillo",
                "M",
                33,
                "Calle Fake 123",
                "3204584846"
        );
        customer.setActive(true);
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByCustomerId(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        updateCustomerStatusUseCase.execute("42", true);

        // Assert
        verify(customerRepository).findByCustomerId(eq("42"));
    }


    @Test
    void testExecute2() {
        // Arrange
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByCustomerId(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(BusinessException.class, () -> updateCustomerStatusUseCase.execute("42", true));
        verify(customerRepository).findByCustomerId(eq("42"));
    }


    @Test
    void testExecute3() {
        // Arrange
        when(customerRepository.findByCustomerId(Mockito.<String>any()))
                .thenThrow(new BusinessException("An error occurred"));

        // Act and Assert
        assertThrows(BusinessException.class, () -> updateCustomerStatusUseCase.execute("42", true));
        verify(customerRepository).findByCustomerId(eq("42"));
    }
}
