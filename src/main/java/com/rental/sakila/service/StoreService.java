package com.rental.sakila.service;

import com.rental.sakila.entity.Store;
import com.rental.sakila.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService
{
    private final StoreRepository repository;

    public Store getStore(Byte id)
    {
        Store store = repository.findById(id)
                .orElseThrow(RuntimeException::new);

        return store;
    }
}
