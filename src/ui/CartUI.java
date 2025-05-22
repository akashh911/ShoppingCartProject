package ui;

import dao.ProductDAO;
import model.CartItem;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CartUI {
    private static java.util.List<CartItem> cart = new java.util.ArrayList<>();

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Name", "Price", "Description", "Quantity", "Action"};
        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAllProducts();

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5; // Quantity and Action editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
        };

        for (Product p : products) {
            model.addRow(new Object[]{
                    p.getId(), p.getName(), p.getPrice(), p.getDescription(), p.getQuantity(), "Add to Cart"
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);

        // Set custom button renderer and editor for "Action"
        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), cart, products, table));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        checkoutBtn.setBackground(new Color(60, 179, 113));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFocusPainted(false);
        bottomPanel.add(checkoutBtn);

        // Checkout button action - open checkout window
        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cart.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Your cart is empty!");
                } else {
                    CheckoutPage.display(cart);
                }
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
