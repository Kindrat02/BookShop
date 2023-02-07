package com.serve.mentorship.service;

import com.serve.mentorship.dto.CustomerDTO;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Integer id) throws NotFoundException;
}
