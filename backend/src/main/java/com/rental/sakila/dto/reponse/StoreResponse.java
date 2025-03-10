package com.rental.sakila.dto.reponse;

import com.rental.sakila.entity.Address;
import com.rental.sakila.entity.Store;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class StoreResponse
{
    private final Byte id;
    private final Address address;

    public static StoreResponse from(Store store)
    {
        return new StoreResponse(store.getId(), store.getAddress());
    }
}
