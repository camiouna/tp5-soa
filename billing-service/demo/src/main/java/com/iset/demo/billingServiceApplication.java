package com.iset.demo;

import java.util.Date;

import com.iset.demo.entities.Bill;
import com.iset.demo.entities.Customer;
import com.iset.demo.entities.Product;
import com.iset.demo.entities.ProductItem;
import com.iset.demo.repository.BillRepository;
import com.iset.demo.repository.ProductItemRepository;
import com.iset.demo.service.CustomerService;
import com.iset.demo.service.InventoryService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

@SpringBootApplication
@EnableFeignClients
public class billingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(billingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerService customerService,
            InventoryService inventoryService) {
        return args -> {
            Customer customer = customerService.findCustomerById(1L);
            System.out.println("**********************");
            System.out.println("ID= " + customer.getId());
            System.out.println("Name= " + customer.getName());
            System.out.println("Email= " + customer.getEmail());

            Bill bill = billRepository.save(new Bill(new Date(), customer.getId(), null, null));
            PagedModel<Product> products = inventoryService.findAllProducts();
            products.getContent().forEach(product -> productItemRepository.save(
                    new ProductItem(product.getId(), product, product.getPrice(), 90, bill)));
        };
    }
}
