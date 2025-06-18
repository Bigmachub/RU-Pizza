/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.pizzeria;

import java.util.List;

/**
 * Represents a customizable pizza where the user can build their own pizza
 * by choosing the desired toppings. This class extends the abstract {@link Pizza} class.
 * The price of the pizza is based on its size and the number of toppings.
 */
public class BuildYourOwn extends Pizza {

    private static final double TOPPING_COST = 1.69; // Cost of a single topping

    /**
     * Default Constructor for BuildYourOwn pizza.
     * Initializes a pizza with no predefined toppings.
     * The toppings can be added dynamically using the {@link #setToppings(List)} method.
     */
    public BuildYourOwn() {
        super();
    }

    /**
     * Calculates the price of the Build Your Own pizza based on its size and the number of toppings.
     * The base price varies by size, and each topping incurs an additional cost.
     *
     * @return The total price of the pizza as a {@code double} value.
     */
    @Override
    public double price() {
        double basePrice;
        switch (getSize()) {
            case SMALL:
                basePrice = 8.99;
                break;
            case MEDIUM:
                basePrice = 10.99;
                break;
            case LARGE:
                basePrice = 12.99;
                break;
            default:
                basePrice = 0; // Default base price for undefined size
        }

        // Calculate the additional cost based on the number of toppings
        double toppingCost = getToppings().size() * TOPPING_COST;
        return basePrice + toppingCost;
    }

    /**
     * Sets the list of toppings for the pizza.
     * This method replaces any existing toppings with the new list.
     *
     * @param toppings The list of toppings to set for this pizza.
     */
    public void setToppings(List<Topping> toppings) {
        // Clear any existing toppings and add new ones
        getToppings().clear();
        getToppings().addAll(toppings);
    }

    /**
     * Returns the cost of a single topping for the Build Your Own pizza.
     *
     * @return The cost of one topping as a {@code double} value.
     */
    public static double getToppingCost() {
        return TOPPING_COST;
    }

    /**
     * Returns a string representation of the Build Your Own pizza,
     * including its size, crust type, and list of selected toppings.
     *
     * @return A formatted string describing the Build Your Own pizza.
     */
    @Override
    public String toString() {
        StringBuilder toppingsDescription = new StringBuilder();

        // Append all toppings to the description
        for (Topping topping : getToppings()) {
            toppingsDescription.append(topping.name()).append(", ");
        }

        // Remove the trailing comma and space, if any toppings exist
        if (toppingsDescription.length() > 0) {
            toppingsDescription.setLength(toppingsDescription.length() - 2);
        }

        // Format the string representation
        return String.format("Build Your Own Pizza: Size = %s, Crust = %s, Toppings = [%s]",
                getSize() != null ? getSize().name() : "None",
                getCrust() != null ? getCrust().name() : "None",
                toppingsDescription.length() > 0 ? toppingsDescription.toString() : "None");
    }
}
