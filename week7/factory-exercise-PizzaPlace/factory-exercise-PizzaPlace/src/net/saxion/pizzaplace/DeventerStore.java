package net.saxion.pizzaplace;

import net.saxion.pizzaplace.pizza.*;

public class DeventerStore extends Store {

    @Override
    protected Pizza createPizza(String type) throws Exception {
        return switch (type) {
            case "cheese" -> new CheesePizza();
            case "hawaii" -> new HawaiiPizza();
            case "pepperoni" -> new PepperoniPizza();
            default -> throw new Exception("Pizza not on menu in Deventer");
        };
    }
}
