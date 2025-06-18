package com.example.project5.management;

import com.example.project5.pizzeria.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the orders in the RU Pizzeria Management System.
 * This class handles the current active order being created and maintains a history of all past orders.
 * It provides functionality for adding, managing, and exporting orders.
 */
public class OrderManager {
    private static OrderManager instance;            // Singleton instance
    private List<Order> historicalOrders;           // List of past (historical) orders
    private Order currentOrder;                     // The active order being created

    /**
     * Private constructor for OrderManager.
     * Initializes empty lists for current and historical orders.
     */
    private OrderManager() {
        this.historicalOrders = new ArrayList<>();
        this.currentOrder = new Order();            // Start with a new active order
    }

    /**
     * Provides the Singleton instance of OrderManager.
     * If the instance does not exist, it creates one.
     * @return The Singleton instance of OrderManager.
     */
    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    /**
     * Returns the active order being built.
     * @return The current order.
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Returns the list of historical orders.
     * @return A list of historical (past) orders.
     */
    public List<Order> getHistoricalOrders() {
        return historicalOrders;
    }

    /**
     * Adds a pizza to the current order.
     * @param pizza The pizza to add.
     */
    public void addPizzaToCurrentOrder(Pizza pizza) {
        if (pizza != null) {
            currentOrder.addPizza(pizza);
        }
    }

    /**
     * Places the current order, adds it to the historical orders list, and resets the active order.
     * @return true if the order was placed successfully, false otherwise.
     */
    public boolean placeOrder() {
        if (currentOrder != null && !currentOrder.getPizzas().isEmpty()) {
            if (!historicalOrders.contains(currentOrder)) { // Ensure no duplicates
                historicalOrders.add(currentOrder);        // Add to historical orders
            }
            this.currentOrder = new Order();               // Reset currentOrder for the next transaction
            return true; // Order successfully placed
        }
        return false; // Order was not placed
    }

    /**
     * Cancels the specified order by removing it from the historical orders list.
     * @param order The order to be canceled.
     * @return true if the order was found and canceled, false otherwise.
     */
    public boolean cancelOrder(Order order) {
        if (order == null) {
            return false;
        }
        if (historicalOrders.contains(order)) {
            historicalOrders.remove(order);
            return true;
        }
        return false;
    }


    /**
     * Clears the current order, resetting to a new empty order.
     */
    public void clearCurrentOrder() {
        if (currentOrder != null) {
            currentOrder.clearOrder();  // Use clearOrder to empty the list of pizzas
        }
    }

    /**
     * Sets the current order to the order passed in.
     * @param order The order to be set as current.
     */
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }
}
