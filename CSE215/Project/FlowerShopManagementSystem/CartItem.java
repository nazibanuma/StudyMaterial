package flowershopmanagementsystem;

public class CartItem {
    private Flower flower;
    private int quantity;

    public CartItem(Flower flower, int quantity) {
        this.flower = flower;
        this.quantity = quantity;
    }

    public Flower getFlower() { return flower; }
    public int getQuantity() { return quantity; }
    public void addQuantity(int add) { this.quantity += add; }
    public double getTotalPrice() { return flower.getPrice() * quantity; }
}
