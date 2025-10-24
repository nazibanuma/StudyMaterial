package theatre2.dashboard;

import theatre2.dashboard.UserDashboardFrames.Profile;
import theatre2.dashboard.UserDashboardFrames.UpdateProfile;
import theatre2.dashboard.UserDashboardFrames.UpdatePassword;
import theatre2.dashboard.UserDashboardFrames.PurchaseHistory;
import java.awt.Color;
import theatre2.LoginSignup.LoginFrame;

public class UserDashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserDashboard.class.getName());

    Color DefaultColor, ClickedColor;
    private String loggedInUsername;
    private String filepath = "src/theatre2/data/users.txt";

    public UserDashboard(String username) {
        this.loggedInUsername = username;
        initComponents();
        DefaultColor = new Color(0, 153, 153);
        ClickedColor = new Color(0, 204, 204);

        profileButton.setBackground(ClickedColor);
        updateProfileButton.setBackground(DefaultColor);
        updatePasswordButton.setBackground(DefaultColor);
        purchaseHistoryButton.setBackground(DefaultColor);

        loadUserHeader();   // <<< make labels update

        Profile profile = new Profile(loggedInUsername);
        UserDesktopPane.removeAll();
        UserDesktopPane.add(profile);
        profile.setBounds(0, 0, UserDesktopPane.getWidth(), UserDesktopPane.getHeight());
        profile.setVisible(true);
        UserDesktopPane.revalidate();
        UserDesktopPane.repaint();
    }

    public UserDashboard() {
        this(null);   // will show empty labels until you set the username
    }
    
//    public void setName(String name){
//        this.BigNameLabel.setText(name);
//    }
//    public void setPhone(String phone){
//        this.BigPhoneNumberLabel.setText(phone);
//    }
    
    public void setHeaderText(String name, String phone) {
    BigNameLabel.setText(name);
    BigPhoneNumberLabel.setText(phone);
}

