/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.management;

import com.example.project5.pizzeria.Pizza;

/**
 * Interface defining the factory methods for creating various types of pizzas.
 * This interface follows the Factory Method design pattern, providing methods to create
 * specific types of pizzas based on the concrete factory implementation (e.g., New York or Chicago style).
 */
public interface PizzaFactory {

    /**
     * Creates a Deluxe pizza.
     * The specific crust and style are determined by the concrete implementation.
     * @return A new {@link Pizza} object of type Deluxe.
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza pizza.
     * The specific crust and style are determined by the concrete implementation.
     * @return A new {@link Pizza} object of type Meatzza.
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQ Chicken pizza.
     * The specific crust and style are determined by the concrete implementation.
     * @return A new {@link Pizza} object of type BBQ Chicken.
     */
    Pizza createBBQChicken();

    /**
     * Creates a Build Your Own pizza.
     * This method initializes a customizable pizza where the user can add their own toppings.
     * The specific crust and style are determined by the concrete implementation.
     * @return A new {@link Pizza} object of type Build Your Own.
     */
    Pizza createBuildYourOwn();
}
