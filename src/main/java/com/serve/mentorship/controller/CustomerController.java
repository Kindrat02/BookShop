package com.serve.mentorship.controller;

import com.serve.mentorship.dto.CustomerDTO;
import com.serve.mentorship.service.CustomerService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomerDTO> getCustomerById(@Positive @RequestParam(name = "id") Integer customerId) throws NotFoundException {
        CustomerDTO customer = customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(customer, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(@PositiveOrZero @RequestParam(defaultValue = "0") Integer page,
                                             @Positive @RequestParam(defaultValue = "3") Integer size) {
        return customerService.getAllCustomers(PageRequest.of(page, size));
    }
}
