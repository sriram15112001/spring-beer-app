package com.deepak.spring6restmvc.bootstrap;

import com.deepak.spring6restmvc.entity.BeerEntity;
import com.deepak.spring6restmvc.entity.CustomerEntity;
import com.deepak.spring6restmvc.model.BeerStyle;
import com.deepak.spring6restmvc.repository.BeerRepository;
import com.deepak.spring6restmvc.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private BeerRepository beerRepository;
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
    }

    private void loadCustomerData() {
        CustomerEntity customer1 = CustomerEntity.builder()
                .name("John Doe")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerEntity customer2 = CustomerEntity.builder()
                .name("Jane Smith")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerEntity customer3 = CustomerEntity.builder()
                .name("Michael Johnson")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        this.customerRepository.save(customer1);
        this.customerRepository.save(customer2);
        this.customerRepository.save(customer3);
    }

    private void loadBeerData() {
        BeerEntity beer1 = BeerEntity.builder()
                .beerName("Pale Ale")
                .beerStyle(BeerStyle.ALE)
                .upc("123456789012")
                .quantityOnHand(50)
                .price(new BigDecimal("8.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerEntity beer2 = BeerEntity.builder()
                .beerName("Stout King")
                .beerStyle(BeerStyle.STOUT)
                .upc("234567890123")
                .quantityOnHand(100)
                .price(new BigDecimal("9.49"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerEntity beer3 = BeerEntity.builder()
                .beerName("Lager Light")
                .beerStyle(BeerStyle.LAGER)
                .upc("345678901234")
                .quantityOnHand(75)
                .price(new BigDecimal("7.99"))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        this.beerRepository.save(beer1);
        this.beerRepository.save(beer2);
        this.beerRepository.save(beer3);
    }


}
