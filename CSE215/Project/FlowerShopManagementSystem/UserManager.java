package flowershopmanagementsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String FILE_PATH = "users.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return users;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if(parts.length == 3) {
                    users.add(new User(parts[0], parts[1], parts[2]));
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole());
            bw.newLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
