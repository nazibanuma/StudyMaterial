package flowershopmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class FlowerShop {
    private String name;
    private List<Flower> flowers;

    public FlowerShop(String name) {
        this.name = name;
        this.flowers = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Flower> getFlowers() { return flowers; }

    public void addFlower(Flower flower) { flowers.add(flower); }
}
