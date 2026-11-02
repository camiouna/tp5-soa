package com.iset.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import com.iset.demo.entities.Customer;

@RepositoryRestResource
public
interface CustomerRepository extends JpaRepository<Customer,Long>{
    public Customer findCustomerByName(String name);
}

@Projection(name="fullCustomer",types=Customer.class)
interface CustomerProjection{
    public Long getId();
    public String getName();
    public String getEmail();
    
}
