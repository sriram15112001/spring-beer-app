package com.deepak.spring6restmvc.services;

import com.deepak.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, Customer> customers;

    public CustomerServiceImpl() {
        this.customers = new HashMap<>();

        Customer customer1 = Customer.builder()
                .name("customer1")
                .id(UUID.randomUUID())
                .version("1")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .name("customer2")
                .id(UUID.randomUUID())
                .version("1")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .name("customer3")
                .id(UUID.randomUUID())
                .version("1")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        this.customers.put(customer1.getId(), customer1);
        this.customers.put(customer2.getId(), customer2);
        this.customers.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(this.customers.values());
    }

    @Override
    public Customer getCustomer(UUID id) {
        return this.customers.get(id);
    }
}
