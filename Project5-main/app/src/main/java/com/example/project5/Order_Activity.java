package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.pizzeria.*;
import com.example.project5.management.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Order_Activity extends AppCompatActivity {
    private Pizza currentPizza;
    private PizzaFactory currentFactory;
    private String selectedStyle, selectedType;
    private OrderManager orderManager;

    private Spinner pizzaStyleSpinner, pizzaSizeSpinner, pizzaTypeSpinner;
    private ListView availableToppingsListView, selectedToppingsListView;
    private Button addToOrderButton, clearSelectionButton, viewOrderCartButton;
    private TextView pizzaTotalLabel;
    private ImageView pizzaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderManager = OrderManager.getInstance();
        pizzaStyleSpinner = findViewById(R.id.pizzaStyleSpinner);
        pizzaSizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        pizzaTypeSpinner = findViewById(R.id.pizzaTypeSpinner);
        availableToppingsListView = findViewById(R.id.availableToppingsListView);
        selectedToppingsListView = findViewById(R.id.selectedToppingsListView);
        addToOrderButton = findViewById(R.id.addToOrderButton);
        clearSelectionButton = findViewById(R.id.clearSelectionButton);
        viewOrderCartButton = findViewById(R.id.viewOrderCartButton);
        pizzaTotalLabel = findViewById(R.id.pizzaTotalLabel);
        pizzaImageView = findViewById(R.id.pizzaImageView);
        ArrayAdapter<Topping> availableAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(Topping.values())));
        availableToppingsListView.setAdapter(availableAdapter);
        ArrayAdapter<Topping> selectedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        selectedToppingsListView.setAdapter(selectedAdapter);
        initializeDropdowns();
        setupListeners();
        pizzaTotalLabel.setText(getString(R.string.pizza_total_label, 0.00));
    }
    private void initializeDropdowns() {
        String defaultSelection = "Make a Selection";
        ArrayAdapter<String> styleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{defaultSelection, "New York Style", "Chicago Style"});
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaStyleSpinner.setAdapter(styleAdapter);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{defaultSelection, "Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"});
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaTypeSpinner.setAdapter(typeAdapter);
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{defaultSelection, "Small", "Medium", "Large"});
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaSizeSpinner.setAdapter(sizeAdapter);
    }
    private void setupListeners() {
        pizzaStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String style = parent.getItemAtPosition(position).toString();
                if (!style.equals("Make a Selection")) {
                    selectedStyle = style;
                    onPizzaStyleSelected(style);
                    if (selectedType != null) {
                        updatePizzaImage();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            } });
        pizzaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = parent.getItemAtPosition(position).toString();
                if (!type.equals("Make a Selection")) {
                    selectedType = type;
                    onPizzaTypeSelected(type);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            } });
        pizzaSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String size = parent.getItemAtPosition(position).toString();

                if (selectedStyle == null || selectedStyle.equals("Make a Selection")) {
                    Toast.makeText(Order_Activity.this, "Please select a pizza style before selecting size.", Toast.LENGTH_SHORT).show();
                    pizzaSizeSpinner.setSelection(0); // Reset size to "Make a Selection"
                    return;
                }
                if (selectedType == null || selectedType.equals("Make a Selection")) {
                    Toast.makeText(Order_Activity.this, "Please select a pizza type before selecting size.", Toast.LENGTH_SHORT).show();
                    pizzaSizeSpinner.setSelection(0); // Reset size to "Make a Selection"
                    return;
                }
                if (!size.equals("Make a Selection") && currentPizza != null) {
                    currentPizza.setSize(Size.valueOf(size.toUpperCase()));
                    updatePizzaPrice();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        availableToppingsListView.setOnItemClickListener((parent, view, position, id) -> {
            if (currentPizza instanceof BuildYourOwn) {
                Topping topping = (Topping) parent.getItemAtPosition(position);
                moveToppingToSelected(topping);
            } else {
                Toast.makeText(this, "Topping selection is only allowed for 'Build Your Own' pizzas.", Toast.LENGTH_SHORT).show();
            } });
        selectedToppingsListView.setOnItemClickListener((parent, view, position, id) -> {
            if (currentPizza instanceof BuildYourOwn) {
                Topping topping = (Topping) parent.getItemAtPosition(position);
                moveToppingToAvailable(topping);
            } else {
                Toast.makeText(this, "Topping deselection is only allowed for 'Build Your Own' pizzas.", Toast.LENGTH_SHORT).show();
            } });
        addToOrderButton.setOnClickListener(v -> onAddToOrderClick());
        clearSelectionButton.setOnClickListener(v -> clearSelection());
        viewOrderCartButton.setOnClickListener(v -> viewOrderCart());
    }

    private void onPizzaStyleSelected(String style) {
        switch (style) {
            case "New York Style":
                currentFactory = new NYPizza();
                break;
            case "Chicago Style":
                currentFactory = new ChicagoPizza();
                break;
            default:
                currentFactory = null;
                Toast.makeText(this, "Invalid pizza style selected.", Toast.LENGTH_SHORT).show();
                return;
        }
        updatePizzaImage();
        if (selectedType != null && selectedType.equals("Build Your Own")) {
            ArrayAdapter<Topping> availableAdapter = (ArrayAdapter<Topping>) availableToppingsListView.getAdapter();
            ArrayAdapter<Topping> selectedAdapter = (ArrayAdapter<Topping>) selectedToppingsListView.getAdapter();
            if (availableAdapter != null) {
                availableAdapter.clear();
                availableAdapter.addAll(Arrays.asList(Topping.values()));
            }
            if (selectedAdapter != null) {
                selectedAdapter.clear(); }
            if (availableAdapter != null) availableAdapter.notifyDataSetChanged();
            if (selectedAdapter != null) selectedAdapter.notifyDataSetChanged(); }
        if (selectedType != null && !selectedType.equals("Make a Selection")) {
            onPizzaTypeSelected(selectedType); }
    }
    private void onPizzaTypeSelected(String type) {
        if (type.equals("Make a Selection")) {
            Toast.makeText(this, "Please select a valid pizza type.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedStyle == null || selectedStyle.equals("Make a Selection")) {
            Toast.makeText(this, "Please select a pizza style before selecting type.", Toast.LENGTH_SHORT).show();
            pizzaTypeSpinner.setSelection(0);
            return;
        }
        selectedType = type;
        ArrayAdapter<Topping> selectedAdapter = (ArrayAdapter<Topping>) selectedToppingsListView.getAdapter();
        ArrayAdapter<Topping> availableAdapter = (ArrayAdapter<Topping>) availableToppingsListView.getAdapter();
        if (selectedAdapter != null) selectedAdapter.clear();
        if (availableAdapter != null) availableAdapter.clear();
        switch (type) {
            case "Deluxe":
                currentPizza = currentFactory.createDeluxe();
                selectedAdapter.addAll(currentPizza.getToppings());
                disableToppingsSelection();
                break;

            case "BBQ Chicken":
                currentPizza = currentFactory.createBBQChicken();
                selectedAdapter.addAll(currentPizza.getToppings());
                disableToppingsSelection();
                break;

            case "Meatzza":
                currentPizza = currentFactory.createMeatzza();
                selectedAdapter.addAll(currentPizza.getToppings());
                disableToppingsSelection();
                break;

            case "Build Your Own":
                currentPizza = currentFactory.createBuildYourOwn();
                availableAdapter.addAll(Arrays.asList(Topping.values()));
                enableToppingsSelection();
                break;

            default:
                Toast.makeText(this, "Invalid pizza type selected.", Toast.LENGTH_SHORT).show();
                return;
        }
        selectedAdapter.notifyDataSetChanged();
        availableAdapter.notifyDataSetChanged();
        updatePizzaImage();
        String selectedSize = pizzaSizeSpinner.getSelectedItem() != null
                ? pizzaSizeSpinner.getSelectedItem().toString()
                : "Make a Selection";

        if (!selectedSize.equals("Make a Selection") && currentPizza != null) {
            currentPizza.setSize(Size.valueOf(selectedSize.toUpperCase()));
            updatePizzaPrice();
        }
    }
    private void updatePizzaPrice() {
        if (currentPizza == null || currentPizza.getSize() == null) {
            pizzaTotalLabel.setText(getString(R.string.pizza_total_label, 0.00));
            return;
        }
        double price = currentPizza.price();
        if (currentPizza instanceof BuildYourOwn) {
            int toppingCount = selectedToppingsListView.getCount();
            price += toppingCount * BuildYourOwn.getToppingCost();
        }
        pizzaTotalLabel.setText(getString(R.string.pizza_total_label, price));
    }
    private void updatePizzaImage() {
        if (selectedStyle == null || selectedType == null ||
                selectedStyle.equals("Make a Selection") || selectedType.equals("Make a Selection")) {
            pizzaImageView.setImageResource(0); // Default/blank image
            return;
        }
        String imagePath = selectedStyle.toLowerCase().replace(" ", "_")
                + "_" + selectedType.toLowerCase().replace(" ", "_");
        int imageResId = getResources().getIdentifier(imagePath, "drawable", getPackageName());
        if (imageResId != 0) {
            pizzaImageView.setImageResource(imageResId);
        } else {
            pizzaImageView.setImageResource(0); // Default/blank image if not found
            Toast.makeText(this, "Image not found for the selected style and type.", Toast.LENGTH_SHORT).show();
        }
    }
    private void moveToppingToSelected(Topping topping) {
        ArrayAdapter<Topping> availableAdapter = (ArrayAdapter<Topping>) availableToppingsListView.getAdapter();
        ArrayAdapter<Topping> selectedAdapter = (ArrayAdapter<Topping>) selectedToppingsListView.getAdapter();
        if (availableAdapter != null && selectedAdapter != null) {
            if (selectedAdapter.getCount() >= 7) {
                Toast.makeText(this, "Maximum of 7 toppings allowed for Build Your Own pizzas.", Toast.LENGTH_SHORT).show();
                return;  }  // Prevent adding more toppings
            availableAdapter.remove(topping);
            selectedAdapter.add(topping);
            availableAdapter.notifyDataSetChanged();
            selectedAdapter.notifyDataSetChanged();
            updatePizzaPrice();
        }
    }
    private void moveToppingToAvailable(Topping topping) {
        ArrayAdapter<Topping> availableAdapter = (ArrayAdapter<Topping>) availableToppingsListView.getAdapter();
        ArrayAdapter<Topping> selectedAdapter = (ArrayAdapter<Topping>) selectedToppingsListView.getAdapter();
        if (availableAdapter != null && selectedAdapter != null) {
            selectedAdapter.remove(topping);
            availableAdapter.add(topping);
            availableAdapter.notifyDataSetChanged();
            selectedAdapter.notifyDataSetChanged();
            updatePizzaPrice();
        }
    }
    private void clearSelection() {
        pizzaStyleSpinner.setSelection(0);
        pizzaTypeSpinner.setSelection(0);
        pizzaSizeSpinner.setSelection(0);
        ArrayAdapter<Topping> selectedAdapter = (ArrayAdapter<Topping>) selectedToppingsListView.getAdapter();
        ArrayAdapter<Topping> availableAdapter = (ArrayAdapter<Topping>) availableToppingsListView.getAdapter();
        if (selectedAdapter != null) selectedAdapter.clear();
        if (availableAdapter != null) {
            availableAdapter.clear();
            availableAdapter.addAll(Arrays.asList(Topping.values())); // Reset available toppings
        }
        if (selectedAdapter != null) selectedAdapter.notifyDataSetChanged();
        if (availableAdapter != null) availableAdapter.notifyDataSetChanged();
        currentPizza = null;
        currentFactory = null;
        selectedType = null;
        selectedStyle = null;
        pizzaTotalLabel.setText(getString(R.string.pizza_total_label, 0.00));
        pizzaImageView.setImageResource(0);
    }
    private void onAddToOrderClick() {
        if (selectedType == null || selectedType.equals("Make a Selection")) {
            Toast.makeText(this, "Please select a pizza type before adding to the order.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedStyle == null || selectedStyle.equals("Make a Selection")) {
            Toast.makeText(this, "Please select a pizza style before adding to the order.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (currentPizza == null || currentPizza.getSize() == null) {
            Toast.makeText(this, "Please select a pizza size before adding to the order.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (currentPizza instanceof BuildYourOwn) {
            // Add user-selected toppings to the pizza
            ArrayList<Topping> selectedToppings = new ArrayList<>();
            for (int i = 0; i < selectedToppingsListView.getCount(); i++) {
                selectedToppings.add((Topping) selectedToppingsListView.getItemAtPosition(i));
            }
            currentPizza.getToppings().clear();
            currentPizza.getToppings().addAll(selectedToppings);
        }
        orderManager.addPizzaToCurrentOrder(currentPizza);
        Toast.makeText(this, "Pizza added to order!", Toast.LENGTH_SHORT).show();
        clearSelection();
    }
    private void viewOrderCart() {
        Intent intent = new Intent(this, Order_Detail_Activity.class);
        startActivity(intent);
    }

    private void enableToppingsSelection() {
        availableToppingsListView.setEnabled(true);
        selectedToppingsListView.setEnabled(true);
    }
    private void disableToppingsSelection() {
        availableToppingsListView.setEnabled(false);
        selectedToppingsListView.setEnabled(false);
    }
}
