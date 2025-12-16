package com.bank.customer.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Customer Service API",
                version = "v1",
                description = "API for customer management"
        )
)
public class OpenApiConfig {
}