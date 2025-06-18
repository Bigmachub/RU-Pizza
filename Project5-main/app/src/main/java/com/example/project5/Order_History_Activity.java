package com.example.project5;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.management.Order;
import com.example.project5.management.OrderManager;

import java.util.List;

public class Order_History_Activity extends AppCompatActivity {
    private RecyclerView orderHistoryRecyclerView;
    private OrderHistoryAdapter orderHistoryAdapter;
    private Button backToOrderViewButton, clearHistoryButton;

    private OrderManager orderManager;
    private List<Order> historicalOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        // Initialize UI components
        orderHistoryRecyclerView = findViewById(R.id.orderHistoryRecyclerView);
        backToOrderViewButton = findViewById(R.id.backToOrderViewButton);
        clearHistoryButton = findViewById(R.id.clearHistoryButton);

        // Get OrderManager instance
        orderManager = OrderManager.getInstance();
        historicalOrders = orderManager.getHistoricalOrders();

        // Set up RecyclerView with item click listener
        orderHistoryAdapter = new OrderHistoryAdapter(historicalOrders, this::showOrderOptionsDialog);
        orderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderHistoryRecyclerView.setAdapter(orderHistoryAdapter);

        // Set up button listeners
        setupListeners();
    }

    private void setupListeners() {
        // Back to Order View (Main Menu) functionality
        backToOrderViewButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Order_Activity.class);
            startActivity(intent);
            finish(); // Close the current activity
        });

        // Clear history functionality
        clearHistoryButton.setOnClickListener(v -> {
            if (orderManager.getHistoricalOrders().isEmpty()) {
                Toast.makeText(this, "No history to clear.", Toast.LENGTH_SHORT).show();
                return;
            }
            new AlertDialog.Builder(this)
                    .setTitle("Clear History")
                    .setMessage("Are you sure you want to clear the entire order history?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        orderManager.getHistoricalOrders().clear();
                        orderHistoryAdapter.notifyDataSetChanged();
                        Toast.makeText(this, "Order history cleared.", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    private void showOrderOptionsDialog(Order order, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Order Options")
                .setMessage("What would you like to do with this order?")
                .setPositiveButton("Edit Order", (dialog, which) -> editOrder(order))
                .setNegativeButton("Cancel Order", (dialog, which) -> cancelOrder(order, position))
                .setNeutralButton("Close", null)
                .show();
    }

    private void cancelOrder(Order order, int position) {
        historicalOrders.remove(position);
        orderHistoryAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Order canceled.", Toast.LENGTH_SHORT).show();
    }

    private void editOrder(Order order) {
        orderManager.setCurrentOrder(order); // Set the selected order as the current order
        Intent intent = new Intent(this, Order_Detail_Activity.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}
