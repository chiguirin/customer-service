package com.bank.customer.application.service;


import com.bank.customer.application.usecase.CreateCustomerUseCase;
import com.bank.customer.application.usecase.GetCustomerByCustomerIdUseCase;
import com.bank.customer.application.usecase.UpdateCustomerStatusUseCase;
import com.bank.customer.infrastructure.controller.dto.CustomerResponseDTO;
import org.springframework.stereotype.Service;
@Service
public class CustomerService {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerStatusUseCase updateCustomerStatusUseCase;
    private final GetCustomerByCustomerIdUseCase getCustomerByCustomerIdUseCase;

    public CustomerService(CreateCustomerUseCase createCustomerUseCase,
                           UpdateCustomerStatusUseCase updateCustomerStatusUseCase, GetCustomerByCustomerIdUseCase getCustomerByCustomerIdUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.updateCustomerStatusUseCase = updateCustomerStatusUseCase;
        this.getCustomerByCustomerIdUseCase = getCustomerByCustomerIdUseCase;
    }

    public void createCustomer(CreateCustomerRequest request) {
        createCustomerUseCase.execute(
                request.customerId(),
                request.password(),
                request.name(),
                request.gender(),
                request.age(),
                request.address(),
                request.phone()
        );
    }

    public void updateCustomerStatus(String customerId, Boolean active) {
        updateCustomerStatusUseCase.execute(customerId, active);
    }

    public record CreateCustomerRequest(
            String customerId,
            String password,
            String name,
            String gender,
            Integer age,
            String address,
            String phone
    ) {}

    public CustomerResponseDTO getCustomerByCustomerId(String customerId) {
        return getCustomerByCustomerIdUseCase.execute(customerId);
    }


}