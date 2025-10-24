package theatre2.dashboard;

import java.awt.Color;
import theatre2.LoginSignup.LoginFrame;
import theatre2.dashboard.MainPageFrames.NowShowingFrame;
import theatre2.dashboard.MainPageFrames.ShowTime;
import theatre2.dashboard.MainPageFrames.UpcomingFrame;
import theatre2.dashboard.MainPageFrames.BuyTicket;


public class MainPage extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainPage.class.getName());

    private String movieFilePath = "src/theatre2/data/movies.txt";
    private String usersFilePath = "src/theatre2/data/users.txt";
    Color DefaultColor, ClickedColor;
    private String loggedInUsername = "";

    public MainPage(String username) {
        loggedInUsername = username;
        initComponents();
        DefaultColor = new Color(0, 153, 153);
        ClickedColor = new Color(0, 204, 204);

        NowShowingFrame ns = new NowShowingFrame();
        MainPageDesktopPane.add(ns);
        ns.setBounds(0, 0, MainPageDesktopPane.getWidth(), MainPageDesktopPane.getHeight());
        ns.setVisible(true);
        MainPageDesktopPane.revalidate();
        MainPageDesktopPane.repaint();

        nowShowingButton.setBackground(ClickedColor);
        upcomingButton.setBackground(DefaultColor);
        showtimesButton.setBackground(DefaultColor);
        buyTicketsButton.setBackground(DefaultColor);

        loadUsersName();

    }

    public MainPage() {
        initComponents();
        DefaultColor = new Color(0, 153, 153);
        ClickedColor = new Color(0, 204, 204);

        NowShowingFrame ns = new NowShowingFrame();
        MainPageDesktopPane.add(ns);
        ns.setBounds(0, 0, MainPageDesktopPane.getWidth(), MainPageDesktopPane.getHeight());
        ns.setVisible(true);
        MainPageDesktopPane.revalidate();
        MainPageDesktopPane.repaint();

        nowShowingButton.setBackground(ClickedColor);
        upcomingButton.setBackground(DefaultColor);
        showtimesButton.setBackground(DefaultColor);
        buyTicketsButton.setBackground(DefaultColor);

    }

    private void loadUsersName() {
        if (loggedInUsername == null || loggedInUsername.trim().isEmpty()) {
            return;
        }

        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(usersFilePath));
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

                        loginUserAdminButton.setText(name);
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

    public void openBuyTicketForMovie(String movieTitle) {
        if (loggedInUsername == null || loggedInUsername.isEmpty()) {
            new LoginFrame().setVisible(true);
            dispose();
            return;
        }
        BuyTicket buyTicket = new BuyTicket(loggedInUsername);
        if (movieTitle != null && !movieTitle.trim().isEmpty()) {
            buyTicket.preselectMovie(movieTitle);
        }

        MainPageDesktopPane.removeAll();
        MainPageDesktopPane.add(buyTicket);
        buyTicket.setBounds(0, 0, MainPageDesktopPane.getWidth(), MainPageDesktopPane.getHeight());
        buyTicket.setVisible(true);
        MainPageDesktopPane.revalidate();
        MainPageDesktopPane.repaint();

        nowShowingButton.setBackground(DefaultColor);
        upcomingButton.setBackground(DefaultColor);
        showtimesButton.setBackground(DefaultColor);
        buyTicketsButton.setBackground(ClickedColor);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        loginUserAdminButton = new javax.swing.JButton();
        buyTicketsButton = new javax.swing.JButton();
        showtimesButton = new javax.swing.JButton();
        nowShowingButton = new javax.swing.JButton();
        upcomingButton = new javax.swing.JButton();
        MainPageDesktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(204, 0, 102));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/theatre2/icon/logo3.png"))); // NOI18N

        loginUserAdminButton.setBackground(new java.awt.Color(0, 153, 153));
        loginUserAdminButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        loginUserAdminButton.setForeground(new java.awt.Color(255, 255, 255));
        loginUserAdminButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/theatre2/icon/make_user_20px.png"))); // NOI18N
        loginUserAdminButton.setText("Login");
        loginUserAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUserAdminButtonActionPerformed(evt);
            }
        });

        buyTicketsButton.setBackground(new java.awt.Color(0, 153, 153));
        buyTicketsButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buyTicketsButton.setForeground(new java.awt.Color(255, 255, 255));
        buyTicketsButton.setText("Buy Tickets");
        buyTicketsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyTicketsButtonActionPerformed(evt);
            }
        });

        showtimesButton.setBackground(new java.awt.Color(0, 153, 153));
        showtimesButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        showtimesButton.setForeground(new java.awt.Color(255, 255, 255));
        showtimesButton.setText("Showtimes");
        showtimesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showtimesButtonActionPerformed(evt);
            }
        });

        nowShowingButton.setBackground(new java.awt.Color(0, 153, 153));
        nowShowingButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nowShowingButton.setForeground(new java.awt.Color(255, 255, 255));
        nowShowingButton.setText("Now Showings");
        nowShowingButton.setMaximumSize(new java.awt.Dimension(135, 40));
        nowShowingButton.setMinimumSize(new java.awt.Dimension(135, 40));
        nowShowingButton.setPreferredSize(new java.awt.Dimension(135, 40));
        nowShowingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nowShowingButtonActionPerformed(evt);
            }
        });

        upcomingButton.setBackground(new java.awt.Color(0, 153, 153));
        upcomingButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        upcomingButton.setForeground(new java.awt.Color(255, 255, 255));
        upcomingButton.setText("Upcoming");
        upcomingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upcomingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(nowShowingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upcomingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showtimesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buyTicketsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(loginUserAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nowShowingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showtimesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buyTicketsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginUserAdminButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upcomingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPageDesktopPane.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MainPageDesktopPaneLayout = new javax.swing.GroupLayout(MainPageDesktopPane);
        MainPageDesktopPane.setLayout(MainPageDesktopPaneLayout);
        MainPageDesktopPaneLayout.setHorizontalGroup(
            MainPageDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        MainPageDesktopPaneLayout.setVerticalGroup(
            MainPageDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MainPageDesktopPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPageDesktopPane))
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

    private void showtimesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showtimesButtonActionPerformed
       
        ShowTime showTime = new ShowTime();

        MainPageDesktopPane.removeAll();
        MainPageDesktopPane.add(showTime);
        showTime.setBounds(0, 0, MainPageDesktopPane.getWidth(), MainPageDesktopPane.getHeight());
        showTime.setVisible(true);
        MainPageDesktopPane.revalidate();
        MainPageDesktopPane.repaint();

        nowShowingButton.setBackground(DefaultColor);
        upcomingButton.setBackground(DefaultColor);
        showtimesButton.setBackground(ClickedColor);
        buyTicketsButton.setBackground(DefaultColor);
    }//GEN-LAST:event_showtimesButtonActionPerformed

    private void buyTicketsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyTicketsButtonActionPerformed
        
        if (loggedInUsername == null || loggedInUsername.isEmpty()) {
            new LoginFrame().setVisible(true);
            dispose();
        } else {
            BuyTicket buyTicket = new BuyTicket(loggedInUsername);

            MainPageDesktopPane.removeAll();
            MainPageDesktopPane.add(buyTicket);
            buyTicket.setBounds(0, 0, MainPageDesktopPane.getWidth(), MainPageDesktopPane.getHeight());
            buyTicket.setVisible(true);
            MainPageDesktopPane.revalidate();
            MainPageDesktopPane.repaint();

            nowShowingButton.setBackground(DefaultColor);
            upcomingButton.setBackground(DefaultColor);
            showtimesButton.setBackground(DefaultColor);
            buyTicketsButton.setBackground(ClickedColor);
        }


    }//GEN-LAST:event_buyTicketsButtonActionPerformed

    private void loginUserAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginUserAdminButtonActionPerformed
       
        if (loggedInUsername == null || loggedInUsername.isEmpty()) {
            new LoginFrame().setVisible(true);
            dispose();
        } else {
            new UserDashboard(loggedInUsername).setVisible(true); // <-- pass it
            dispose();
        }
    }//GEN-LAST:event_loginUserAdminButtonActionPerformed

    private void nowShowingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nowShowingButtonActionPerformed
       
        NowShowingFrame ns = new NowShowingFrame();

        MainPageDesktopPane.removeAll();
        MainPageDesktopPane.add(ns);
        ns.setBounds(0, 0, MainPageDesktopPane.getWidth(), MainPageDesktopPane.getHeight());
        ns.setVisible(true);
        MainPageDesktopPane.revalidate();
        MainPageDesktopPane.repaint();

        nowShowingButton.setBackground(ClickedColor);
        upcomingButton.setBackground(DefaultColor);
        showtimesButton.setBackground(DefaultColor);
        buyTicketsButton.setBackground(DefaultColor);
    }//GEN-LAST:event_nowShowingButtonActionPerformed

    private void upcomingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upcomingButtonActionPerformed
       
        UpcomingFrame up = new UpcomingFrame();

        MainPageDesktopPane.removeAll();
        MainPageDesktopPane.add(up);
        up.setBounds(0, 0, MainPageDesktopPane.getWidth(), MainPageDesktopPane.getHeight());
        up.setVisible(true);
        MainPageDesktopPane.revalidate();
        MainPageDesktopPane.repaint();

        nowShowingButton.setBackground(DefaultColor);
        upcomingButton.setBackground(ClickedColor);
        showtimesButton.setBackground(DefaultColor);
        buyTicketsButton.setBackground(DefaultColor);
    }//GEN-LAST:event_upcomingButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane MainPageDesktopPane;
    private javax.swing.JButton buyTicketsButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginUserAdminButton;
    private javax.swing.JButton nowShowingButton;
    private javax.swing.JButton showtimesButton;
    private javax.swing.JButton upcomingButton;
    // End of variables declaration//GEN-END:variables
}
