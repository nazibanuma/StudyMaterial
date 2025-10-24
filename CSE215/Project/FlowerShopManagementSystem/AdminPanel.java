package flowershopmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    private JTextField locationField;
    private JTextField shopField;
    private JTextField flowerNameField;
    private JTextField flowerColorField;
    private JTextField flowerPriceField;
    private JTextField flowerQtyField;
    private JComboBox<String> locationCombo;
    private JComboBox<String> shopCombo;
    private JButton addLocationBtn, addShopBtn, addFlowerBtn;

    public AdminPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        int row = 0;

        // Location
        c.gridx = 0; c.gridy = row; add(new JLabel("Location:"), c);
        locationField = new JTextField(); c.gridx = 1; add(locationField, c);
        addLocationBtn = new JButton("Add Location"); c.gridx = 2; add(addLocationBtn, c);
        row++;

        // Select Location
        c.gridx = 0; c.gridy = row; add(new JLabel("Select Location:"), c);
        locationCombo = new JComboBox<>(); c.gridx = 1; c.gridwidth = 2; add(locationCombo, c);
        c.gridwidth = 1; row++;

        // Shop
        c.gridx = 0; c.gridy = row; add(new JLabel("Shop Name:"), c);
        shopField = new JTextField(); c.gridx = 1; add(shopField, c);
        addShopBtn = new JButton("Add Shop"); c.gridx = 2; add(addShopBtn, c);
        row++;

        // Select Shop
        c.gridx = 0; c.gridy = row; add(new JLabel("Select Shop:"), c);
        shopCombo = new JComboBox<>(); c.gridx = 1; c.gridwidth = 2; add(shopCombo, c);
        c.gridwidth = 1; row++;

        // Flowers
        c.gridx = 0; c.gridy = row; add(new JLabel("Flower Name:"), c);
        flowerNameField = new JTextField(); c.gridx = 1; c.gridwidth = 2; add(flowerNameField, c);
        c.gridwidth = 1; row++;

        c.gridx = 0; c.gridy = row; add(new JLabel("Color:"), c);
        flowerColorField = new JTextField(); c.gridx = 1; c.gridwidth = 2; add(flowerColorField, c);
        c.gridwidth = 1; row++;

        c.gridx = 0; c.gridy = row; add(new JLabel("Price:"), c);
        flowerPriceField = new JTextField(); c.gridx = 1; c.gridwidth = 2; add(flowerPriceField, c);
        c.gridwidth = 1; row++;

        c.gridx = 0; c.gridy = row; add(new JLabel("Quantity:"), c);
        flowerQtyField = new JTextField(); c.gridx = 1; c.gridwidth = 2; add(flowerQtyField, c);
        c.gridwidth = 1; row++;

        addFlowerBtn = new JButton("Add Flower"); c.gridx = 1; c.gridy = row; c.gridwidth = 2; add(addFlowerBtn, c);
    }

    // Getters
    public JTextField getLocationField() { return locationField; }
    public JTextField getShopField() { return shopField; }
    public JTextField getFlowerNameField() { return flowerNameField; }
    public JTextField getFlowerColorField() { return flowerColorField; }
    public JTextField getFlowerPriceField() { return flowerPriceField; }
    public JTextField getFlowerQtyField() { return flowerQtyField; }
    public JComboBox<String> getLocationCombo() { return locationCombo; }
    public JComboBox<String> getShopCombo() { return shopCombo; }
    public JButton getAddLocationBtn() { return addLocationBtn; }
    public JButton getAddShopBtn() { return addShopBtn; }
    public JButton getAddFlowerBtn() { return addFlowerBtn; }
}
