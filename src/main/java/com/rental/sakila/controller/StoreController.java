package com.rental.sakila.controller;

import com.rental.sakila.repository.StoreRepository;
import com.rental.sakila.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salika/stores")
public class StoreController
{
    private final StoreService storeService;

    @GetMapping
    public Object getStores()
    {
        var storeList = storeService.getStores();

        return storeList;
    }

}
