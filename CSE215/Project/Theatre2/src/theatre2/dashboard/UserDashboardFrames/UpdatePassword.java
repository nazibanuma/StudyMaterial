/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package theatre2.dashboard.UserDashboardFrames;

import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author AURORA
 */
public class UpdatePassword extends javax.swing.JInternalFrame {

    private String loggedInUsername;
    private String filepath = "src/theatre2/data/password.txt";

    public UpdatePassword(String username) {
        this.loggedInUsername = username;
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    public UpdatePassword() {
        this(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        currenPasswordField = new javax.swing.JTextField();
        updatePasswordButton = new javax.swing.JButton();
        newPasswordField = new javax.swing.JPasswordField();
        confirmnewPasswordField = new javax.swing.JPasswordField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Current Password:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("New Password");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Confirm New Password:");

        currenPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        currenPasswordField.setForeground(new java.awt.Color(0, 0, 0));

        updatePasswordButton.setBackground(new java.awt.Color(0, 153, 153));
        updatePasswordButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updatePasswordButton.setForeground(new java.awt.Color(255, 255, 255));
        updatePasswordButton.setText("Update Password");
        updatePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePasswordButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newPasswordField)
                            .addComponent(currenPasswordField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(confirmnewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updatePasswordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(currenPasswordField))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(newPasswordField))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(confirmnewPasswordField))
                .addGap(29, 29, 29)
                .addComponent(updatePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void updatePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePasswordButtonActionPerformed
        // TODO add your handling code here:
        // must know who is logged in
        if (loggedInUsername == null || loggedInUsername.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No logged-in user.");
            return;
        }

        String current = currenPasswordField.getText().trim();
        String newPass = new String(newPasswordField.getPassword()).trim();
        String confirm = new String(confirmnewPasswordField.getPassword()).trim();

        if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }
        if (!newPass.equals(confirm)) {
            javax.swing.JOptionPane.showMessageDialog(this, "New passwords do not match.");
            return;
        }

        java.io.BufferedReader br = null;
        StringBuilder out = new StringBuilder();
        boolean userFound = false;
        boolean updated = false;

        try {
            br = new java.io.BufferedReader(new java.io.FileReader(filepath));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    out.append("\n");
                    continue;
                }
                // expected format: username|password|ROLE
                String[] p = line.split("\\|");
                if (p.length >= 2 && p[0].trim().equals(loggedInUsername)) {
                    userFound = true;
                    String oldPass = p[1].trim();
                    String role = (p.length >= 3) ? p[2].trim() : "USER";

                    if (!current.equals(oldPass)) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Current password is incorrect.");
                        out.append(line).append("\n");  // keep original
                    } else {
                        out.append(loggedInUsername).append("|").append(newPass).append("|").append(role).append("\n");
                        updated = true;
                    }
                } else {
                    out.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            return;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ignore) {
            }
        }

        if (!userFound) {
            javax.swing.JOptionPane.showMessageDialog(this, "User not found in password file.");
            return;
        }
        if (!updated) {
            // already showed a message (current password wrong) or nothing changed
            return;
        }

        java.io.BufferedWriter bw = null;
        try {
            bw = new java.io.BufferedWriter(new java.io.FileWriter(filepath, false)); // overwrite
            bw.write(out.toString());
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
            return;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception ignore) {
            }
        }

        javax.swing.JOptionPane.showMessageDialog(this, "Password updated.");
        // reset fields
        currenPasswordField.setText("");
        newPasswordField.setText("");
        confirmnewPasswordField.setText("");
    }//GEN-LAST:event_updatePasswordButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmnewPasswordField;
    private javax.swing.JTextField currenPasswordField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JButton updatePasswordButton;
    // End of variables declaration//GEN-END:variables
}
