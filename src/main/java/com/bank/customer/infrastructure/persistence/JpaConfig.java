package com.bank.customer.infrastructure.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.bank.customer.domain.repository")
@EntityScan(basePackages = "com.bank.customer.domain.model")
public class JpaConfig {
}