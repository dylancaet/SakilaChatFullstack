package com.rental.sakila.controller;

import com.rental.sakila.data.DataSupplier;
import com.rental.sakila.dto.reponse.FilmResponse;
import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.dto.request.FilmRequest;
import com.rental.sakila.entity.Film;
import com.rental.sakila.entity.Store;
import com.rental.sakila.exception.ItemNotFoundException;
import com.rental.sakila.service.FilmService;
import com.rental.sakila.service.StoreService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertThrows;

@Test
public class FilmControllerTest
{
    @InjectMocks
    private FilmController controller;

    @Mock
    private FilmService service;

    @BeforeClass
    private void methodSetup()
    {
        MockitoAnnotations.openMocks(this);

        for (Film film : DataSupplier.provideValidFilms())
        {
            doReturn(film).when(service).getFilm(film.getId());
            doReturn(film).when(service).createFilm(film.getTitle(), Optional.ofNullable(film.getDescription()), Optional.of(film.getPrice()));
        }

        Page<Film> pageFilms = new PageImpl<Film>(Arrays.stream(DataSupplier.provideValidFilms()).toList());
        doReturn(pageFilms).when(service).getFilmList(1, null);
        doThrow(ItemNotFoundException.class).when(service).getFilm(argThat(id -> id < 0));
    }

    @Test(dataProvider = "validFilms", dataProviderClass = DataSupplier.class)
    public void get_filmresponse_individual_from_controller(Film testFilm)
    {
        FilmResponse expectedResponse = FilmResponse.from(testFilm);
        FilmResponse actualResponse = controller.getFilm(testFilm.getId());

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(actualResponse.getId(), testFilm.getId());
        Assert.assertEquals(expectedResponse, actualResponse);

        verify(service, times(1)).getFilm(testFilm.getId());
    }

    @Test
    public void get_filmresponse_list_from_controller()
    {
        List<FilmResponse> expectedFilms = Arrays.stream(DataSupplier.provideValidFilms()).map(FilmResponse::from).toList();
        List<FilmResponse> actualFilms = controller.getFilmList(1, null).getFilms();

        Assert.assertEquals(expectedFilms, actualFilms);

        verify(service, times(1)).getFilmList(1, null);
    }

    @Test(dataProvider = "validFilms", dataProviderClass = DataSupplier.class)
    public void create_film_from_controller(Film testFilm)
    {
        FilmRequest requestFilmCreate = new FilmRequest();
        requestFilmCreate.setDescription(testFilm.getDescription());
        requestFilmCreate.setTitle(testFilm.getTitle());
        requestFilmCreate.setPrice(testFilm.getPrice());

        FilmResponse createdFilm = controller.createFilm(requestFilmCreate);

        Assert.assertEquals(createdFilm.getTitle(), testFilm.getTitle());
        Assert.assertEquals(createdFilm.getDescription(), testFilm.getDescription());
        Assert.assertEquals(createdFilm.getPrice(), testFilm.getPrice());

        verify(service, times(1)).createFilm(testFilm.getTitle(), Optional.ofNullable(testFilm.getDescription()), Optional.of(testFilm.getPrice()));
    }

    @Test(dataProvider = "validFilms", dataProviderClass = DataSupplier.class)
    public void update_film_from_controller(Film testFilm)
    {
        FilmRequest requestFilmUpdate = new FilmRequest();
        requestFilmUpdate.setId(testFilm.getId());
        requestFilmUpdate.setTitle(testFilm.getTitle());
        requestFilmUpdate.setDescription("EDITED " + testFilm.getDescription());
        requestFilmUpdate.setPrice(testFilm.getPrice() + 5.0f);

        Film updatedFilm = new Film(testFilm.getId(), testFilm.getTitle(), requestFilmUpdate.getPrice(), requestFilmUpdate.getDescription(), (byte)0);

        doReturn(updatedFilm).when(service).updateFilm(
                testFilm.getId(),
                Optional.of(requestFilmUpdate.getTitle()),
                Optional.of(requestFilmUpdate.getDescription()),
                Optional.of(requestFilmUpdate.getPrice())
        );

        FilmResponse updatedFilmResponse = controller.updateFilm(requestFilmUpdate);

        Assert.assertNotNull(updatedFilmResponse);
        Assert.assertEquals(updatedFilmResponse.getTitle(), testFilm.getTitle());
        Assert.assertEquals(updatedFilmResponse.getDescription(), "EDITED " + testFilm.getDescription());
        Assert.assertEquals(updatedFilmResponse.getPrice(), testFilm.getPrice() + 5.0f);

        verify(service, times(1)).updateFilm(
                testFilm.getId(),
                Optional.of(requestFilmUpdate.getTitle()),
                Optional.of(requestFilmUpdate.getDescription()),
                Optional.of(requestFilmUpdate.getPrice())
        );
    }

    @Test(dataProvider = "validFilms", dataProviderClass = DataSupplier.class)
    public void delete_film_from_controller(Film testFilm)
    {
        doNothing().when(service).deleteFilm(testFilm.getId());

        controller.deleteFilm(testFilm.getId());

        verify(service, times(1)).deleteFilm(testFilm.getId());
    }


}
