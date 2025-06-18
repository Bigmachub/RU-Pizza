package com.example.project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.pizzeria.*;
import java.util.List;

public class PizzaCartAdapter extends RecyclerView.Adapter<PizzaCartAdapter.PizzaViewHolder> {

    private final Context context;
    private final List<Pizza> pizzaList;
    private final OnPizzaClickListener listener;

    public PizzaCartAdapter(Context context, List<Pizza> pizzaList, OnPizzaClickListener listener) {
        this.context = context;
        this.pizzaList = pizzaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pizza_cart, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaNameTextView.setText(pizza instanceof BuildYourOwn ? "Build Your Own" : pizza.getClass().getSimpleName());
        holder.pizzaStyleTextView.setText(pizza.getCrust() != null ? "Style: " + pizza.getCrust() : "Style: None");
        List<Topping> toppings = pizza.getToppings();
        holder.pizzaToppingsTextView.setText(toppings.isEmpty() ? "Toppings: None" : "Toppings: " + toppings.toString());
        holder.pizzaPriceTextView.setText(holder.itemView.getContext().getString(R.string.pizza_price_label, pizza.price()));
        holder.itemView.setOnClickListener(v -> listener.onPizzaClick(pizza, position));
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public interface OnPizzaClickListener {
        void onPizzaClick(Pizza pizza, int position);
    }

    static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pizzaNameTextView, pizzaStyleTextView, pizzaToppingsTextView, pizzaPriceTextView;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaNameTextView = itemView.findViewById(R.id.pizzaNameTextView);
            pizzaStyleTextView = itemView.findViewById(R.id.pizzaStyleTextView);
            pizzaToppingsTextView = itemView.findViewById(R.id.pizzaToppingsTextView);
            pizzaPriceTextView = itemView.findViewById(R.id.pizzaPriceTextView);
        }
    }
}
