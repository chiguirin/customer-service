package com.bank.customer.infrastructure.controller;

import com.bank.customer.application.service.CustomerService;
import com.bank.customer.infrastructure.controller.dto.CreateCustomerRequestDTO;
import com.bank.customer.infrastructure.controller.dto.CustomerResponseDTO;
import com.bank.customer.infrastructure.controller.dto.UpdateCustomerStatusRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customers", description = "Customer operations")
@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCustomerRequestDTO request) {

        service.createCustomer(
                new CustomerService.CreateCustomerRequest(
                        request.customerId(),
                        request.password(),
                        request.name(),
                        request.gender(),
                        request.age(),
                        request.address(),
                        request.phone()
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{customerId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable String customerId,
            @RequestBody UpdateCustomerStatusRequestDTO request) {

        service.updateCustomerStatus(customerId, request.active());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getByCustomerId(
            @PathVariable String customerId) {

        return ResponseEntity.ok(service.getCustomerByCustomerId(customerId));
    }
}