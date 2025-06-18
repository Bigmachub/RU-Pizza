package com.example.project5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.management.Order;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {
    private final List<Order> orderList;
    private final OnOrderClickListener onOrderClickListener;

    public OrderHistoryAdapter(List<Order> orderList, OnOrderClickListener onOrderClickListener) {
        this.orderList = orderList;
        this.onOrderClickListener = onOrderClickListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.orderNumberTextView.setText(holder.itemView.getContext().getString(R.string.order_number_label, position + 1));
        holder.orderDetailsTextView.setText(order.toString());

        holder.itemView.setOnClickListener(v -> {
            if (onOrderClickListener != null) {
                onOrderClickListener.onOrderClick(order, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public interface OnOrderClickListener {
        void onOrderClick(Order order, int position);
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumberTextView, orderDetailsTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumberTextView = itemView.findViewById(R.id.orderNumberTextView);
            orderDetailsTextView = itemView.findViewById(R.id.orderDetailsTextView);
        }
    }
}
