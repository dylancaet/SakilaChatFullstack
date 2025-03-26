package com.rental.sakila.service;

import com.rental.sakila.data.DataSupplier;
import com.rental.sakila.entity.Film;
import com.rental.sakila.entity.Store;
import com.rental.sakila.exception.ItemNotFoundException;
import com.rental.sakila.repository.FilmRepository;
import com.rental.sakila.repository.StoreRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@Test
public class FilmServiceTest
{
    @InjectMocks
    private FilmService service;

    @Mock
    private FilmRepository repository;

    @BeforeClass
    private void methodSetup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void resetMocks()
    {
        reset(repository);
    }

    @Test(dataProvider = "validFilms", dataProviderClass = DataSupplier.class)
    public void get_film_entries_from_repository(Film expectedFilm)
    {
        doReturn(Optional.of(expectedFilm)).when(repository).findById(expectedFilm.getId());

        Film actualFilm = service.getFilm(expectedFilm.getId());

        Assert.assertEquals(expectedFilm.getId(), actualFilm.getId());

        verify(repository, times(1)).findById(expectedFilm.getId());
    }

    @Test(dataProvider = "validFilms", dataProviderClass = DataSupplier.class)
    public void delete_existing_film(Film existingFilm)
    {
        Short filmId = existingFilm.getId();

        doReturn(Optional.of(existingFilm)).when(repository).findById(filmId);
        doNothing().when(repository).delete(existingFilm);

        service.deleteFilm(filmId);

        verify(repository, times(1)).findById(filmId);
        verify(repository, times(1)).delete(existingFilm);
    }

    @Test
    public void create_new_film_entry()
    {
        String title = "A FILM";
        Optional<String> description = Optional.of("A FILM DESCRIPTION");
        Optional<Float> price = Optional.of(9.99f);

        Film newFilm = new Film((short) 100, title, price.get(), description.get(), (byte)0); // Mock created film

        doReturn(newFilm).when(repository).save(any(Film.class));

        Film result = service.createFilm(title, description, price);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getTitle(), title);
        Assert.assertEquals(result.getDescription(), description.get());
        Assert.assertEquals(result.getPrice(), price.get());

        verify(repository, times(1)).save(any(Film.class));
    }

    @Test(dataProvider = "validFilms", dataProviderClass = DataSupplier.class)
    public void update_film_existing_entry(Film existingFilm)
    {
        Short filmId = existingFilm.getId();
        Optional<String> newTitle = Optional.of("EDITED " + existingFilm.getTitle());
        Optional<String> newDescription = Optional.of("EDITED " + existingFilm.getDescription());
        Optional<Float> newPrice = Optional.of(existingFilm.getPrice() + 5.0f);

        Film updatedFilm = new Film(filmId, newTitle.get(), newPrice.get(), newDescription.get(), (byte)0);

        doReturn(Optional.of(existingFilm)).when(repository).findById(filmId);

        doReturn(updatedFilm).when(repository).save(any(Film.class));

        Film result = service.updateFilm(filmId, newTitle, newDescription, newPrice);

        Assert.assertEquals(result.getId(), filmId);
        Assert.assertEquals(result.getTitle(), newTitle.get());
        Assert.assertEquals(result.getDescription(), newDescription.get());
        Assert.assertEquals(result.getPrice(), newPrice.get());

        verify(repository, times(1)).findById(filmId);
        verify(repository, times(1)).save(any(Film.class));
    }

    @Test
    public void get_all_films_from_repository()
    {
        Pageable pageable = PageRequest.of(1, 50);

        Page<Film> expectedFilmPage = new PageImpl<>(Arrays.stream(DataSupplier.provideValidFilms()).toList());

        doReturn(expectedFilmPage).when(repository).findAll(pageable);

        Page<Film> actualFilmPage = service.getFilmList(1, Optional.empty());

        Assert.assertEquals(expectedFilmPage.getTotalElements(), actualFilmPage.getTotalElements());

        verify(repository, times(1)).findAll(pageable);
    }

    @Test(expectedExceptions = ItemNotFoundException.class)
    public void get_invalid_film_throws_exception()
    {
        Short invalidId = -1;

        doReturn(Optional.empty()).when(repository).findById(invalidId);

        service.getFilm(invalidId);

        verify(repository, times(1)).findById(invalidId);
    }

    @Test(expectedExceptions = ItemNotFoundException.class)
    public void delete_invalid_film_throws_exception()
    {
        Short invalidId = -1;

        doReturn(Optional.empty()).when(repository).findById(invalidId);

        service.deleteFilm(invalidId);

        verify(repository, times(1)).findById(invalidId);
    }

    @Test(expectedExceptions = ItemNotFoundException.class)
    public void update_invalid_film_throws_exception()
    {
        Short invalidId = -1;

        doReturn(Optional.empty()).when(repository).findById(invalidId);

        service.updateFilm(invalidId, null, null, null);

        verify(repository, times(1)).findById(invalidId);
    }

    @Test
    public void find_films_by_title()
    {
        int pageNumber = 0;
        String searchTitle = "Test Film";
        Optional<String> title = Optional.of(searchTitle);

        List<Film> filmList = List.of(new Film((short)1, "Test Film", 10.0f, "Desc", (byte)0));
        Page<Film> filmPage = new PageImpl<>(filmList);

        doReturn(filmPage).when(repository).findByTitleContainingIgnoreCase(eq(searchTitle), any(Pageable.class));

        Page<Film> result = service.getFilmList(pageNumber, title);

        Assert.assertEquals(result.getTotalElements(), filmList.size());

        verify(repository, times(1)).findByTitleContainingIgnoreCase(eq(searchTitle), any(Pageable.class));
    }
}
