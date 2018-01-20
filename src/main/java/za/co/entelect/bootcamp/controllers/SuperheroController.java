package za.co.entelect.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import za.co.entelect.bootcamp.controllers.exceptions.NotFoundException;
import za.co.entelect.bootcamp.controllers.forms.SuperheroForm;
import za.co.entelect.bootcamp.domain.Gender;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.publisher.PublisherService;
import za.co.entelect.bootcamp.services.superhero.SuperheroService;

import javax.validation.Valid;

@Controller
public class SuperheroController {

    private final SuperheroService superheroService;
    private final PublisherService publisherService;

    @Autowired
    public SuperheroController(SuperheroService superheroService, PublisherService publisherService) {
        this.superheroService = superheroService;
        this.publisherService = publisherService;
    }

    @DeleteMapping("/superheroes/{superheroId}")
    public String deleteHero(@PathVariable("superheroId") Integer superheroId) {
        Superhero hero = superheroService.findOne(superheroId);
        if (hero == null) {
            throw new NotFoundException(String.format("Superhero with ID %d not found.", superheroId));
        }

        Publisher publisher = publisherService.getPublisherBySuperhero(hero);
        superheroService.delete(hero);

        return String.format("redirect:/report/%d", publisher.getId());
    }


    @GetMapping("/superheroes/add/{publisherId}")
    public String addSuperhero(
            @PathVariable("publisherId") Integer publisherId,
            @ModelAttribute("form") SuperheroForm form,
            ModelMap modelMap
    ) {
        Publisher publisher = publisherService.findOne(publisherId);
        if (publisher == null) {
            throw new NotFoundException(String.format("Publisher with ID %d does not exist.", publisherId));
        }

        modelMap.put("publisher", publisher);
        modelMap.put("genders", Gender.values());

        return "addHero";
    }

    @PostMapping(value = "/superheroes/add/{publisherId}")
    public String addSuperhero(
            @PathVariable("publisherId") Integer publisherId,
            @Valid @ModelAttribute("form") SuperheroForm form,
            BindingResult bindingResult,
            ModelMap modelMap
    ) {
        Publisher publisher = publisherService.findOne(publisherId);
        if (publisher == null) {
            throw new NotFoundException(String.format("Publisher with ID %d does not exist.", publisherId));
        }

        modelMap.put("publisher", publisher);
        modelMap.put("genders", Gender.values());

        if (bindingResult.hasErrors()) {
            modelMap.put("hasErrors", true);
            return "addHero"; // Errors are automatically added to the model
        }

        Superhero superhero = superheroService.save(form.toSuperhero());
        publisherService.registerSuperhero(publisher, superhero);

        return "redirect:/report/" + publisherId;
    }
}
