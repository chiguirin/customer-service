package com.bank.customer.om.bank.customer.application.usecase;

import com.bank.customer.application.usecase.CreateCustomerUseCase;
import com.bank.customer.domain.repository.CustomerRepository;
import com.bank.customer.domain.repository.PersonRepository;
import com.bank.customer.shared.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateCustomerUseCaseTest {

    @Test
    void should_fail_when_customer_already_exist() {

        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);

        Mockito.when(customerRepository.findByCustomerId("123"))
                .thenReturn(java.util.Optional.of(Mockito.mock(com.bank.customer.domain.model.Customer.class)));

        CreateCustomerUseCase useCase =
                new CreateCustomerUseCase(customerRepository, personRepository, event -> {});

        assertThrows(BusinessException.class, () ->
                useCase.execute("123", "pwd", "name", "M", 20, "addr", "123")
        );
    }
}