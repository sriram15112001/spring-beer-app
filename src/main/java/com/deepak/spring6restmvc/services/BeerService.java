package com.deepak.spring6restmvc.services;

import com.deepak.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);

    Beer saveBeer(Beer beer);

    boolean updateBeer(UUID id, Beer beer);

    boolean deleteBeer(UUID id);
}
