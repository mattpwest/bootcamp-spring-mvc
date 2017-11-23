package za.co.entelect.bootcamp.services.superhero;

import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.generic.ReadService;
import za.co.entelect.bootcamp.services.generic.WriteService;

public interface SuperheroService extends ReadService<Superhero, Integer>, WriteService<Superhero, Integer> {
}
