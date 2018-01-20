package za.co.entelect.bootcamp.controllers.forms;

import za.co.entelect.bootcamp.domain.Gender;
import za.co.entelect.bootcamp.domain.Superhero;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SuperheroForm {
    @NotNull(message = "Superhero name is required.")
    @Size(min = 1, message = "Superhero name may not be empty.")
    private String name;

    @NotNull(message = "Superhero gender is required.")
    private Gender gender;

    public SuperheroForm() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Superhero toSuperhero() {
        Superhero result = new Superhero();
        result.setName(name);
        result.setGender(gender);
        return result;
    }
}
