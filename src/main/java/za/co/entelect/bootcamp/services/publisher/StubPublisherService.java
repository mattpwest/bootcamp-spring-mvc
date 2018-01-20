package za.co.entelect.bootcamp.services.publisher;

import org.springframework.stereotype.Service;
import za.co.entelect.bootcamp.domain.Gender;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.generic.GenericStubService;

import java.util.*;
import java.util.stream.Collectors;

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
    public void unregisterSuperhero(Superhero hero) {
        Publisher publisher = heroPublisher.get(hero);
        if (publisher != null) {
            publisherHeroes.get(publisher).remove(hero);
        }
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

    @Override
    public Iterable<Superhero> getSuperheroesByPublisherAndGender(Publisher publisher, Gender gender) {
        List<Superhero> heroes = new ArrayList<>();
        getSuperheroesByPublisher(publisher).forEach(heroes::add);
        return heroes.stream()
                .filter(hero -> hero.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @Override
    public SuperheroSummaryReport generateHeroSummaryReport(Publisher publisher) {
        SuperheroSummaryReport report = new SuperheroSummaryReport();
        Iterable<Superhero> heroes = this.getSuperheroesByPublisher(publisher);

        int count = 0;
        int male = 0;
        int female = 0;
        int other = 0;
        for (Superhero hero : heroes) {
            count++;

            if (Gender.Male.equals(hero.getGender())) {
                male++;
            } else if (Gender.Female.equals(hero.getGender())) {
                female++;
            } else if (Gender.Other.equals(hero.getGender())) {
                other++;
            }
        }

        report.setPublisher(publisher);
        report.setCount(count);
        report.setFemale(female);
        report.setMale(male);
        report.setOther(other);

        return report;
    }
}
