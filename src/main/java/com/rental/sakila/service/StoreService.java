package com.rental.sakila.service;

import com.rental.sakila.entity.Store;
import com.rental.sakila.exception.ItemNotFoundException;
import com.rental.sakila.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@CommonsLog
@Service
public class StoreService
{
    private final StoreRepository repository;

    public Store getStore(Byte id)
    {
        Store store = repository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(String.format("ERR: Store %s not found", id)));

        return store;
    }
}
