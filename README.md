# 🛒 Java Shopping Cart Application

A simple Java Swing-based Shopping Cart system that demonstrates object-oriented programming, UI development with Swing, and data handling using DAO (Data Access Object) patterns.

## 🚀 Features

- Product catalog with CRUD operations
- Add/remove items to/from cart
- Dynamic JTable buttons using custom editors/renderers
- Checkout interface
- Modular code using MVC pattern

## 📁 Project Structure

src/
├── dao/

│   ├── DBConnection.java # Handles connection to the database


│  ├── ProductDAO.java         # Contains methods to interact with product data (CRUD)


│   └── main.java               # Main entry point of the application

│

├── model/

│   ├── CartItem.java           # Represents an item in the cart

│   └── Product.java            # Represents a product entity

│

├── ui/

├── ButtonEditor.java       # Custom editor for buttons in a table (e.g., in JTable)
    ├── ButtonRenderer.java     # Custom renderer for buttons in a table
    ├── CartUI.java             # User interface for managing the shopping cart
    └── CheckoutPage.java       # UI for the checkout process
