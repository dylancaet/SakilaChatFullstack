package com.rental.sakila.controller;

import com.rental.sakila.Route;
import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.entity.Store;
import com.rental.sakila.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class StoreController
{
    private final StoreService service;

    /* GET Individual */
    @GetMapping(Route.Store.GET_STORE)
    public StoreResponse getStore(@PathVariable Byte id)
    {
        Store store = service.getStore(id);
        return StoreResponse.from(store);
    }

    /* GET List */
    @GetMapping(Route.Store.GET_STORE_LIST)
    public List<StoreResponse> getStoreList()
    {
        List<StoreResponse> storeList = service.getStoreList().stream().map(StoreResponse::from).toList();
        return storeList;
    }

}
