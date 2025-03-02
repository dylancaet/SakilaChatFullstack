package com.rental.sakila.data;

import com.rental.sakila.entity.Actor;
import org.testng.annotations.DataProvider;

import java.util.List;

public class DataSupplier
{
    @DataProvider(name = "validActors")
    public static Object[][] provideValidActors() {
        return new Object[][] {
                {new Actor((short)1, "Johnny", "Depp", "Johnny Depp", List.of())},
                {new Actor((short)2, "Tom", "Riddle", "Tom Riddle", List.of())},
                {new Actor((short)3, "Judas", "Christ", "Judas Christ", List.of())}
        };
    }

    @DataProvider(name = "invalidActors")
    public static Object[][] provideInvalidActors() {
        return new Object[][] {
                {new Actor((short)5, "Judas", "Old", "Judas", List.of())},
                {new Actor((short)4, "", "Old", "Old", List.of())},
                {new Actor((short)6, "Judas", "", "Judas", List.of())}
        };
    }
}
