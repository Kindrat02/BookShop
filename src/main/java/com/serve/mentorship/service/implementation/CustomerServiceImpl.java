package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.CustomerDTO;
import com.serve.mentorship.entity.Customer;
import com.serve.mentorship.mapper.CustomerMapper;
import com.serve.mentorship.repository.CustomerRepository;
import com.serve.mentorship.service.CustomerService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Pageable pageable) {
        return customerRepository
                .findAll(pageable)
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(Integer id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::toDTO);
    }
}
