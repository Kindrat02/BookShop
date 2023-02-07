package com.serve.mentorship.mapper;

import com.serve.mentorship.dto.CustomerDTO;
import com.serve.mentorship.entity.Customer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer model);
    Customer toModel(CustomerDTO dto);
}
