package com.deepak.spring6restmvc.mapper;

import com.deepak.spring6restmvc.entity.BeerEntity;
import com.deepak.spring6restmvc.model.Beer;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerEntityToBeer(BeerEntity beerEntity);

    BeerEntity beerToBeerEntity(Beer beer);
}
