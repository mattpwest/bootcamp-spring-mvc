package za.co.entelect.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.entelect.bootcamp.domain.Gender;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.publisher.PublisherService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("")
    public String homePage(ModelMap modelMap) {
        return "index";
    }

    @GetMapping("/report")
    public String report(ModelMap modelMap) {
        List<PublisherService.SuperheroSummaryReport> reports = new ArrayList<>();
        for (Publisher publisher : publisherService.findAll()) {
            reports.add(publisherService.generateHeroSummaryReport(publisher));
        }

        modelMap.put("reports", reports);
        return "report";
    }

    @GetMapping("/report/{publisherId}")
    public String listSuperheroes(
            @PathVariable("publisherId") Integer publisherId,
            ModelMap modelMap
    ) {
        Publisher publisher = publisherService.findOne(publisherId);
        Iterable<Superhero> heroes = publisherService.getSuperheroesByPublisher(publisher);

        modelMap.put("publisher", publisher);
        modelMap.put("heroes", heroes);

        return "heroes";

    }

    @GetMapping("/report/{publisherId}/filter")
    public String listSuperheroesFiltered(
            @PathVariable("publisherId") Integer publisherId,
            @RequestParam("gender") Gender gender,
            ModelMap modelMap
    ) {
        Publisher publisher = publisherService.findOne(publisherId);
        Iterable<Superhero> heroes = publisherService.getSuperheroesByPublisherAndGender(publisher, gender);

        modelMap.put("publisher", publisher);
        modelMap.put("heroes", heroes);
        modelMap.put("filter", gender);

        return "heroes";
    }
}
