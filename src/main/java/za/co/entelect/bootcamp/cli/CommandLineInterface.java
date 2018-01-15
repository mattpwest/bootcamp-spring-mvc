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
            printPublisherReport(publisher);
        }
    }

    private void printPublisherReport(Publisher publisher) {
        PublisherService.SuperheroSummaryReport report = publisherService.generateHeroSummaryReport(publisher);

        System.out.println(" === " + publisher.getLongName() + " === ");
        System.out.println();
        System.out.println("Characters: " + report.getCount());
        System.out.println("\tFemale: " + report.getFemale());
        System.out.println("\tMale: " + report.getMale());
        System.out.println("\tOther: " + report.getOther());
        System.out.println();
    }

}
