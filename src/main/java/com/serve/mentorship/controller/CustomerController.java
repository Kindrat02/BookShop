package com.serve.mentorship.controller;

import com.serve.mentorship.dto.CustomerDTO;
import com.serve.mentorship.service.CustomerService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.constraints.Positive;

@Validated
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public CustomerDTO getCustomerById(@Positive @RequestParam(name = "id") Integer customerId) throws NotFoundException {
        return customerService.getCustomerById(customerId);
    }
    
    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
