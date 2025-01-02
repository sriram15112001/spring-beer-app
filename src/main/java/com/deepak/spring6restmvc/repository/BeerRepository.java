package com.deepak.spring6restmvc.repository;

import com.deepak.spring6restmvc.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
