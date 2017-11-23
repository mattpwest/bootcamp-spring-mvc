package za.co.entelect.bootcamp.cli;

import org.springframework.stereotype.Component;
import za.co.entelect.bootcamp.domain.Gender;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.publisher.PublisherService;

@Component
public class CommandLineInterface {

    private final PublisherService publisherService;

    public CommandLineInterface(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public void printPublishersReport() {
        System.out.println(" = Publisher Report = ");
        System.out.println();
        for (Publisher publisher : publisherService.findAll()) {
            Iterable<Superhero> heroes = publisherService.getSuperheroesByPublisher(publisher);

            printPublisherReport(publisher, heroes);
        }
    }

    private void printPublisherReport(Publisher publisher, Iterable<Superhero> heroes) {
        int count = 0;
        int female = 0;
        int male = 0;
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

        System.out.println(" === " + publisher.getLongName() + " === ");
        System.out.println();
        System.out.println("Characters: " + count);
        System.out.println("\tFemale: " + female);
        System.out.println("\tMale: " + male);
        System.out.println("\tOther: " + other);
        System.out.println();
    }

}
