package com.rental.sakila.service;

import com.rental.sakila.entity.Store;
import com.rental.sakila.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;

@CommonsLog
@RequiredArgsConstructor
@Service
public class StoreService
{
    private final StoreRepository storeRepository;

    public List<Store> getStores()
    {
        List<Store> storeList = storeRepository.findAll();

        return storeList;
    }

}
