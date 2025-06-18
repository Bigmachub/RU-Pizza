/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.pizzeria;

/**
 * Represents a Meatzza pizza, which is a predefined type of pizza with specific meat toppings.
 * The Meatzza pizza includes toppings such as Sausage, Pepperoni, Beef, and Ham.
 * This class extends the abstract {@link Pizza} class and provides its own implementation of the
 * {@code price()} method based on the pizza size.
 */
public class Meatzza extends Pizza {
    public Meatzza() {
        getToppings().add(Topping.SAUSAGE);
        getToppings().add(Topping.PEPPERONI);
        getToppings().add(Topping.BEEF);
        getToppings().add(Topping.HAM);
    }

    /**
     * Calculates the price of the Meatzza pizza based on its size.
     * The price varies for Small, Medium, and Large sizes.
     * @return The price of the pizza as a {@code double} value.
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL:
                return 17.99;
            case MEDIUM:
                return 19.99;
            case LARGE:
                return 21.99;
            default:
                return 0;
        }
    }

    /**
     * Returns a string representation of the Meatzza pizza,
     * including its size, crust type, and list of toppings.
     * @return A formatted string describing the Meatzza pizza.
     */
    @Override
    public String toString() {
        return "Meatzza Pizza, " + getSize() + ", Crust: " + getCrust() + ", Toppings: " + getToppings();
    }
}