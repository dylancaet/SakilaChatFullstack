package com.rental.sakila.controller;

import com.rental.sakila.dto.request.FilmRequest;
import com.rental.sakila.route.Route;
import com.rental.sakila.dto.reponse.FilmResponse;
import com.rental.sakila.dto.reponse.PaginatedFilmResponse;
import com.rental.sakila.dto.request.RequestValidation;
import com.rental.sakila.entity.Film;
import com.rental.sakila.service.FilmService;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@AllArgsConstructor
@RestController
public class FilmController
{
    private final FilmService service;

    /* GET Single */
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

    /* POST */
    @CrossOrigin
    @PostMapping(Route.API.Film.POST_FILM)
    public FilmResponse createFilm(@Validated(RequestValidation.Create.class) @RequestBody FilmRequest request)
    {
        var filmCreated = service.createFilm(request.getTitle(),
                        Optional.ofNullable(request.getDescription()),
                        Optional.of(request.getPrice()));

        return FilmResponse.from(filmCreated);
    }

    /* PATCH */
    @CrossOrigin
    @PatchMapping(Route.API.Film.PATCH_FILM)
    public FilmResponse updateFilm(@Validated(RequestValidation.Update.class) @RequestBody FilmRequest request)
    {
        var filmUpdated = service.updateFilm(request.getId(),
                        Optional.ofNullable(request.getTitle()),
                        Optional.ofNullable(request.getDescription()),
                        Optional.ofNullable(request.getPrice()));

        return FilmResponse.from(filmUpdated);
    }

    /* DELETE */
    @CrossOrigin
    @DeleteMapping(Route.API.Film.DELETE_FILM)
    public void deleteFilm(@Validated(RequestValidation.Delete.class) @PathVariable Short id)
    {
        service.deleteFilm(id);
    }
}
