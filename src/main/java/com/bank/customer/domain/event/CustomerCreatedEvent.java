package com.bank.customer.domain.event;

public record CustomerCreatedEvent(
        String customerId
) {
}
