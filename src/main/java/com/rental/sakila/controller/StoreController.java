package com.rental.sakila.controller;

import com.rental.sakila.Route;
import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.entity.Store;
import com.rental.sakila.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    Cross Origin Resource Sharing (CORS)
    - A security measure implemented by Browsers to restrict cross-origin requests (to stop malicious fetches)
    - Origins defined in the HTTP header
    - Browsers will send a `preflight` request before actual request
    - fetch() follows same-origin policy and can only fetch on the same origin
        unless the preflight response includes the right CORS headers

    https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
 */

@AllArgsConstructor
@RestController
public class StoreController
{
    private final StoreService service;

    /* GET Individual */
    @CrossOrigin
    @GetMapping(Route.API.Store.GET_STORE)
    public StoreResponse getStore(@PathVariable Byte id)
    {
        Store store = service.getStore(id);
        return StoreResponse.from(store);
    }

    /* GET List */
    @CrossOrigin
    @GetMapping(Route.API.Store.GET_STORE_LIST)
    public List<StoreResponse> getStoreList()
    {
        List<StoreResponse> storeList = service.getStoreList().stream().map(StoreResponse::from).toList();
        return storeList;
    }

}
