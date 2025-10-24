package flowershopmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private List<FlowerShop> shops;

    public Location(String name) {
        this.name = name;
        this.shops = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<FlowerShop> getShops() { return shops; }

    public void addShop(FlowerShop shop) { shops.add(shop); }
}
