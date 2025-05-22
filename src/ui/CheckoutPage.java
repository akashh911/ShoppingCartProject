package ui;

import model.CartItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CheckoutPage {
    public static void display(List<CartItem> cart) {
        JFrame frame = new JFrame("Checkout - Billing");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"Product Name", "Quantity", "Price per unit", "Subtotal"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        double total = 0.0;

        for (CartItem item : cart) {
            double subtotal = item.getProduct().getPrice() * item.getQuantity();
            total += subtotal;
            model.addRow(new Object[]{
                    item.getProduct().getName(),
                    item.getQuantity(),
                    String.format("₹ %.2f", item.getProduct().getPrice()),
                    String.format("₹ %.2f", subtotal)
            });
        }

        JTable table = new JTable(model);
        table.setEnabled(false);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);

        JLabel totalLabel = new JLabel("Total Amount: ₹ " + String.format("%.2f", total));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(totalLabel, BorderLayout.CENTER);
        bottomPanel.add(closeButton, BorderLayout.EAST);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.setLayout(new BorderLayout(10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
