package com.bank.customer.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bank.customer.domain.model.Customer;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@DataJpaTest
class CustomerRepositoryIT {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void savesAndIncrementsVersion() {
        Customer customer = new Customer(
                "CUST-1",
                "secret",
                "Ana",
                "F",
                30,
                "Street 123",
                "555-0100");

        Customer saved = repository.saveAndFlush(customer);
        assertThat(saved.getVersion()).isNotNull();
        Long initialVersion = saved.getVersion();

        saved.setActive(false);
        Customer updated = repository.saveAndFlush(saved);
        assertThat(updated.getVersion()).isGreaterThan(initialVersion);
    }

    @Test
    void detectsOptimisticLockConflict() {
        Customer customer = new Customer(
                "CUST-2",
                "secret",
                "Luis",
                "M",
                41,
                "Street 456",
                "555-0200");
        repository.saveAndFlush(customer);
        entityManager.clear();

        Customer first = repository.findByCustomerId("CUST-2").orElseThrow();
        entityManager.detach(first);
        Customer second = repository.findByCustomerId("CUST-2").orElseThrow();
        entityManager.detach(second);

        first.setActive(false);
        repository.saveAndFlush(first);

        second.setActive(false);
        assertThrows(ObjectOptimisticLockingFailureException.class,
                () -> repository.saveAndFlush(second));
    }
}
