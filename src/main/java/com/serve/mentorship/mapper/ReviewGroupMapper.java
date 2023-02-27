package com.serve.mentorship.mapper;

import com.serve.mentorship.dto.ReviewGroupDTO;
import com.serve.mentorship.entity.ReviewGroup;
import com.serve.mentorship.service.CustomerService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CustomerService.class})
public abstract class ReviewGroupMapper {

    @Autowired
    protected CustomerService customerService;


    @Mapping(target = "customer", expression = "java(customerService.getCustomerById(reviewGroup.getCustomerId()).get())")
    @Mapping(target = "period", expression = "java(java.time.YearMonth.of(reviewGroup.getYear(), reviewGroup.getMonth()))")
    public abstract ReviewGroupDTO toDTO(ReviewGroup reviewGroup);
}
