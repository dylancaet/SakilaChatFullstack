package com.rental.sakila.data.controller;

import com.rental.sakila.controller.StoreController;
import com.rental.sakila.data.DataSupplier;
import com.rental.sakila.dto.reponse.StoreResponse;
import com.rental.sakila.entity.Store;
import com.rental.sakila.service.StoreService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@Test
public class StoreControllerTest
{
    @InjectMocks
    private StoreController controller;

    @Mock
    private StoreService service;

    @BeforeClass
    private void methodSetup()
    {
        MockitoAnnotations.openMocks(this);

        for (Store store : DataSupplier.provideValidStores())
        {
            doReturn(store).when(service).getStore(store.getId());
        }

        doReturn(Arrays.stream(DataSupplier.provideValidStores()).toList()).when(service).getStoreList();
    }

    @Test(dataProvider = "validStores", dataProviderClass = DataSupplier.class)
    public void get_storeresponse_individual_from_controller(Store testStores)
    {
        StoreResponse expectedResponse = StoreResponse.from(testStores);
        StoreResponse actualResponse = controller.getStore(testStores.getId());

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(actualResponse.getId(), testStores.getId());
        Assert.assertEquals(expectedResponse, actualResponse);

        verify(service, times(1)).getStore(testStores.getId());
    }

    @Test
    public void get_storeresponse_list_from_controller()
    {
        List<StoreResponse> expectedStores = Arrays.stream(DataSupplier.provideValidStores()).map(StoreResponse::from).toList();
        List<StoreResponse> actualStores = controller.getStoreList();

        Assert.assertEquals(expectedStores, actualStores);

        verify(service, times(1)).getStoreList();
    }
}
