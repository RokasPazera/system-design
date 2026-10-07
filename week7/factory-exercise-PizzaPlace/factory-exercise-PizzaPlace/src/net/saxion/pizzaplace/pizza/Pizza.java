package net.saxion.pizzaplace.pizza;

import java.util.ArrayList;

public abstract class Pizza {

    String name;
    String dough;
    String sauce;

    ArrayList<String> toppings = new ArrayList<>();

    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (String topping : toppings) {
            System.out.println("\t" + topping);
        }
    }

    public void bake() {
        System.out.println("Bake for 25 minutes at 180C");
    }

    public void cut() {
        System.out.println("Cut pizza into diagonal slices");
    }

    public void  box() {
        System.out.println("Place pizza in box");
    }

    public String getName() {
        return name;
    }
}
