package com.bank.customer.application.usecase;


import com.bank.customer.domain.model.Customer;
import com.bank.customer.domain.repository.CustomerRepository;
import com.bank.customer.infrastructure.controller.dto.CustomerResponseDTO;
import com.bank.customer.shared.exception.BusinessException;
import org.springframework.stereotype.Component;


@Component
public class GetCustomerByCustomerIdUseCase {

    private final CustomerRepository repository;

    public GetCustomerByCustomerIdUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerResponseDTO execute(String customerId) {
        Customer customer = repository.findByCustomerId(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found"));

        return new CustomerResponseDTO(
                customer.getCustomerId(),
                customer.isActive(),
                customer.getName(),
                customer.getGender(),
                customer.getAge(),
                customer.getAddress(),
                customer.getPhone()
        );
    }
}