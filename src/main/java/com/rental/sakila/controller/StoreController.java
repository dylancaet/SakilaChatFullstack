package com.rental.sakila.controller;

import com.rental.sakila.Route;
import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.entity.Store;
import com.rental.sakila.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class StoreController
{
    private final StoreService service;

    @GetMapping(Route.Store.GET_STORE)
    public Object getStore(@PathVariable Byte id)
    {
        Store store = service.getStore(id);
        return StoreResponse.from(store);
    }
}
