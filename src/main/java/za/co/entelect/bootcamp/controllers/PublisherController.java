package za.co.entelect.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import za.co.entelect.bootcamp.domain.Publisher;
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
    public String report(ModelMap modelMap) {
        List<PublisherService.SuperheroSummaryReport> reports = new ArrayList<>();
        for (Publisher publisher : publisherService.findAll()) {
            reports.add(publisherService.generateHeroSummaryReport(publisher));
        }

        modelMap.put("reports", reports);
        return "index";
    }
}
