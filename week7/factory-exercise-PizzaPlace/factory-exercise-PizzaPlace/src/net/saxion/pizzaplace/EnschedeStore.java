package net.saxion.pizzaplace;

import net.saxion.pizzaplace.pizza.*;

public class EnschedeStore extends Store {

    @Override
    protected Pizza createPizza(String type) throws Exception {
        return switch (type) {
            case "cheese" -> new CheesePizza();
            case "pepperoni" -> new PepperoniPizza();
            case "greek" -> new GreekPizza();
            default -> throw new Exception("Pizza not on menu in Enschede");
        };
    }
}
