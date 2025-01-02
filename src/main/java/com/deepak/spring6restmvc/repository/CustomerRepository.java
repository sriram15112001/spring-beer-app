package com.deepak.spring6restmvc.repository;

import com.deepak.spring6restmvc.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
