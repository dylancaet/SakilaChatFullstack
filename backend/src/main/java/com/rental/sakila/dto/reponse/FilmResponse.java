package com.rental.sakila.dto.reponse;

import com.rental.sakila.entity.Film;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class FilmResponse
{
    private final Short id;
    private final String title;
    private final float price;
    private final String description;

    public static FilmResponse from(Film film)
    {
        return new FilmResponse(film.getId(), film.getTitle(), film.getPrice(), film.getDescription());
    }
}
