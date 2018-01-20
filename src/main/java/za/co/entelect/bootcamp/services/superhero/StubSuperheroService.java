package za.co.entelect.bootcamp.services.superhero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.generic.GenericStubService;
import za.co.entelect.bootcamp.services.publisher.PublisherService;

@Service
public class StubSuperheroService
        extends GenericStubService<Superhero, Integer>
        implements SuperheroService {

    private final PublisherService publisherService;

    @Autowired
    public StubSuperheroService(PublisherService publisherService) {
        super();

        this.publisherService = publisherService;
    }

    @Override
    public void delete(Superhero hero) {
        super.delete(hero);

        publisherService.unregisterSuperhero(hero);
    }

    @Override
    public void delete(Integer integer) {
        Superhero hero = findOne(integer);
        if (hero != null) {
            delete(hero);
        }
    }
}
