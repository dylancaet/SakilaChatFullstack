package com.rental.sakila;

public final class Route
{
    public static final String BASE_API = "/api/sakila";

    public final static class Store
    {
        public static final String BASE_STORE   = BASE_API+"/store";
        public static final String GET_STORE    = BASE_STORE+"/{id}";
    }
}
