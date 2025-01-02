package com.deepak.spring6restmvc.services;

import com.deepak.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomer(UUID id);

    Customer saveCustomer(Customer customer);

    boolean updateCustomer(UUID id, Customer customer);

    boolean deleteCustomer(UUID id);
}
