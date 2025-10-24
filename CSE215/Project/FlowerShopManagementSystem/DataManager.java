package flowershopmanagementsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String FILE_PATH = "data.txt";

    // Format:
    // L|<Location>
    // S|<Location>|<Shop>
    // F|<Location>|<Shop>|<Name>|<Color>|<Price>|<Qty>

    public static List<Location> loadAll() {
        List<Location> locations = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return locations;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|", -1);
                if (parts.length < 2) continue;

                String type = parts[0];
                switch (type) {
                    case "L": {
                        // L|Dhaka
                        String locName = parts[1];
                        if (findLocation(locations, locName) == null) {
                            locations.add(new Location(locName));
                        }
                        break;
                    }
                    case "S": {
                        // S|Dhaka|Bloom & Co
                        if (parts.length < 3) break;
                        String locName = parts[1];
                        String shopName = parts[2];
                        Location loc = findOrCreateLocation(locations, locName);
                        if (loc.getShops().stream().noneMatch(s -> s.getName().equals(shopName))) {
                            loc.addShop(new FlowerShop(shopName));
                        }
                        break;
                    }
                    case "F": {
                        // F|Dhaka|Bloom & Co|Rose|Red|120.0|50
                        if (parts.length < 7) break;
                        String locName = parts[1];
                        String shopName = parts[2];
                        String name = parts[3];
                        String color = parts[4];
                        double price = Double.parseDouble(parts[5]);
                        int qty = Integer.parseInt(parts[6]);

                        Location loc = findOrCreateLocation(locations, locName);
                        FlowerShop shop = loc.getShops().stream()
                                .filter(s -> s.getName().equals(shopName))
                                .findFirst()
                                .orElseGet(() -> {
                                    FlowerShop ns = new FlowerShop(shopName);
                                    loc.addShop(ns);
                                    return ns;
                                });

                        shop.addFlower(new Flower(name, color, price, qty));
                        break;
                    }
                    default: // ignore
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public static void saveAll(List<Location> locations) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Location loc : locations) {
                bw.write("L|" + loc.getName()); bw.newLine();
                for (FlowerShop shop : loc.getShops()) {
                    bw.write("S|" + loc.getName() + "|" + shop.getName()); bw.newLine();
                    for (Flower f : shop.getFlowers()) {
                        bw.write("F|" + loc.getName() + "|" + shop.getName() + "|" +
                                f.getName() + "|" + f.getColor() + "|" + f.getPrice() + "|" + f.getQuantity());
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helpers
    private static Location findLocation(List<Location> list, String name) {
        return list.stream().filter(l -> l.getName().equals(name)).findFirst().orElse(null);
    }

    private static Location findOrCreateLocation(List<Location> list, String name) {
        Location l = findLocation(list, name);
        if (l == null) {
            l = new Location(name);
            list.add(l);
        }
        return l;
    }
}
