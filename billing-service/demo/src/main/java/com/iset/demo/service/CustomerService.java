package com.iset.demo.service;

import com.iset.demo.entities.Customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerService {
    @GetMapping("/customers/{id}")
    Customer findCustomerById(@PathVariable("id") Long id);
}
