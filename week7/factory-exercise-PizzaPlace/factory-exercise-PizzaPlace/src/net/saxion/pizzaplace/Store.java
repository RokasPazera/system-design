package net.saxion.pizzaplace;

import net.saxion.pizzaplace.pizza.*;

public abstract class Store {

    public Pizza orderPizza(String type) throws Exception {
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String type) throws Exception;
}
