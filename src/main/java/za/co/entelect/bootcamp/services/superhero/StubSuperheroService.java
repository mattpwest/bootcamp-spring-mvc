package za.co.entelect.bootcamp.services.superhero;

import org.springframework.stereotype.Service;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.generic.GenericStubService;

@Service
public class StubSuperheroService
        extends GenericStubService<Superhero, Integer>
        implements SuperheroService {

    public StubSuperheroService() {
        super();
    }
}
