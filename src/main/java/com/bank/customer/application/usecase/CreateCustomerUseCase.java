package com.bank.customer.application.usecase;


import com.bank.customer.domain.event.CustomerCreatedEvent;
import com.bank.customer.domain.model.Customer;
import com.bank.customer.domain.repository.CustomerRepository;
import com.bank.customer.shared.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateCustomerUseCase.class);

    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CreateCustomerUseCase(CustomerRepository customerRepository,
                                 ApplicationEventPublisher eventPublisher) {
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(String customerId,
                        String password,
                        String name,
                        String gender,
                        Integer age,
                        String address,
                        String phone) {

        customerRepository.findByCustomerId(customerId)
                .ifPresent(c -> {
                    log.warn("Customer already exists with id {}", customerId);
                    throw new BusinessException("Customer already exists");
                });

        Customer customer = new Customer(
                customerId,
                password,
                name,
                gender,
                age,
                address,
                phone
        );

        customerRepository.save(customer);

        eventPublisher.publishEvent(new CustomerCreatedEvent(customerId));

        log.info("Customer was created successfully and event was published");
    }
}