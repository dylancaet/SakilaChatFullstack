package com.rental.sakila.data;

import com.rental.sakila.entity.Store;
import org.testng.annotations.DataProvider;

public class DataSupplier
{
    @DataProvider(name = "validStores")
    public static Store[] provideValidStores()
    {
        return new Store[] {
                new Store((byte) 1),
                new Store((byte) 2),
                new Store((byte) 3)
        };
    }
}
