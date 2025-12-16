package com.bank.customer.application.usecase;

import com.bank.customer.domain.model.Customer;
import com.bank.customer.domain.repository.CustomerRepository;
import com.bank.customer.shared.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UpdateCustomerStatusUseCase {

    private final CustomerRepository repository;

    public UpdateCustomerStatusUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(String customerId, Boolean active) {
        Customer customer = repository.findByCustomerId(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found"));

        customer.setActive(active);
    }
}