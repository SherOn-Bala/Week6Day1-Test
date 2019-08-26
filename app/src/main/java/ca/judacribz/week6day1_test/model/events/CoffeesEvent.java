package ca.judacribz.week6day1_test.model.events;


import java.util.List;

import ca.judacribz.week6day1_test.model.Coffee;

public class CoffeesEvent {
    List<Coffee> coffees;

    public CoffeesEvent(List<Coffee> coffees) {
        this.coffees = coffees;
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public void setCoffees(List<Coffee> coffees) {
        this.coffees = coffees;
    }
}
