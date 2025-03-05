package com.rental.sakila.service;

import com.rental.sakila.entity.Store;
import com.rental.sakila.exception.ItemNotFoundException;
import com.rental.sakila.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@CommonsLog
@Service
public class StoreService
{
    private final StoreRepository repository;

    /**
     *  Retrieves a {@link Store} entry with the specified id, else throws an exception.
     *
     * @param   id      the id to find.
     * @return  the {@link Store} with the matching id.
     * @exception ItemNotFoundException if the <code>id</code> is not found
     */
    public Store getStore(Byte id)
    {
        Store store = repository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(String.format("ERR: Store %s not found", id)));

        return store;
    }

    /**
     * Retrieves all stores from the database.
     *
     * @return a list containing all {@link Store} entries.
     */
    public List<Store> getStoreList()
    {
        List<Store> storeList = repository.findAll().stream().toList();

        return storeList;
    }
}
