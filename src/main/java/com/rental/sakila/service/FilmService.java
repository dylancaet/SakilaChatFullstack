package com.rental.sakila.service;

import com.rental.sakila.entity.Film;
import com.rental.sakila.entity.Store;
import com.rental.sakila.exception.ItemNotFoundException;
import com.rental.sakila.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@CommonsLog
@Service
public class FilmService
{
    private final FilmRepository repository;

    /**
     *  Retrieves a {@link Film} entry with the specified id, else throws an exception.
     *
     * @param   id      the id to find.
     * @return  the {@link Film} with the matching id.
     * @exception ItemNotFoundException if the <code>id</code> is not found
     */
    public Film getFilm(Short id)
    {
        Film film = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("ERR: Film %s not found", id)));

        return film;
    }

    /**
     * Retrieves a page of maximum of 50 films from the database.
     * A page is a set of items that will always return the same values.
     *
     * @param   pageNumber  the page to retrieve items from.
     * @param   title  filter by title like-ness
     * @return a {@link Page<Film>} containing maximum 50 {@link Film} entries.
     */
    public Page<Film> getFilmList(int pageNumber, Optional<String> title)
    {
        int pageSize = 50;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Film> filmPage;

        if (title.isPresent()) {
            filmPage = repository.findByTitleContainingIgnoreCase(title.get(), pageable);
        }
        else {
            filmPage = repository.findAll(pageable);
        }

        return filmPage;
    }

    /**
     * Creates a film entry from a title, in the database.
     *
     * @param   title  the title of the film.
     * @param   description  the price of the film, optional.
     * @param   price  the price of the film, optional.
     * @return the resulting {@link Film} entry created.
     */
    public Film createFilm(String title, Optional<String> description, Optional<Float> price)
    {
        int languageId = 1;

        Film film = new Film();
        film.setTitle(title);
        film.setLanguageId((byte)languageId);
        description.ifPresent(film::setDescription);
        price.ifPresent(film::setPrice);

        Film filmSaved = repository.save(film);

        log.info(String.format("Film Created: %s", film.getId()));

        return filmSaved;
    }


    /**
     *  Deletes a {@link Film} entry with the specified id, else throws an exception.
     *
     * @param   id      the id to find.
     * @exception ItemNotFoundException if the <code>id</code> is not found
     */
    public void deleteFilm(Short id)
    {
        Film film = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("ERR: Film %s not found", id)));

        repository.delete(film);

        log.info(String.format("Actor Deleted: %s", film.getId()));
    }


    /**
     * Updates a film entry with the specified id, in the database.
     *
     * @param id    the id to found.
     * @param title title of the film, optional.
     * @param description description of the film, optional.
     * @param price price of the film, optional.
     * @return the resulting {@link Film} entry updated.
     * @exception ItemNotFoundException if the <code>id</code> is not found
     */
    public Film updateFilm(Short id, Optional<String> title, Optional<String> description, Optional<Float> price)
    {
        Film film = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("ERR: Film %s not found", id)));

        title.ifPresent(film::setTitle);
        description.ifPresent(film::setDescription);
        price.ifPresent(film::setPrice);

        Film filmUpdated = repository.save(film);

        log.info(String.format("Film Updated: %s", film.getId()));

        return filmUpdated;
    }
}
