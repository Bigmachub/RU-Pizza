/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.management;

import com.example.project5.pizzeria.*;

/**
 * Concrete factory class for creating Chicago-style pizzas.
 * Implements the {@link PizzaFactory} interface to provide specific implementations
 * of Chicago-style pizza types with predefined crusts.
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Deluxe pizza with a Chicago-style Deep Dish crust.
     * @return A new {@link Deluxe} pizza with Deep Dish crust.
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.DEEP_DISH);
        return pizza;
    }

    /**
     * Creates a Meatzza pizza with a Chicago-style Stuffed crust.
     * @return A new {@link Meatzza} pizza with Stuffed crust.
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.STUFFED);
        return pizza;
    }

    /**
     * Creates a BBQ Chicken pizza with a Chicago-style Pan crust.
     * @return A new {@link BBQChicken} pizza with Pan crust.
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.PAN);
        return pizza;
    }

    /**
     * Creates a Build Your Own pizza with a Chicago-style Pan crust.
     * This method initializes a customizable pizza with no predefined toppings.
     * @return A new {@link BuildYourOwn} pizza with Pan crust.
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.PAN);
        return pizza;
    }
}