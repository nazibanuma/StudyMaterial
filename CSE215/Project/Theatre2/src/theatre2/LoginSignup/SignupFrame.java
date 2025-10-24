package theatre2.LoginSignup;

import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class SignupFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SignupFrame.class.getName());

    private String filepath = "src/theatre2/data/password.txt";
    private String usersFilepath = "src/theatre2/data/users.txt";

    public SignupFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        showPasswordCheckBox = new java.awt.Checkbox();
        signupButton = new javax.swing.JButton();
        loginHereLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SIGNUP HERE");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Username");

        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Confirm Password");

        confirmPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        showPasswordCheckBox.setForeground(new java.awt.Color(0, 153, 153));
        showPasswordCheckBox.setLabel("Show Password");
        showPasswordCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showPasswordCheckBoxMousePressed(evt);
            }
        });

        signupButton.setBackground(new java.awt.Color(0, 153, 153));
        signupButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        signupButton.setForeground(new java.awt.Color(255, 255, 255));
        signupButton.setText("Signup");
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });

        loginHereLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loginHereLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginHereLabel.setText("Already have an account? Login here!");
        loginHereLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginHereLabelMousePressed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(showPasswordCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(usernameField)
                        .addComponent(signupButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginHereLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirmPasswordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)))
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPasswordCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginHereLabel)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void showPasswordCheckBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordCheckBoxMousePressed
        // TODO add your handling code here:
        if (!showPasswordCheckBox.getState()) {
            passwordField.setEchoChar((char) 0);
            confirmPasswordField.setEchoChar((char) 0); // show password
        } else {
            passwordField.setEchoChar('*');
            confirmPasswordField.setEchoChar('*'); // hide password
        }
    }//GEN-LAST:event_showPasswordCheckBoxMousePressed

    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        // TODO add your handling code here:
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        boolean exists = false;

        // Check if username already exists
        try {
            FileReader fr = new java.io.FileReader(filepath);
            BufferedReader br = new java.io.BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 1) {
                    String fileUsername = parts[0];
                    if (username.equalsIgnoreCase(fileUsername)) {
                        exists = true;
                        break;
                    }
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error reading users file: " + e.getMessage());
            return;
        }

        if (exists) {
            JOptionPane.showMessageDialog(this, "Username already exists. Choose another.");
            return;
        }

        // Append new user as USER
        try {
            java.io.FileWriter fw = new java.io.FileWriter(filepath, true);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
            bw.write(username + "|" + password + "|USER");
            bw.newLine();
            bw.close();
            fw.close();

            java.io.FileWriter fw1 = new java.io.FileWriter(usersFilepath, true);
            java.io.BufferedWriter bw1 = new java.io.BufferedWriter(fw1); // <- use fw1
            bw1.write(username + "|User|Gender|01700000000|example@gmail.com|Address");
            bw1.newLine();
            bw1.close();
            fw1.close();

            javax.swing.JOptionPane.showMessageDialog(this, "Signup successful! Please login.");
            new LoginFrame().setVisible(true);
            dispose();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error writing to users file: " + e.getMessage());
        }


    }//GEN-LAST:event_signupButtonActionPerformed

    private void loginHereLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginHereLabelMousePressed
        // TODO add your handling code here:
        new LoginFrame().setVisible(true);
        dispose(); // close the login frame
    }//GEN-LAST:event_loginHereLabelMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel loginHereLabel;
    private javax.swing.JPasswordField passwordField;
    private java.awt.Checkbox showPasswordCheckBox;
    private javax.swing.JButton signupButton;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
