package flowershopmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerPanel extends JPanel {
    private JComboBox<String> locationCombo, shopCombo;
    private JTable flowerTable;
    private DefaultTableModel tableModel;
    private JTextField quantityField;
    private JButton addToCartBtn, purchaseBtn;

    public CustomerPanel() {
        setLayout(new BorderLayout(12,12));
        setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        // Top filters
        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        c.gridx = 0; c.gridy = 0;
        top.add(new JLabel("Location:"), c);
        locationCombo = new JComboBox<>();
        c.gridx = 1; top.add(locationCombo, c);

        c.gridx = 2; top.add(new JLabel("Shop:"), c);
        shopCombo = new JComboBox<>();
        c.gridx = 3; top.add(shopCombo, c);

        add(top, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"Name", "Color", "Price", "Quantity"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        flowerTable = new JTable(tableModel);
        flowerTable.setRowHeight(22);
        add(new JScrollPane(flowerTable), BorderLayout.CENTER);

        // Bottom
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(new JLabel("Qty:"));
        quantityField = new JTextField(6);
        bottom.add(quantityField);

        addToCartBtn = new JButton("Add to Cart");
        bottom.add(addToCartBtn);

        purchaseBtn = new JButton("Purchase");
        bottom.add(purchaseBtn);

        add(bottom, BorderLayout.SOUTH);
    }

    public void setFlowers(List<Flower> flowers) {
        tableModel.setRowCount(0);
        if (flowers == null) return;
        for (Flower f : flowers) {
            tableModel.addRow(new Object[]{f.getName(), f.getColor(), f.getPrice(), f.getQuantity()});
        }
    }

    // Getters
    public JComboBox<String> getLocationCombo() { return locationCombo; }
    public JComboBox<String> getShopCombo() { return shopCombo; }
    public JTable getFlowerTable() { return flowerTable; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTextField getQuantityField() { return quantityField; }
    public JButton getAddToCartBtn() { return addToCartBtn; }
    public JButton getPurchaseBtn() { return purchaseBtn; }
}