public void refreshHeader() {
    loadUserHeader(); // reuse your existing method
}


    private void loadUserHeader() {
        if (loggedInUsername == null || loggedInUsername.trim().isEmpty()) {
            return;
        }

        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(filepath));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] p = line.split("\\|"); // username|Name|Gender|Phone|Email|Address
                if (p.length >= 6) {
                    String u = p[0].trim();
                    if (u.equalsIgnoreCase(loggedInUsername)) {
                        String name = p[1].trim();
                        String phone = p[3].trim();

                        // Fallbacks if fields are empty
                        if (name.isEmpty()) {
                            name = u;
                        }

                        BigNameLabel.setText(name);
                        BigPhoneNumberLabel.setText(phone);
                        break; // found it
                    }
                }
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error reading users: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        profileButton = new javax.swing.JButton();
        updateProfileButton = new javax.swing.JButton();
        purchaseHistoryButton = new javax.swing.JButton();
        updatePasswordButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BigNameLabel = new javax.swing.JLabel();
        BigPhoneNumberLabel = new javax.swing.JLabel();
        mainMoviePageButton = new javax.swing.JButton();
        UserDesktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        profileButton.setBackground(new java.awt.Color(0, 153, 153));
        profileButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        profileButton.setForeground(new java.awt.Color(255, 255, 255));
        profileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/theatre2/icon/resume.png"))); // NOI18N
        profileButton.setText("  Profile");
        profileButton.setBorder(null);
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });

        updateProfileButton.setBackground(new java.awt.Color(0, 153, 153));
        updateProfileButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateProfileButton.setForeground(new java.awt.Color(255, 255, 255));
        updateProfileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/theatre2/icon/recycle.png"))); // NOI18N
        updateProfileButton.setText("  Update Profile");
        updateProfileButton.setBorder(null);
        updateProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfileButtonActionPerformed(evt);
            }
        });

        purchaseHistoryButton.setBackground(new java.awt.Color(0, 153, 153));
        purchaseHistoryButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        purchaseHistoryButton.setForeground(new java.awt.Color(255, 255, 255));
        purchaseHistoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/theatre2/icon/ticket.png"))); // NOI18N
        purchaseHistoryButton.setText("  Purchase History");
        purchaseHistoryButton.setBorder(null);
        purchaseHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseHistoryButtonActionPerformed(evt);
            }
        });

        updatePasswordButton.setBackground(new java.awt.Color(0, 153, 153));
        updatePasswordButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updatePasswordButton.setForeground(new java.awt.Color(255, 255, 255));
        updatePasswordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/theatre2/icon/padlock.png"))); // NOI18N
        updatePasswordButton.setText("  Update Password");
        updatePasswordButton.setBorder(null);
        updatePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePasswordButtonActionPerformed(evt);
            }
        });

        logoutButton.setBackground(new java.awt.Color(204, 0, 0));
        logoutButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/theatre2/icon/male_user_50px.png"))); // NOI18N
        logoutButton.setText("  Logout");
        logoutButton.setBorder(null);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 10));

        BigNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        BigNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        BigNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BigNameLabel.setPreferredSize(new java.awt.Dimension(122, 40));

        BigPhoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BigPhoneNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        BigPhoneNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BigPhoneNumberLabel.setPreferredSize(new java.awt.Dimension(110, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BigNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BigPhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(BigNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BigPhoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        mainMoviePageButton.setBackground(new java.awt.Color(0, 153, 153));
        mainMoviePageButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mainMoviePageButton.setForeground(new java.awt.Color(255, 255, 255));
        mainMoviePageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/theatre2/icon/clapperboard.png"))); // NOI18N
        mainMoviePageButton.setText("Main Movie Page");
        mainMoviePageButton.setBorder(null);
        mainMoviePageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMoviePageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(updateProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(updatePasswordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(purchaseHistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainMoviePageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updatePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purchaseHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainMoviePageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        UserDesktopPane.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout UserDesktopPaneLayout = new javax.swing.GroupLayout(UserDesktopPane);
        UserDesktopPane.setLayout(UserDesktopPaneLayout);
        UserDesktopPaneLayout.setHorizontalGroup(
            UserDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 894, Short.MAX_VALUE)
        );
        UserDesktopPaneLayout.setVerticalGroup(
            UserDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserDesktopPane))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(UserDesktopPane)
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

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        Profile profile = new Profile(loggedInUsername);
        UserDesktopPane.removeAll();
        UserDesktopPane.add(profile);
        profile.setBounds(0, 0, UserDesktopPane.getWidth(), UserDesktopPane.getHeight());
        profile.setVisible(true);
        UserDesktopPane.revalidate();
        UserDesktopPane.repaint();

        profileButton.setBackground(ClickedColor);
        updateProfileButton.setBackground(DefaultColor);
        updatePasswordButton.setBackground(DefaultColor);
        purchaseHistoryButton.setBackground(DefaultColor);
    }//GEN-LAST:event_profileButtonActionPerformed

    private void updateProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProfileButtonActionPerformed
        // TODO add your handling code here:
        UpdateProfile updateProfile = new UpdateProfile(loggedInUsername);
        UserDesktopPane.removeAll();
        UserDesktopPane.add(updateProfile);
        updateProfile.setBounds(0, 0, UserDesktopPane.getWidth(), UserDesktopPane.getHeight());
        updateProfile.setVisible(true);
        UserDesktopPane.revalidate();
        UserDesktopPane.repaint();

        profileButton.setBackground(DefaultColor);
        updateProfileButton.setBackground(ClickedColor);
        updatePasswordButton.setBackground(DefaultColor);
        purchaseHistoryButton.setBackground(DefaultColor);
    }//GEN-LAST:event_updateProfileButtonActionPerformed

    private void purchaseHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseHistoryButtonActionPerformed
        // TODO add your handling code here:
        PurchaseHistory purchaseHistory = new PurchaseHistory(loggedInUsername);
        UserDesktopPane.removeAll();
        UserDesktopPane.add(purchaseHistory);
        purchaseHistory.setBounds(0, 0, UserDesktopPane.getWidth(), UserDesktopPane.getHeight());
        purchaseHistory.setVisible(true);
        UserDesktopPane.revalidate();
        UserDesktopPane.repaint();

        profileButton.setBackground(DefaultColor);
        updateProfileButton.setBackground(DefaultColor);
        updatePasswordButton.setBackground(DefaultColor);
        purchaseHistoryButton.setBackground(ClickedColor);
    }//GEN-LAST:event_purchaseHistoryButtonActionPerformed

    private void updatePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePasswordButtonActionPerformed
        // TODO add your handling code here:
        UpdatePassword updatePassword = new UpdatePassword(loggedInUsername);
        UserDesktopPane.removeAll();
        UserDesktopPane.add(updatePassword);
        updatePassword.setBounds(0, 0, UserDesktopPane.getWidth(), UserDesktopPane.getHeight());
        updatePassword.setVisible(true);
        UserDesktopPane.revalidate();
        UserDesktopPane.repaint();

        profileButton.setBackground(DefaultColor);
        updateProfileButton.setBackground(DefaultColor);
        updatePasswordButton.setBackground(ClickedColor);
        purchaseHistoryButton.setBackground(DefaultColor);
    }//GEN-LAST:event_updatePasswordButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        new LoginFrame().setVisible(true);
        dispose(); // close the login frame

        profileButton.setBackground(DefaultColor);
        updateProfileButton.setBackground(DefaultColor);
        updatePasswordButton.setBackground(DefaultColor);
        purchaseHistoryButton.setBackground(DefaultColor);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void mainMoviePageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMoviePageButtonActionPerformed
        // TODO add your handling code here:
        new MainPage(loggedInUsername).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mainMoviePageButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BigNameLabel;
    private javax.swing.JLabel BigPhoneNumberLabel;
    private javax.swing.JDesktopPane UserDesktopPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton mainMoviePageButton;
    private javax.swing.JButton profileButton;
    private javax.swing.JButton purchaseHistoryButton;
    private javax.swing.JButton updatePasswordButton;
    private javax.swing.JButton updateProfileButton;
    // End of variables declaration//GEN-END:variables
}
