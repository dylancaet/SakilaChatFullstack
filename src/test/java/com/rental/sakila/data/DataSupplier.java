package com.rental.sakila.data;

import com.rental.sakila.entity.Address;
import com.rental.sakila.entity.City;
import com.rental.sakila.entity.Staff;
import com.rental.sakila.entity.Store;
import org.testng.annotations.DataProvider;

public class DataSupplier
{
    @DataProvider(name = "validStores")
    public static Store[] provideValidStores()
    {
        return new Store[] {
                new Store((byte) 1, new Address((short) 1, "28 Plum St", "Plum", null, null, new City((short)1, "Plum Town"))),
                new Store((byte) 2, new Address((short) 1, "28 Plum St", "Plum", null, null, new City((short)1, "Plum Town"))),
                new Store((byte) 3, new Address((short) 1, "28 Plum St", "Plum", null, null, new City((short)1, "Plum Town")))
        };
    }
}
