package ui;

import model.CartItem;
import model.Product;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private int row;
    private JTable table;
    private List<CartItem> cart;
    private List<Product> products;

    public ButtonEditor(JCheckBox checkBox, List<CartItem> cart, List<Product> products, JTable table) {
        this.cart = cart;
        this.products = products;
        this.table = table;
        button = new JButton("Add to Cart");
        button.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.row = row;
        button.setText(value.toString());
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();

        Object qtyObj = table.getValueAt(row, 4);
        int qty;

        try {
            if (qtyObj instanceof Integer) {
                qty = (Integer) qtyObj;
            } else {
                qty = Integer.parseInt(qtyObj.toString());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(table, "Please enter a valid integer quantity.");
            return;
        }

        if (qty <= 0) {
            JOptionPane.showMessageDialog(table, "Quantity must be greater than zero.");
            return;
        }

        Product product = products.get(row);
        if (qty > product.getQuantity()) {
            JOptionPane.showMessageDialog(table, "Quantity exceeds available stock (" + product.getQuantity() + ").");
            return;
        }

        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProduct().getId() == product.getId()) {
                int newQty = item.getQuantity() + qty;
                if (newQty > product.getQuantity()) {
                    JOptionPane.showMessageDialog(table, "Total quantity in cart exceeds stock.");
                    return;
                }
                item.setQuantity(newQty);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(product, qty));
        }

        JOptionPane.showMessageDialog(table, qty + " x '" + product.getName() + "' added to cart!");
    }
}
