package com.rental.sakila.service;

import com.rental.sakila.controller.ActorController;
import com.rental.sakila.data.DataSupplier;
import com.rental.sakila.dto.response.ActorResponse;
import com.rental.sakila.entity.Actor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.*;

@Test
public class ActorControllerTest
{
    @InjectMocks private ActorController controller;

    @Mock private ActorService service;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);

        for (Actor actor : DataSupplier.provideValidActors())
        {
            doReturn(actor).when(service).getActor(actor.getId());
        }
    }

    @Test(dataProvider = "validActors", dataProviderClass = DataSupplier.class)
    public void get_actor_individual(Actor testActor)
    {
        ActorResponse expectedResponse = ActorResponse.from(testActor);
        ActorResponse actualResponse = controller.getActorIndividual(testActor.getId());

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(actualResponse.getId(), testActor.getId());
        Assert.assertEquals(actualResponse.getFirstName(), testActor.getFirstName());
        Assert.assertEquals(actualResponse.getLastName(), testActor.getLastName());
        Assert.assertEquals(expectedResponse, actualResponse);

        verify(service, times(1)).getActor(testActor.getId());
    }

}
