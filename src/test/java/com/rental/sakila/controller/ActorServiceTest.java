package com.rental.sakila.controller;

import com.rental.sakila.data.DataSupplier;
import com.rental.sakila.entity.Actor;
import com.rental.sakila.exception.ActorNotFoundException;
import com.rental.sakila.service.ActorService;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.mockito.Mockito.*;

@Test
public class ActorServiceTest
{
    @Mock /* Alternatively, open and tear down mocks */
    private ActorService service;


    /* BeforeTest suite-wide setup, BeforeClass once-per-class setup, BeforeMethod per-test setup */
    /* Best Practices:
         - focus on behaviour, not implementation
         - use argument matchers
         - verify interactions
         - clean up
    */
    @BeforeClass
    private void methodSetup()
    {
        this.service = mock(ActorService.class);

        for (Actor actor : DataSupplier.provideValidActors())
        {
            doReturn(actor).when(service).getActor(actor.getId());
        }

        for (Actor actor : DataSupplier.provideInvalidActors())
        {
            doReturn(actor).when(service).getActor(actor.getId());
        }

        doThrow(ActorNotFoundException.class).when(service).getActor(shortThat((Short s) -> s < 0));
    }

    @Test(dataProvider = "validActors", dataProviderClass = DataSupplier.class)
    public void valid_ActorData_from_repository(Actor expectedActor)
    {
        Actor actualActor = service.getActor(expectedActor.getId());

        Assert.assertEquals(expectedActor.getFirstName(), actualActor.getFirstName());
        Assert.assertEquals(expectedActor.getLastName(), actualActor.getLastName());
        Assert.assertTrue(actualActor.isValid());
    }

    @Test(dataProvider = "invalidActors", dataProviderClass = DataSupplier.class)
    public void invalid_on_invalidActors(Actor expectedActor)
    {
        Actor actualActor = service.getActor(expectedActor.getId());

        Assert.assertFalse(actualActor.isValid());
    }

    @Test(dataProvider = "validActors", dataProviderClass = DataSupplier.class)
    public void get_actors_from_repository(Actor expectedActor)
    {
        Actor actualActor = service.getActor(expectedActor.getId());

        Assert.assertEquals(actualActor, expectedActor);
    }
}
