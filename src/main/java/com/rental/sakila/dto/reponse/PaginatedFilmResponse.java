package com.rental.sakila.dto.reponse;

import com.rental.sakila.entity.Film;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class PaginatedFilmResponse
{
    private int currentPage;
    private int totalPages;
    private long totalFilms;
    private List<FilmResponse> films;

    public static PaginatedFilmResponse from(Page<Film> page)
    {
        List<FilmResponse> films = page.stream().map(FilmResponse::from).toList();
        return new PaginatedFilmResponse(page.getNumber(), page.getTotalPages(), page.getTotalElements(), films);
    }

}