/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.pizzeria;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a generic pizza.
 * Provides common attributes and methods for all types of pizzas, including toppings, crust, and size.
 * The specific price calculation is left to the subclasses through the abstract {@link #price()} method.
 */
public abstract class Pizza {
    private List<Topping> toppings; // List of toppings on the pizza
    private Crust crust;  // Crust type for the pizza
    private Size size;    // Size of the pizza (Small, Medium, Large)

    /**
     * Constructor for the Pizza class.
     * Initializes the list of toppings as an empty {@link ArrayList}.
     */
    public Pizza() {
        this.toppings = new ArrayList<>(); // Initialize with ArrayList
    }

    /**
     * Abstract method to calculate the price of the pizza.
     * This method must be implemented by subclasses to define specific price calculations.
     * @return The price of the pizza as a {@code double} value.
     */
    public abstract double price();

    /**
     * Sets the crust type for the pizza.
     * @param crust The {@link Crust} type to set for this pizza.
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Gets the crust type of the pizza.
     * @return The {@link Crust} type of this pizza.
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Sets the size of the pizza.
     * @param size The {@link Size} to set for this pizza.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Gets the size of the pizza.
     * @return The {@link Size} of this pizza.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Gets the list of toppings on the pizza.
     * @return A {@link List} of {@link Topping} objects on this pizza.
     */
    public List<Topping> getToppings() {
        return toppings;
    }

    public String getName() {
        return this.getClass().getSimpleName(); // Returns the class name (e.g., Deluxe, Meatzza)
    }

    public String getStyle() {
        return crust != null ? crust.name() : "Unknown Style";
    }

    /**
     * Returns a string representation of the pizza,
     * including the list of toppings, crust type, and size.
     * @return A formatted string describing the pizza.
     */
    @Override
    public String toString() {
        return "Pizza{" +
                "toppings=" + toppings +
                ", crust=" + crust +
                ", size=" + size +
                '}';
    }
}