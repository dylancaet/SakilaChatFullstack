package com.rental.sakila.controller;

import com.rental.sakila.Route;
import com.rental.sakila.dto.reponse.FilmResponse;
import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.entity.Film;
import com.rental.sakila.entity.Store;
import com.rental.sakila.service.FilmService;
import com.rental.sakila.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.Optional;

import java.util.List;


@AllArgsConstructor
@RestController
public class FilmController
{
    private final FilmService service;

    /* GET Individual */
    @CrossOrigin
    @GetMapping(Route.Film.GET_FILM)
    public FilmResponse getFilm(@PathVariable Short id)
    {
        Film film = service.getFilm(id);
        return FilmResponse.from(film);
    }

    /* GET List */
    @CrossOrigin
    @GetMapping(Route.Film.GET_FILM_LIST)
    public List<FilmResponse> getFilmList(@RequestParam(defaultValue = "0") int page)
    {
        List<FilmResponse> filmList = service.getFilmList(page)
                            .stream().map(FilmResponse::from).toList();
        return filmList;
    }

}
