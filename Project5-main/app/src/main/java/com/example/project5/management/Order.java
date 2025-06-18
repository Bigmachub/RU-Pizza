/**
 * @author Shreeyut
 * @author Andy
 */
package com.example.project5.management;

import com.example.project5.pizzeria.Pizza;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer order in the RU Pizzeria Management System.
 * Each order has a unique order number and contains a list of pizzas.
 * The order number is automatically generated and incremented for each new order.
 */
public class Order {
    private static int nextOrderNumber = 1;  // Static field to generate unique order numbers
    private int number;                      // Unique order number for each order
    private List<Pizza> pizzas;              // List of pizzas in the order

    /**
     * Constructs an empty Order with a unique order number and an empty list of pizzas.
     */
    public Order() {
        this.number = nextOrderNumber++;
        this.pizzas = new ArrayList<>(); // Initialize with ArrayList
    }

    /**
     * Adds a pizza to the order.
     * @param pizza The pizza to add.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Clears all pizzas from the order.
     */
    public void clearOrder() {
        pizzas.clear();
    }

    /**
     * Calculates the total price of the order including tax.
     * @return The total price including tax.
     */
    public double calculateTotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        double tax = subtotal * 0.06625; // NJ sales tax rate
        return subtotal + tax;
    }

    /**
     * Returns the list of pizzas in the order.
     * @return A list of pizzas in the order.
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Gets a string representation of the order details.
     * @return A string with the order details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Pizza pizza : pizzas) {
            sb.append(pizza.toString()).append("\n");
        }
        sb.append("Order Total: $").append(String.format("%.2f", calculateTotal()));
        return sb.toString();
    }
}