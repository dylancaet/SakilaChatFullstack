package com.rental.sakila.route;

public final class Route
{
    public final static class API
    {
        public static final String API_ROOT = "/api/sakila";

        public final static class Store
        {
            public static final String BASE_STORE       = API_ROOT+"/store";
            public static final String GET_STORE_LIST   = BASE_STORE+"/list";
            public static final String GET_STORE        = BASE_STORE+"/{id}";
        }

        public final static class Film
        {
            public static final String BASE_FILM        = API_ROOT+"/film";
            public static final String GET_FILM_LIST    = BASE_FILM+"/list";
            public static final String GET_FILM         = BASE_FILM+"/{id}";
            public static final String POST_FILM        = BASE_FILM;
        }
    }

    public final static class WebSocket
    {
        public static final String WS_ROOT          = "/ws";
        public static final String TEXT_MESSAGE     = WS_ROOT+"/message";
    }
}
