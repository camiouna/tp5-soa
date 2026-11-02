package com.iset.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.iset.demo.entities.Customer;
import com.iset.demo.repositories.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication {

	CustomerRepository customerRepository;

    CustomerServiceApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository,RepositoryRestConfiguration restConfiguration){
		return args->{
			restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.save(new Customer("ISET","ISET@gmail.com"));
			customerRepository.save(new Customer("ENSI","ENSI@gmail.com"));
			customerRepository.save(new Customer("ENIT","ENIT@gmail.com"));
			

		};
	}

}
