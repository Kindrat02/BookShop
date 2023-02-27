package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.dto.CustomerDTO;
import com.serve.mentorship.entity.Author;
import com.serve.mentorship.entity.Customer;
import com.serve.mentorship.mapper.CustomerMapper;
import com.serve.mentorship.repository.CustomerRepository;
import com.serve.mentorship.service.CustomerService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
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

    @Transactional
    @Override
    public boolean deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Transactional
    @Override
    public CustomerDTO createCustomer(CustomerDTO author) {
        Customer customerModel = customerMapper.toModel(author);
        customerModel.setCreatedAt(new GregorianCalendar());

        Customer savedEntity = customerRepository.save(customerModel);
        return customerMapper.toDTO(savedEntity);
    }
}
