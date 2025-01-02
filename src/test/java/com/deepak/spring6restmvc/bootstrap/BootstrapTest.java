package com.deepak.spring6restmvc.bootstrap;

import com.deepak.spring6restmvc.repository.BeerRepository;
import com.deepak.spring6restmvc.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BootstrapTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    Bootstrap bootstrap;

    @BeforeEach
    void setup() {
        bootstrap = new Bootstrap(beerRepository, customerRepository);
    }

    @Test
    void bootstrapTest() throws Exception {
        bootstrap.run(null);
        assertEquals(beerRepository.count(), 3);
        assertEquals(customerRepository.count(), 3);
    }
}