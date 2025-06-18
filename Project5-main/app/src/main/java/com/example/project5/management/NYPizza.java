/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.management;

import com.example.project5.pizzeria.*;

/**
 * Concrete factory class for creating New York-style pizzas.
 * Implements the {@link PizzaFactory} interface to provide specific implementations
 * of New York-style pizza types with predefined crusts.
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a Deluxe pizza with a New York-style Brooklyn crust.
     * @return A new {@link Deluxe} pizza with Brooklyn crust.
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.BROOKLYN);
        return pizza;
    }

    /**
     * Creates a Meatzza pizza with a New York-style Hand-Tossed crust.
     * @return A new {@link Meatzza} pizza with Hand-Tossed crust.
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.HAND_TOSSED);
        return pizza;
    }

    /**
     * Creates a BBQ Chicken pizza with a New York-style Thin crust.
     * @return A new {@link BBQChicken} pizza with Thin crust.
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.THIN);
        return pizza;
    }

    /**
     * Creates a Build Your Own pizza with a New York-style Hand-Tossed crust.
     * This method initializes a customizable pizza with no predefined toppings.
     * @return A new {@link BuildYourOwn} pizza with Hand-Tossed crust.
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.HAND_TOSSED);
        return pizza;
    }
}