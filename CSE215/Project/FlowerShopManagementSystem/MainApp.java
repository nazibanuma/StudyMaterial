package flowershopmanagementsystem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static List<User> users = new ArrayList<>();
    private static List<Location> locations = new ArrayList<>();
    private static ShoppingCart cart = new ShoppingCart();

    public static void main(String[] args) {
        // Load users & data
        users = UserManager.loadUsers();
        locations = DataManager.loadAll();

        // Ensure at least one admin exists
        if (users.stream().noneMatch(u -> u.getUsername().equalsIgnoreCase("admin"))) {
            User admin = new User("admin", "admin123", "ADMIN");
            users.add(admin);
            UserManager.saveUser(admin);
        }

        // Seed demo data if file was empty
        if (locations.isEmpty()) {
            seedDemoData();
            DataManager.saveAll(locations);
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Flower Shop Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(960, 640);
            frame.setLocationRelativeTo(null);

            LoginPanel loginPanel = new LoginPanel(); // has Login + Register
            frame.setContentPane(loginPanel);
            frame.setVisible(true);

            // LOGIN
            loginPanel.getLoginBtn().addActionListener(e -> {
                String username = loginPanel.getUsernameField().getText().trim();
                String password = new String(loginPanel.getPasswordField().getPassword());

                User user = users.stream()
                        .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                        .findFirst().orElse(null);

                if (user == null) {
                    MessageDialog.showError("Invalid credentials!");
                    return;
                }

                if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                    showAdmin(frame);
                } else {
                    showCustomer(frame);
                }
            });

            // REGISTER
            loginPanel.getRegisterBtn().addActionListener(e -> {
                String username = loginPanel.getUsernameField().getText().trim();
                String password = new String(loginPanel.getPasswordField().getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    MessageDialog.showError("Please enter a username and password.");
                    return;
                }
                boolean exists = users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
                if (exists) {
                    MessageDialog.showError("Username already exists. Choose another one.");
                    return;
                }

                User newUser = new User(username, password, "CUSTOMER");
                users.add(newUser);
                UserManager.saveUser(newUser);
                MessageDialog.showInfo("Registration successful! You can now log in.");
                loginPanel.getUsernameField().setText("");
                loginPanel.getPasswordField().setText("");
            });
        });
    }

    private static void showAdmin(JFrame frame) {
        AdminPanel adminPanel = new AdminPanel();
        frame.setContentPane(adminPanel);
        frame.revalidate();

        // Populate combos with current data
        refreshLocationCombo(adminPanel);

        // Add Location
        adminPanel.getAddLocationBtn().addActionListener(ae -> {
            String loc = adminPanel.getLocationField().getText().trim();
            if (loc.isEmpty()) {
                MessageDialog.showError("Enter a location name.");
                return;
            }
            if (locations.stream().anyMatch(l -> l.getName().equalsIgnoreCase(loc))) {
                MessageDialog.showError("Location already exists.");
                return;
            }
            locations.add(new Location(loc));
            adminPanel.getLocationField().setText("");
            DataManager.saveAll(locations);
            refreshLocationCombo(adminPanel);
            MessageDialog.showInfo("Location added!");
        });

        // Add Shop
        adminPanel.getAddShopBtn().addActionListener(ae -> {
            String shopName = adminPanel.getShopField().getText().trim();
            String locName = (String) adminPanel.getLocationCombo().getSelectedItem();

            if (shopName.isEmpty() || locName == null) {
                MessageDialog.showError("Enter shop name and select a location.");
                return;
            }

            Location loc = findLocation(locName);
            if (loc == null) {
                MessageDialog.showError("Location not found.");
                return;
            }

            if (loc.getShops().stream().anyMatch(s -> s.getName().equalsIgnoreCase(shopName))) {
                MessageDialog.showError("Shop already exists in this location.");
                return;
            }

            loc.addShop(new FlowerShop(shopName));
            adminPanel.getShopField().setText("");
            DataManager.saveAll(locations);
            refreshShopCombo(adminPanel, loc);
            MessageDialog.showInfo("Shop added!");
        });

        // When location changes in the combo, refresh the shop combo
        adminPanel.getLocationCombo().addActionListener(ae -> {
            String locName = (String) adminPanel.getLocationCombo().getSelectedItem();
            Location loc = findLocation(locName);
            refreshShopCombo(adminPanel, loc);
        });

        // Add Flower
        adminPanel.getAddFlowerBtn().addActionListener(ae -> {
            String flowerName = adminPanel.getFlowerNameField().getText().trim();
            String color = adminPanel.getFlowerColorField().getText().trim();
            String priceText = adminPanel.getFlowerPriceField().getText().trim();
            String qtyText = adminPanel.getFlowerQtyField().getText().trim();
            String shopName = (String) adminPanel.getShopCombo().getSelectedItem();
            String locName = (String) adminPanel.getLocationCombo().getSelectedItem();

            if (flowerName.isEmpty() || color.isEmpty() || shopName == null || locName == null) {
                MessageDialog.showError("Please complete all fields and select location/shop.");
                return;
            }

            double price;
            int qty;
            try {
                price = Double.parseDouble(priceText);
                qty = Integer.parseInt(qtyText);
                if (price < 0 || qty < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                MessageDialog.showError("Enter valid numeric values for price and quantity.");
                return;
            }

            Location loc = findLocation(locName);
            if (loc == null) {
                MessageDialog.showError("Location not found.");
                return;
            }

            FlowerShop shop = loc.getShops().stream()
                    .filter(s -> s.getName().equals(shopName))
                    .findFirst().orElse(null);

            if (shop == null) {
                MessageDialog.showError("Shop not found.");
                return;
            }

            shop.addFlower(new Flower(flowerName, color, price, qty));
            adminPanel.getFlowerNameField().setText("");
            adminPanel.getFlowerColorField().setText("");
            adminPanel.getFlowerPriceField().setText("");
            adminPanel.getFlowerQtyField().setText("");
            DataManager.saveAll(locations);
            MessageDialog.showInfo("Flower added!");
        });
    }

    private static void showCustomer(JFrame frame) {
        CustomerPanel customerPanel = new CustomerPanel();
        frame.setContentPane(customerPanel);
        frame.revalidate();

        // Load locations into combo
        customerPanel.getLocationCombo().removeAllItems();
        for (Location loc : locations) {
            customerPanel.getLocationCombo().addItem(loc.getName());
        }

        // On location change → load shops
        customerPanel.getLocationCombo().addActionListener(ae -> {
            customerPanel.getShopCombo().removeAllItems();
            String locName = (String) customerPanel.getLocationCombo().getSelectedItem();
            if (locName == null) {
                customerPanel.setFlowers(null);
                return;
            }
            Location loc = findLocation(locName);
            if (loc == null) return;
            for (FlowerShop shop : loc.getShops()) {
                customerPanel.getShopCombo().addItem(shop.getName());
            }
            customerPanel.setFlowers(null);
        });

        // On shop change → refresh table with flowers
        customerPanel.getShopCombo().addActionListener(ae -> {
            String locName = (String) customerPanel.getLocationCombo().getSelectedItem();
            String shopName = (String) customerPanel.getShopCombo().getSelectedItem();
            if (locName == null || shopName == null) {
                customerPanel.setFlowers(null);
                return;
            }
            Location loc = findLocation(locName);
            if (loc == null) return;
            FlowerShop shop = loc.getShops().stream()
                    .filter(s -> s.getName().equals(shopName))
                    .findFirst().orElse(null);
            customerPanel.setFlowers(shop != null ? shop.getFlowers() : null);
        });

        // Add to cart
        customerPanel.getAddToCartBtn().addActionListener(ae -> {
            int row = customerPanel.getFlowerTable().getSelectedRow();
            if (row == -1) {
                MessageDialog.showError("Select a flower from the table.");
                return;
            }

            String qtyText = customerPanel.getQuantityField().getText().trim();
            int qty;
            try {
                qty = Integer.parseInt(qtyText);
                if (qty <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                MessageDialog.showError("Enter a valid positive quantity.");
                return;
            }

            String locName = (String) customerPanel.getLocationCombo().getSelectedItem();
            String shopName = (String) customerPanel.getShopCombo().getSelectedItem();
            if (locName == null || shopName == null) {
                MessageDialog.showError("Select a location and shop first.");
                return;
            }

            Location loc = findLocation(locName);
            if (loc == null) {
                MessageDialog.showError("Location not found.");
                return;
            }

            FlowerShop shop = loc.getShops().stream()
                    .filter(s -> s.getName().equals(shopName))
                    .findFirst().orElse(null);

            if (shop == null) {
                MessageDialog.showError("Shop not found.");
                return;
            }

            if (row < 0 || row >= shop.getFlowers().size()) {
                MessageDialog.showError("Invalid selection.");
                return;
            }

            Flower f = shop.getFlowers().get(row);
            if (qty > f.getQuantity()) {
                MessageDialog.showError("Not enough stock!");
                return;
            }

            cart.addItem(f, qty);
            f.setQuantity(f.getQuantity() - qty);
            DataManager.saveAll(locations);
            MessageDialog.showInfo("Added to cart.");

            // Refresh table properly
            customerPanel.setFlowers(shop.getFlowers());
        });

        // Purchase
        customerPanel.getPurchaseBtn().addActionListener(ae -> {
            if (cart.getItems().isEmpty()) {
                MessageDialog.showError("Cart is empty.");
                return;
            }
            String[] methods = {"Cash", "Credit Card", "UPI"};
            String choice = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select Payment Method:",
                    "Payment",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    methods,
                    methods[0]
            );
            if (choice != null) {
                double total = cart.getTotal();
                cart.clear();
                DataManager.saveAll(locations);
                MessageDialog.showInfo("Payment successful via " + choice + ". Total: " + total);
                // Refresh table after purchase
                String locName = (String) customerPanel.getLocationCombo().getSelectedItem();
                String shopName = (String) customerPanel.getShopCombo().getSelectedItem();
                Location loc = findLocation(locName);
                if (loc != null) {
                    FlowerShop shop = loc.getShops().stream()
                            .filter(s -> s.getName().equals(shopName))
                            .findFirst().orElse(null);
                    if (shop != null) customerPanel.setFlowers(shop.getFlowers());
                }
            }
        });
    }

    private static void refreshLocationCombo(AdminPanel adminPanel) {
        adminPanel.getLocationCombo().removeAllItems();
        for (Location loc : locations) {
            adminPanel.getLocationCombo().addItem(loc.getName());
        }
        // Also refresh shop combo for the selected location
        String locName = (String) adminPanel.getLocationCombo().getSelectedItem();
        refreshShopCombo(adminPanel, findLocation(locName));
    }

    private static void refreshShopCombo(AdminPanel adminPanel, Location loc) {
        adminPanel.getShopCombo().removeAllItems();
        if (loc == null) return;
        for (FlowerShop s : loc.getShops()) {
            adminPanel.getShopCombo().addItem(s.getName());
        }
    }

    private static Location findLocation(String name) {
        if (name == null) return null;
        return locations.stream()
                .filter(l -> l.getName().equals(name))
                .findFirst().orElse(null);
    }

    private static void seedDemoData() {
        Location dhaka = new Location("Dhaka");
        Location chittagong = new Location("Chittagong");

        FlowerShop bloom = new FlowerShop("Bloom & Co");
        bloom.addFlower(new Flower("Rose", "Red", 120, 50));
        bloom.addFlower(new Flower("Tulip", "Yellow", 90, 30));

        FlowerShop petal = new FlowerShop("Petal House");
        petal.addFlower(new Flower("Lily", "White", 150, 20));
        petal.addFlower(new Flower("Orchid", "Purple", 300, 10));

        dhaka.addShop(bloom);
        chittagong.addShop(petal);

        locations.add(dhaka);
        locations.add(chittagong);
    }
}
