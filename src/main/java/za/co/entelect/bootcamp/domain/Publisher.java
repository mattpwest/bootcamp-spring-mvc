package za.co.entelect.bootcamp.domain;

import za.co.entelect.bootcamp.domain.generic.IdentifiableDomain;

public class Publisher extends IdentifiableDomain<Integer> {

    private static Integer nextId = 1;

    private String shortName;
    private String longName;

    public Publisher() {
        super(nextId++);
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
