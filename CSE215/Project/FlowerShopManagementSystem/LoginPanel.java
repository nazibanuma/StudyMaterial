package flowershopmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;

    public LoginPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(24,24,24,24));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        int row = 0;

        c.gridx = 0; c.gridy = row; add(new JLabel("Username:"), c);
        usernameField = new JTextField(); c.gridx = 1; add(usernameField, c); row++;

        c.gridx = 0; c.gridy = row; add(new JLabel("Password:"), c);
        passwordField = new JPasswordField(); c.gridx = 1; add(passwordField, c); row++;

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");
        btns.add(loginBtn);
        btns.add(registerBtn);
        c.gridx = 0; c.gridy = row; c.gridwidth = 2; add(btns, c);
    }

    public JTextField getUsernameField() { return usernameField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginBtn() { return loginBtn; }
    public JButton getRegisterBtn() { return registerBtn; }
}
