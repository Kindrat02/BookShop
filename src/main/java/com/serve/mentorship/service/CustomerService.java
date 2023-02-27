package com.serve.mentorship.service;

import com.serve.mentorship.dto.CustomerDTO;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers(Pageable pageable);
    Optional<CustomerDTO> getCustomerById(Integer id);
    boolean deleteCustomer(Integer id);
    CustomerDTO createCustomer(CustomerDTO customer);
}
