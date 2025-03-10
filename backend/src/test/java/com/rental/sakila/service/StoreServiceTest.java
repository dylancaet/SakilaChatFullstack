package com.rental.sakila.service;

import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.entity.Store;
import com.rental.sakila.repository.StoreRepository;
import com.rental.sakila.service.StoreService;
import com.rental.sakila.data.DataSupplier;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@Test
public class StoreServiceTest
{
    @InjectMocks /* Alternatively, open and tear down mocks */
    private StoreService service;

    @Mock
    private StoreRepository repository;

    @BeforeClass
    private void methodSetup()
    {
        MockitoAnnotations.openMocks(this);

        for (Store store : DataSupplier.provideValidStores())
        {
            doReturn(Optional.of(store)).when(repository).findById(store.getId());
        }

        doReturn(Arrays.stream(DataSupplier.provideValidStores()).toList()).when(repository).findAll();
    }

    @Test(dataProvider = "validStores", dataProviderClass = DataSupplier.class)
    public void get_store_entries_from_repository(Store expectedStore)
    {
        Store actualActor = service.getStore(expectedStore.getId());

        Assert.assertEquals(expectedStore.getId(), actualActor.getId());

        verify(repository, times(1)).findById(expectedStore.getId());
    }

    @Test
    public void get_all_stores_from_repository()
    {
        List<Store> expectedStores = Arrays.stream(DataSupplier.provideValidStores()).toList();
        List<Store> actualStores = service.getStoreList();

        Assert.assertEquals(expectedStores.size(), actualStores.size());

        verify(repository, times(1)).findAll();
    }
}
