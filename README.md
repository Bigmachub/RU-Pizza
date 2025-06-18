# 🍕 Pizza Ordering App

## 📋 Overview
This project is an Android-based application that allows users to place and customize pizza orders from a menu of 4 different pizza types, each with a unique dough style. One of the pizzas is fully customizable, where users can choose from a variety of toppings. The app supports full order management including editing, viewing details, placing, and printing orders, along with basic sales tracking.

This project demonstrates object-oriented programming (OOP) principles such as **encapsulation** and **abstraction** to build a maintainable and scalable mobile application.

---

## 💡 Features

- Browse 4 unique pizza types with different dough styles
- Customize one pizza with selectable toppings
- Set base prices and topping costs
- Place orders and print receipts
- View and edit past/current orders
- See detailed breakdown of individual orders
- Track overall sales and performance

---

## 🧠 OOP Concepts Applied

### 🔒 Encapsulation
- Order, Pizza, and Topping data are kept private and accessed through clear, controlled methods to ensure integrity and prevent unintended changes.

### 🧩 Abstraction
- Complex behaviors like price calculations, order formatting, and topping management are abstracted behind clean interfaces for easy use by UI and controller logic.

---

## 📱 Tech Stack

- **Language**: Java (or Kotlin)
- **Platform**: Android (SDK 30+)
- **Tools**: Android Studio, XML for layout
- **Architecture**: MVC/MVVM design pattern
- **Persistence**: In-memory or optional SQLite for saved orders (optional)

---

## 🛒 Pizza Options Example

| Pizza Name       | Dough Style    | Customizable | Base Price |
|------------------|----------------|--------------|------------|
| Margherita       | Thin Crust     | ❌           | $10.00     |
| Pepperoni Deluxe | Hand-Tossed    | ❌           | $12.00     |
| Veggie Supreme   | Stuffed Crust  | ❌           | $11.00     |
| Custom Pizza     | Pan Crust      | ✅           | $8.00      |

*Toppings: $1.00 each (e.g., mushrooms, olives, bacon, onions, etc.)*

---

## 🧪 Key Actions

- **Add Pizza** → Choose a type or customize  
- **Edit Order** → Update pizza selection or quantity  
- **View Orders** → See list with full details  
- **Print Orders** → Export or simulate printed receipts  
- **Sales Summary** → View total orders and revenue

---

## 👨‍🍳 Author

**Andy Chen**  
Feel free to reach out if you have questions or would like to contribute!

---

## 📜 License

This project is for educational/demo purposes only.
