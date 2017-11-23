package za.co.entelect.bootcamp.domain;

import za.co.entelect.bootcamp.domain.generic.IdentifiableDomain;

public class Superhero extends IdentifiableDomain<Integer> {

    private static Integer nextId = 1;

    private String name;
    private Gender gender;

    public Superhero() {
        super(nextId++);
    }

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
}
