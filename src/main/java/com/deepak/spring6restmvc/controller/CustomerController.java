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

    @PutMapping("{customerId}")
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID id, @RequestBody Customer customer) {
        boolean isUpdated = this.customerService.updateCustomer(id, customer);
        if(isUpdated) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId") UUID id) {
        boolean isDeleted = this.customerService.deleteCustomer(id);
        if(isDeleted) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }


}
