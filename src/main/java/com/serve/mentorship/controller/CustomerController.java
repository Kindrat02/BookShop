package com.serve.mentorship.controller;

import com.serve.mentorship.dto.CustomerDTO;
import com.serve.mentorship.service.CustomerService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;
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
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(@PositiveOrZero @RequestParam(defaultValue = "0") Integer page,
                                             @Positive @RequestParam(defaultValue = "3") Integer size) {
        List<CustomerDTO> paginatedList = customerService.getAllCustomers(PageRequest.of(page, size));
        return new ResponseEntity<>(paginatedList, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity deleteCustomer(@Positive @PathVariable(name = "id") Integer customerId) {
        return new ResponseEntity(customerService.deleteCustomer(customerId) ? HttpStatus.OK : HttpStatus.GONE);
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO newCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, new HttpHeaders(), HttpStatus.OK);
    }
}
