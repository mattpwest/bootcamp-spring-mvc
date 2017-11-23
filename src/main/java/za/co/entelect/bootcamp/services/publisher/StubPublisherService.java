package za.co.entelect.bootcamp.services.publisher;

import org.springframework.stereotype.Service;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.generic.GenericStubService;

import java.util.*;

@Service
public class StubPublisherService
        extends GenericStubService<Publisher, Integer>
        implements PublisherService {

    private Map<Publisher, List<Superhero>> publisherHeroes;
    private Map<Superhero, Publisher> heroPublisher;

    public StubPublisherService() {
        super();

        this.publisherHeroes = new HashMap<>();
        this.heroPublisher = new HashMap<>();
    }

    @Override
    public void registerSuperhero(Publisher publisher, Superhero superhero) {
        if (!publisherHeroes.containsKey(publisher)) {
            publisherHeroes.put(publisher, new ArrayList<>());
        }

        publisherHeroes.get(publisher).add(superhero);
        heroPublisher.put(superhero, publisher);
    }

    @Override
    public Publisher getPublisherBySuperhero(Superhero superhero) {
        return heroPublisher.get(superhero);
    }

    @Override
    public Iterable<Superhero> getSuperheroesByPublisher(Publisher publisher) {
        if (!publisherHeroes.containsKey(publisher)) return new ArrayList<>();

        return Collections.unmodifiableList(publisherHeroes.get(publisher));
    }

}
