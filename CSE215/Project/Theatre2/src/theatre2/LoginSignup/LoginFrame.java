package theatre2.LoginSignup;

import java.io.BufferedReader;
import java.io.FileReader;
import theatre2.dashboard.AdminDashboard;
import theatre2.dashboard.UserDashboard;
import theatre2.dashboard.MainPage;
import theatre2.LoginSignup.SignupFrame;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginFrame.class.getName());
    
    private String filepath = "src/theatre2/data/password.txt";

    public LoginFrame() {
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
        showPasswordCheckBox = new java.awt.Checkbox();
        loginButton = new javax.swing.JButton();
        signupHereLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 450));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("WELCOME BACK");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Username");

        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Password");

        showPasswordCheckBox.setForeground(new java.awt.Color(0, 153, 153));
        showPasswordCheckBox.setLabel("Show Password");
        showPasswordCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showPasswordCheckBoxMousePressed(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(0, 153, 153));
        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        signupHereLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        signupHereLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signupHereLabel.setText("Don't have an account? Signup here!");
        signupHereLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                signupHereLabelMousePressed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showPasswordCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(usernameField)
                    .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(signupHereLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
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
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPasswordCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signupHereLabel)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()); // get password from JPasswordField

        if (username.isEmpty() || password.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter both username and password");
            return;
        }

        boolean matchFound = false;

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String fileUsername = parts[0];
                    String filePassword = parts[1];
                    String role = parts[2];

                    if (username.equals(fileUsername) && password.equals(filePassword)) {
                        matchFound = true;

                        if (role.equalsIgnoreCase("ADMIN")) {
                            new AdminDashboard().setVisible(true);
                        } else if (role.equalsIgnoreCase("USER")) {
                            
                            //new UserDashboard(username).setVisible(true);
                            new MainPage(username).setVisible(true);//MainPage with the username constructor !!!!!!
                        }
                        this.dispose();
                        break;
                    }
                }
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        if (!matchFound) {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void signupHereLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupHereLabelMousePressed
       
        new SignupFrame().setVisible(true);
        dispose(); // close the login frame
    }//GEN-LAST:event_signupHereLabelMousePressed

    private void showPasswordCheckBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordCheckBoxMousePressed
     
        if (!showPasswordCheckBox.getState()) {
            passwordField.setEchoChar((char) 0); // show password
        } 
        else {
            passwordField.setEchoChar('*'); // hide password
        }
    }//GEN-LAST:event_showPasswordCheckBoxMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private java.awt.Checkbox showPasswordCheckBox;
    private javax.swing.JLabel signupHereLabel;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
