package com.rental.sakila.controller;

import com.rental.sakila.Route;
import com.rental.sakila.dto.reponse.FilmResponse;
import com.rental.sakila.dto.reponse.PaginatedFilmResponse;
import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.entity.Film;
import com.rental.sakila.entity.Store;
import com.rental.sakila.service.FilmService;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;


@AllArgsConstructor
@RestController
public class FilmController
{
    private final FilmService service;

    /* GET Individual */
    @CrossOrigin
    @GetMapping(Route.API.Film.GET_FILM)
    public FilmResponse getFilm(@PathVariable Short id)
    {
        Film film = service.getFilm(id);
        return FilmResponse.from(film);
    }

    /* GET List */
    @CrossOrigin
    @GetMapping(Route.API.Film.GET_FILM_LIST)
    public PaginatedFilmResponse getFilmList(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                             @RequestParam(required = false) Optional<String> title)
    {
        Page<Film> filmList = service.getFilmList(page, title);
        return PaginatedFilmResponse.from(filmList);
    }

}
