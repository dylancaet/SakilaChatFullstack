package com.rental.sakila.data;

import com.rental.sakila.entity.*;
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

    @DataProvider(name = "validFilms")
    public static Film[] provideValidFilms()
    {
        return new Film[] {
                new Film((short) 1, "The Phantom of the Opera", 12.99f, "A story about the phantom of an opera", (byte) 0),
                new Film((short) 2, "Polar Bear Express", 5.59f, "A story a polar bear train", (byte) 0),
                new Film((short) 3, "Hall", 3.49f, "A story about halls", (byte) 0)
        };
    }

}
