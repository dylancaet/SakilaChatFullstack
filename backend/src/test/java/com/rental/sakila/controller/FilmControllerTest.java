package com.rental.sakila.controller;

import com.rental.sakila.data.DataSupplier;
import com.rental.sakila.dto.reponse.FilmResponse;
import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.entity.Film;
import com.rental.sakila.entity.Store;
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

import static org.mockito.Mockito.*;

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
        }

        Page<Film> pageFilms = new PageImpl<Film>(Arrays.stream(DataSupplier.provideValidFilms()).toList());
        doReturn(pageFilms).when(service).getFilmList(1, null);
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
}
