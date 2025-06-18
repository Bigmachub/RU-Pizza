/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.pizzeria;

/**
 * Represents a BBQ Chicken pizza, which is a predefined type of pizza with specific toppings.
 * The BBQ Chicken pizza includes toppings such as BBQ Chicken, Green Pepper, Provolone, and Cheddar.
 * This class extends the abstract {@link Pizza} class and provides its own implementation of the
 * {@code price()} method based on the pizza size.
 */
public class BBQChicken extends Pizza {

    /**
     * Constructor for BBQChicken pizza.
     * Initializes the pizza with predefined toppings:
     * BBQ Chicken, Green Pepper, Provolone, and Cheddar.
     */
    public BBQChicken() {
        getToppings().add(Topping.BBQ_CHICKEN);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.PROVOLONE);
        getToppings().add(Topping.CHEDDAR);
    }

    /**
     * Calculates the price of the BBQ Chicken pizza based on its size.
     * The price varies for Small, Medium, and Large sizes.
     * @return The price of the pizza as a {@code double} value.
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL:
                return 14.99;
            case MEDIUM:
                return 16.99;
            case LARGE:
                return 19.99;
            default:
                return 0;
        }
    }

    /**
     * Returns a string representation of the BBQ Chicken pizza,
     * including its size, crust type, and list of toppings.
     * @return A formatted string describing the BBQ Chicken pizza.
     */
    @Override
    public String toString() {
        return "BBQ Chicken Pizza, " + getSize() + ", Crust: " + getCrust() + ", Toppings: " + getToppings();
    }
}
