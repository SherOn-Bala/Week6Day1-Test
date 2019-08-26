package ca.judacribz.week6day1_test.model.events;

import ca.judacribz.week6day1_test.model.Coffee;

public class CoffeeEvent {
    Coffee coffee;

    public CoffeeEvent(Coffee coffee) {
        this.coffee = coffee;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
}
