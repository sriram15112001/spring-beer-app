package com.deepak.spring6restmvc.controller;

import com.deepak.spring6restmvc.model.Beer;
import com.deepak.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID id){
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(id);
    }

    @PostMapping
    public ResponseEntity<Beer> saveBeer(@RequestBody Beer beer) {
        HttpHeaders headers = new HttpHeaders();
        Beer savedBeer = this.beerService.saveBeer(beer);
        headers.add("location", "/api/v1/beer" + savedBeer.getId());
        return new ResponseEntity<>(savedBeer, headers, HttpStatus.CREATED);
    }

    @PutMapping("{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID id, @RequestBody Beer beer) {
        boolean isUpdated = this.beerService.updateBeer(id, beer);
        if(isUpdated) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }
}
