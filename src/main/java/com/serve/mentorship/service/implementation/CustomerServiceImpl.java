package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.CustomerDTO;
import com.serve.mentorship.mapper.CustomerMapper;
import com.serve.mentorship.repository.CustomerRepository;
import com.serve.mentorship.service.CustomerService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) throws NotFoundException {
        return customerMapper.toDTO(
                customerRepository
                .findById(id)
                .orElseThrow(NotFoundException::new));
    }
}
