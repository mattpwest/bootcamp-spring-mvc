package za.co.entelect.bootcamp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.entelect.bootcamp.domain.Gender;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.publisher.PublisherService;
import za.co.entelect.bootcamp.services.superhero.SuperheroService;

@Component
public class StubDataPopulator {

    private final PublisherService publisherService;
    private final SuperheroService superheroService;

    @Autowired
    public StubDataPopulator(PublisherService publisherService, SuperheroService superheroService) {
        this.publisherService = publisherService;
        this.superheroService = superheroService;

        addDC();
        addMarvel();
    }

    private void addDC() {
        Publisher dc = addPublisher("DC", "DC Comics");

        addSuperhero(dc, "Aquaman", Gender.Male);
        addSuperhero(dc, "Batman", Gender.Male);
        addSuperhero(dc, "Catwoman", Gender.Female);
        addSuperhero(dc, "Flash", Gender.Male);
        addSuperhero(dc, "Green Lantern", Gender.Male);
        addSuperhero(dc, "Harley Quinn", Gender.Female);
        addSuperhero(dc, "Supergirl", Gender.Female);
        addSuperhero(dc, "Superman", Gender.Male);
        addSuperhero(dc, "Wonder Woman", Gender.Female);
    }

    private void addMarvel() {
        Publisher marvel = addPublisher("Marvel", "Marvel Comics");

        addSuperhero(marvel, "Beast", Gender.Male);
        addSuperhero(marvel, "Captain America", Gender.Male);
        addSuperhero(marvel, "Cyclops", Gender.Male);
        addSuperhero(marvel, "Daredevil", Gender.Male);
        addSuperhero(marvel, "Deadpool", Gender.Male);
        addSuperhero(marvel, "Gambit", Gender.Male);
        addSuperhero(marvel, "Gamora", Gender.Female);
        addSuperhero(marvel, "Jean Grey", Gender.Female);
        addSuperhero(marvel, "Groot", Gender.Male);
        addSuperhero(marvel, "Hulk", Gender.Male);
        addSuperhero(marvel, "Iron Man", Gender.Male);
        addSuperhero(marvel, "Jubilee", Gender.Female);
        addSuperhero(marvel, "Mystique", Gender.Female);
        addSuperhero(marvel, "Rogue", Gender.Female);
        addSuperhero(marvel, "Sabretooth", Gender.Male);
        addSuperhero(marvel, "Spider-Man", Gender.Male);
        addSuperhero(marvel, "Star-Lord", Gender.Male);
        addSuperhero(marvel, "Storm", Gender.Female);
        addSuperhero(marvel, "Thor", Gender.Male);
        addSuperhero(marvel, "Wolverine", Gender.Male);
        addSuperhero(marvel, "Professor X", Gender.Male);
    }

    private Publisher addPublisher(String shortName, String longName) {
        Publisher publisher = new Publisher();
        publisher.setShortName(shortName);
        publisher.setLongName(longName);

        return publisherService.save(publisher);
    }

    private void addSuperhero(Publisher publisher, String name, Gender gender) {
        Superhero superhero = new Superhero();
        superhero.setName(name);
        superhero.setGender(gender);
        superheroService.save(superhero);

        publisherService.registerSuperhero(publisher, superhero);
    }
}
