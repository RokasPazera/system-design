package net.saxion.pizzaplace;

import net.saxion.pizzaplace.pizza.*;

public class Store {

    public Pizza orderPizza(String type) throws Exception {
        Pizza pizza;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("hawaii")) {
            pizza = new HawaiiPizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else {
            throw new Exception("Pizza not on menu");
        }

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
