/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.pizzeria;

/**
 * Represents a Deluxe pizza, which is a predefined type of pizza with specific toppings.
 * The Deluxe pizza includes toppings such as Sausage, Pepperoni, Green Pepper, Onion, and Mushroom.
 * This class extends the abstract {@link Pizza} class and provides its own implementation of the
 * {@code price()} method based on the pizza size.
 */
public class Deluxe extends Pizza {

    /**
     * Constructor for Deluxe pizza.
     * Initializes the pizza with predefined toppings:
     * Sausage, Pepperoni, Green Pepper, Onion, and Mushroom.
     */
    public Deluxe() {
        getToppings().add(Topping.SAUSAGE);
        getToppings().add(Topping.PEPPERONI);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.ONION);
        getToppings().add(Topping.MUSHROOM);
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size.
     * The price varies for Small, Medium, and Large sizes.
     * @return The price of the pizza as a {@code double} value.
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL:
                return 16.99;
            case MEDIUM:
                return 18.99;
            case LARGE:
                return 20.99;
            default:
                return 0;
        }
    }

    /**
     * Returns a string representation of the Deluxe pizza,
     * including its size, crust type, and list of toppings.
     * @return A formatted string describing the Deluxe pizza.
     */
    @Override
    public String toString() {
        return "Deluxe Pizza, " + getSize() + ", Crust: " + getCrust() + ", Toppings: " + getToppings();
    }
}
