package com.deepak.spring6restmvc.controller;

import com.deepak.spring6restmvc.model.Beer;
import com.deepak.spring6restmvc.services.BeerService;
import com.deepak.spring6restmvc.services.BeerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl;

    @BeforeEach
    void setup() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void getBeerById() throws Exception{
        Beer beer = beerServiceImpl.listBeers().get(0);
        when(beerService.getBeerById(any(UUID.class))).thenReturn(beer);

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(beer.getId().toString()))
                .andExpect(jsonPath("$.beerName").value(beer.getBeerName().toString()));

    }

    @Test
    void listBeers() throws Exception {
        when(beerService.listBeers()).thenReturn(beerServiceImpl.listBeers());

        mockMvc.perform(get("/api/v1/beer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void addBeer() throws Exception {
        Beer beer = beerServiceImpl.listBeers().getFirst();
        beer.setId(null);
        beer.setCreatedDate(null);
        beer.setUpdateDate(null);

        when(beerService.saveBeer(any(Beer.class))).thenReturn(beer);

        mockMvc.perform(post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(beer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }

    @Test
    void putBeer() throws Exception {
        Beer beer = beerServiceImpl.listBeers().getFirst();
        when(beerService.updateBeer(any(UUID.class), any(Beer.class))).thenReturn(true).thenReturn(false);
        mockMvc.perform(put("/api/v1/beer/" + beer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isNoContent());
        mockMvc.perform(put("/api/v1/beer/" + beer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    void deleteBeer() throws Exception {
        Beer beer = beerServiceImpl.listBeers().getFirst();
        when(beerService.deleteBeer(any(UUID.class))).thenReturn(true).thenReturn(false);
        mockMvc.perform(delete("/api/v1/beer/" + beer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        mockMvc.perform(delete("/api/v1/beer/" + beer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isExpectationFailed());

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(beerService, times(2)).deleteBeer(uuidArgumentCaptor.capture());

        assertEquals(beer.getId(), uuidArgumentCaptor.getValue());


    }
}