package com.iset.demo.service;

import com.iset.demo.entities.Bill;
import com.iset.demo.repository.BillRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BillRestController {
    private final BillRepository billRepository;
    private final CustomerService customerService;
    private final InventoryService inventoryService;

    public BillRestController(
            BillRepository billRepository,
            CustomerService customerService,
            InventoryService inventoryService) {
        this.billRepository = billRepository;
        this.customerService = customerService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable("id") Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill not found"));
        bill.setCustomer(customerService.findCustomerById(bill.getCustomerID()));
        bill.getProductItems().forEach(item ->
                item.setProduct(inventoryService.findProductById(item.getProductID())));
        return bill;
    }
}
