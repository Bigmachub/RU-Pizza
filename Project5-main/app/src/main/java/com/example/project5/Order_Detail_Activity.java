package com.example.project5;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.management.OrderManager;
import com.example.project5.pizzeria.Pizza;

import java.util.List;

public class Order_Detail_Activity extends AppCompatActivity implements PizzaCartAdapter.OnPizzaClickListener {
    private RecyclerView cartRecyclerView;
    private PizzaCartAdapter pizzaCartAdapter;
    private TextView subtotalLabel, taxLabel, totalCostLabel;
    private Button clearCartButton, addMorePizzaButton, pastOrdersButton, placeOrderButton;

    private OrderManager orderManager;
    private List<Pizza> pizzaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Initialize UI components
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        subtotalLabel = findViewById(R.id.subtotalLabel);
        taxLabel = findViewById(R.id.taxLabel);
        totalCostLabel = findViewById(R.id.totalCostLabel);
        clearCartButton = findViewById(R.id.clearCartButton);
        addMorePizzaButton = findViewById(R.id.addMorePizzaButton);
        pastOrdersButton = findViewById(R.id.pastOrdersButton);
        placeOrderButton = findViewById(R.id.placeOrderButton);

        // Get OrderManager instance and current order
        orderManager = OrderManager.getInstance();
        pizzaList = orderManager.getCurrentOrder().getPizzas();

        // Set up RecyclerView
        pizzaCartAdapter = new PizzaCartAdapter(this, pizzaList, this); // Pass listener
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(pizzaCartAdapter);

        // Update order details
        updateOrderDetails();

        // Set up button listeners
        setupListeners();
    }

    private void setupListeners() {
        // Clear cart functionality
        clearCartButton.setOnClickListener(v -> {
            if (pizzaList.isEmpty()) {
                Toast.makeText(this, "Cart is already empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            new AlertDialog.Builder(this)
                    .setTitle("Clear Cart")
                    .setMessage("Are you sure you want to clear the entire cart?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        pizzaList.clear();
                        pizzaCartAdapter.notifyDataSetChanged();
                        updateOrderDetails();
                        Toast.makeText(this, "Cart cleared.", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        // Add more pizza functionality
        addMorePizzaButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Order_Activity.class);
            startActivity(intent);
            finish(); // Close current activity
        });

        // Place order functionality
        placeOrderButton.setOnClickListener(v -> {
            if (pizzaList.isEmpty()) {
                Toast.makeText(this, "Cannot place an empty order.", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean orderPlaced = orderManager.placeOrder();
            if (orderPlaced) {
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                pizzaCartAdapter.notifyDataSetChanged();
                finish(); // Close the activity after placing the order
            } else {
                Toast.makeText(this, "Failed to place the order.", Toast.LENGTH_SHORT).show();
            }
        });

        // Past orders functionality
        pastOrdersButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Order_History_Activity.class);
            startActivity(intent); // Navigate to Order_History_Activity
        });
    }

    private void updateOrderDetails() {
        if (pizzaList.isEmpty()) {
            subtotalLabel.setText(getString(R.string.subtotal_label, 0.0));
            taxLabel.setText(getString(R.string.tax_label, 0.0));
            totalCostLabel.setText(getString(R.string.total_cost_label, 0.0));
            return;
        }
        double subtotal = 0.0;
        for (Pizza pizza : pizzaList) {
            subtotal += pizza.price();
        }
        double totalCost = orderManager.getCurrentOrder().calculateTotal();
        double tax = totalCost - subtotal;
        subtotalLabel.setText(getString(R.string.subtotal_label, subtotal));
        taxLabel.setText(getString(R.string.tax_label, tax));
        totalCostLabel.setText(getString(R.string.total_cost_label, totalCost));
    }


    @Override
    public void onPizzaClick(Pizza pizza, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Remove Pizza")
                .setMessage("Are you sure you want to remove this pizza from the cart?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    pizzaList.remove(position);
                    pizzaCartAdapter.notifyItemRemoved(position);
                    updateOrderDetails();
                    Toast.makeText(this, "Pizza removed.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
