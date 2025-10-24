package flowershopmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() { items = new ArrayList<>(); }

    public void addItem(Flower flower, int quantity) {
        // Merge same item for nicer UX
        for (CartItem it : items) {
            if (it.getFlower() == flower) {
                it.addQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(flower, quantity));
    }

    public List<CartItem> getItems() { return items; }

    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clear() { items.clear(); }
}
