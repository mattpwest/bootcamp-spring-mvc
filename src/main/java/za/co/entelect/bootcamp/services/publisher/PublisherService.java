package za.co.entelect.bootcamp.services.publisher;

import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.generic.ReadService;
import za.co.entelect.bootcamp.services.generic.WriteService;

public interface PublisherService extends ReadService<Publisher, Integer>, WriteService<Publisher, Integer> {

    void registerSuperhero(Publisher publisher, Superhero superhero);

    Publisher getPublisherBySuperhero(Superhero superhero);

    Iterable<Superhero> getSuperheroesByPublisher(Publisher publisher);
    //Pageable<Superhero> getSuperheroesByPublisher(Publisher publisher);
}
