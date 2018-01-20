package za.co.entelect.bootcamp.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.entelect.bootcamp.controllers.exceptions.NotFoundException;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.publisher.PublisherService;
import za.co.entelect.bootcamp.services.superhero.SuperheroService;

import static org.mockito.Mockito.*;

public class SuperheroControllerTest {

    private SuperheroService superheroService;
    private PublisherService publisherService;

    private SuperheroController cut;

    @Before
    public void setUp() {
        superheroService = mock(SuperheroService.class);
        publisherService = mock(PublisherService.class);

        cut = new SuperheroController(superheroService, publisherService);
    }

    @Test
    public void testDeleteHero() {
        // Given
        Superhero hero = mock(Superhero.class);
        Publisher publisher = mock(Publisher.class);
        when(publisher.getId()).thenReturn(1);

        when(superheroService.findOne(1)).thenReturn(hero);
        when(publisherService.getPublisherBySuperhero(hero)).thenReturn(publisher);

        // When
        String view = cut.deleteHero(1);

        // Then
        Assert.assertEquals("redirect:/report/1", view);
        verify(superheroService, times(1)).delete(hero);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteHeroThrowsForInvalidId() {
        // Given
        when(superheroService.findOne(1)).thenReturn(null);

        // When Then
        String view = cut.deleteHero(1);
    }
}
