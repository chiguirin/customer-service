package com.bank.customer.application.usecase;



import com.bank.customer.domain.event.CustomerCreatedEvent;
import com.bank.customer.domain.model.Customer;
import com.bank.customer.domain.model.Person;
import com.bank.customer.domain.repository.CustomerRepository;
import com.bank.customer.domain.repository.PersonRepository;
import com.bank.customer.shared.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateCustomerUseCase.class);

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CreateCustomerUseCase(CustomerRepository customerRepository,
                                 PersonRepository personRepository,
                                 ApplicationEventPublisher eventPublisher) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
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
                    log.warn("Customer already exist with id {}", customerId);
                    throw new BusinessException("Customer already exist");
                });

        Person person = personRepository.save(
                new Person(name, gender, age, address, phone)
        );

        customerRepository.save(
                new Customer(customerId, password, person)
        );

        eventPublisher.publishEvent(new CustomerCreatedEvent(customerId));

        log.info("Customer was created sucessfuly and event was published");
    }
}