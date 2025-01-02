package com.deepak.spring6restmvc.controller;

import com.deepak.spring6restmvc.model.Customer;
import com.deepak.spring6restmvc.services.CustomerService;
import com.deepak.spring6restmvc.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setup() {
        customerServiceImpl = new CustomerServiceImpl();
    }
    @Test
    void createCustomer() throws Exception{
        Customer customer = customerServiceImpl.getAllCustomers().getFirst();
        customer.setId(null);
        customer.setCreatedDate(null);
        customer.setUpdatedDate(null);

        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));

    }

    @Test
    void getCustomer() throws Exception{
        Customer customer = customerServiceImpl.getAllCustomers().getFirst();
        when(customerService.getCustomer(customer.getId())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customer.getId().toString()))
                .andExpect(jsonPath("$.name").value(customer.getName().toString()));
    }

    @Test
    void getCustomers() throws Exception {
        List<Customer> customers = customerServiceImpl.getAllCustomers();
        when(customerService.getAllCustomers()).thenReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void putCustomer() throws Exception {
        Customer customer = customerServiceImpl.getAllCustomers().getFirst();
        when(customerService.updateCustomer(any(UUID.class), any(Customer.class))).thenReturn(true);
        mockMvc.perform(put("/api/v1/customer/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCustomer() throws Exception {
        when(customerService.deleteCustomer(any(UUID.class))).thenReturn(true);
        Customer customer = customerServiceImpl.getAllCustomers().getFirst();
        mockMvc.perform(delete("/api/v1/customer/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        ArgumentCaptor<UUID> argumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(customerService, times(1)).deleteCustomer(argumentCaptor.capture());
        assertEquals(customer.getId(), argumentCaptor.getValue());
    }
}