package flowershopmanagementsystem;

import javax.swing.*;

public class MessageDialog {
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
