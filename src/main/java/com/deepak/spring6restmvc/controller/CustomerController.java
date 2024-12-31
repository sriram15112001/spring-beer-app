package com.deepak.spring6restmvc.controller;

import com.deepak.spring6restmvc.model.Customer;
import com.deepak.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return this.customerService.getAllCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("customerId") UUID id) {
        return this.customerService.getCustomer(id);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = this.customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "api/v1/customer/" + savedCustomer.getId());
        return new ResponseEntity<>(savedCustomer, headers, HttpStatus.CREATED);

    }

}
