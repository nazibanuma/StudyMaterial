package theatre2.dashboard.MainPageFrames;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import theatre2.LoginSignup.PaymentFrame;

public class BuyTicket extends javax.swing.JInternalFrame {

    private String ticketPriceFilePath = "src/theatre2/data/tickets.txt";
    private String bookingFilePath = "src/theatre2/data/bookings.txt";
    private String userFilePath = "src/theatre2/data/users.txt";
    private String loggedInUsername;

    // simple state
    private java.util.ArrayList<String[]> currentRows = new java.util.ArrayList<>(); // matched show rows from tickets.txt
    private int currentUnitPrice = 0;
    private String currentUserFullName = "";
    private String currentUserPhone = "";

    public BuyTicket(String username) {
        loggedInUsername = username;
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        if (yearField.getText().trim().isEmpty()) {
            yearField.setText("2025");
        }

        // SAFE null check
        if (loggedInUsername != null && !loggedInUsername.isEmpty()) {
            loadUserNameAndPhone(loggedInUsername);
        }

        loadMoviesFromTickets();

        // listeners (very basic)
        movieNameComboBox.addActionListener(e -> {
            updateShowTimes();
            updateUnitPriceAndTotal();
        });
        dayField.addActionListener(e -> {
            updateShowTimes();
            updateUnitPriceAndTotal();
        });
        monthField.addActionListener(e -> {
            updateShowTimes();
            updateUnitPriceAndTotal();
        });
        yearField.addActionListener(e -> {
            updateShowTimes();
            updateUnitPriceAndTotal();
        });
        showTimeComboBox.addActionListener(e -> updateUnitPriceAndTotal());

        // recalc on Enter
        ticketQuantityField.addActionListener(e -> updateUnitPriceAndTotal());
        // recalc while typing (still basic)
        ticketQuantityField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                updateUnitPriceAndTotal();
            }
        });
    }

    public BuyTicket() {
        this(null);
    }

    private void loadUserNameAndPhone(String username) {
        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(userFilePath));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] p = line.split("\\|"); // username|Name|Gender|Phone|Email|Address
                if (p.length >= 6 && username.equalsIgnoreCase(p[0].trim())) {
                    currentUserFullName = p[1].trim();
                    currentUserPhone = p[3].trim();
                    break;
                }
            }
        } catch (Exception ignore) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    /**
     * Call this right after creating the frame to select a movie title.
     */
    public void preselectMovie(String movieTitle) {
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            return;
        }
        // ensure items exist
        if (movieNameComboBox.getItemCount() == 0) {
            loadMoviesFromTickets();
        }
        for (int i = 0; i < movieNameComboBox.getItemCount(); i++) {
            String item = (String) movieNameComboBox.getItemAt(i); 
            if (movieTitle.equalsIgnoreCase(item)) {
                movieNameComboBox.setSelectedIndex(i);
                break;
            }
        }
        // refresh dependent fields
        updateShowTimes();
        updateUnitPriceAndTotal();
    }

    private void loadMoviesFromTickets() {
        movieNameComboBox.removeAllItems();
        movieNameComboBox.addItem("Select Movie");
        java.util.ArrayList<String> seen = new java.util.ArrayList<>();

        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(ticketPriceFilePath));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] p = line.split("\\|"); // Movie|Screen|Date|Start|End|Price
                if (p.length >= 1) {
                    String mv = p[0].trim();
                    if (!mv.isEmpty() && !seen.contains(mv)) {
                        seen.add(mv);
                        movieNameComboBox.addItem(mv);
                    }
                }
            }
        } catch (java.io.FileNotFoundException ignore) {
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading movies: " + ex.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    private String selectedDate() {
        String d = (String) dayField.getSelectedItem();
        String m = (String) monthField.getSelectedItem();
        String y = yearField.getText().trim();
        if (d == null) {
            d = "";
        }
        if (m == null) {
            m = "";
        }
        if (y.isEmpty()) {
            y = "2025";
        }
        return d + " " + m + " " + y; // must match format in tickets.txt
    }

    private void updateShowTimes() {
        showTimeComboBox.removeAllItems();
        currentRows.clear();
        ticketPriceLabel.setText("");
        calculatedPriceLabel.setText("");
        currentUnitPrice = 0;

        String movie = (String) movieNameComboBox.getSelectedItem();
        if (movie == null || "Select Movie".equalsIgnoreCase(movie)) {
            return;
        }

        String date = selectedDate();

        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(ticketPriceFilePath));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] p = line.split("\\|"); // Movie|Screen|Date|Start|End|Price
                if (p.length >= 6) {
                    String mv = p[0].trim();
                    String sc = p[1].trim();
                    String dt = p[2].trim();
                    String st = p[3].trim();
                    String en = p[4].trim();
                    String pr = p[5].trim();
                    if (mv.equalsIgnoreCase(movie) && dt.equalsIgnoreCase(date)) {
                        showTimeComboBox.addItem(sc + " | " + st + " - " + en);
                        currentRows.add(new String[]{mv, sc, dt, st, en, pr});
                    }
                }
            }
        } catch (java.io.FileNotFoundException ignore) {
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading showtimes: " + ex.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private int getQty() {
        String t = ticketQuantityField.getText().trim();
        return t.isEmpty() ? 0 : parseInt(t);
    }

    private void updateUnitPriceAndTotal() {
        int idx = showTimeComboBox.getSelectedIndex();
        if (idx < 0 || idx >= currentRows.size()) {
            ticketPriceLabel.setText("");
            calculatedPriceLabel.setText("");
            currentUnitPrice = 0;
            return;
        }
        String[] row = currentRows.get(idx);
        currentUnitPrice = parseInt(row[5]); // price
        ticketPriceLabel.setText(String.valueOf(currentUnitPrice));

        int total = currentUnitPrice * getQty();
        calculatedPriceLabel.setText(String.valueOf(total));
    }

    private void saveBookingWithPayment() {
        String movie = (String) movieNameComboBox.getSelectedItem();
        if (movie == null || "Select Movie".equalsIgnoreCase(movie)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a movie.");
            return;
        }
        int idx = showTimeComboBox.getSelectedIndex();
        if (idx < 0 || idx >= currentRows.size()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a show time.");
            return;
        }
        int qty = getQty();
        if (qty <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Enter a valid ticket quantity.");
            return;
        }
        if (ticketPriceLabel.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Price not available.");
            return;
        }

        String[] r = currentRows.get(idx); // 0 Movie | 1 Screen | 2 Date | 3 Start | 4 End | 5 Price
        int unit = currentUnitPrice;
        int total = unit * qty;

        // 7-field format (same as Booking frame uses):
        // CustomerName|Phone|Movie|Date|Screen | Start - End|Qty|Total
        String line = (currentUserFullName == null ? "" : currentUserFullName) + "|"
                + (currentUserPhone == null ? "" : currentUserPhone) + "|"
                + r[0] + "|" + r[2] + "|" + (r[1] + " | " + r[3] + " - " + r[4]) + "|"
                + qty + "|" + total + "|" + loggedInUsername;

        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        PaymentFrame.PaymentListener onPaid = () -> {
            try (java.io.FileWriter fw = new java.io.FileWriter(bookingFilePath, true); java.io.BufferedWriter bw = new java.io.BufferedWriter(fw)) {
                bw.write(line);
                bw.newLine();
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error saving booking: " + e.getMessage());
                return;
            }
            javax.swing.JOptionPane.showMessageDialog(this, "Payment successful. Booking saved.\nTotal: " + total);
            // reset minimal fields
            ticketQuantityField.setText("");
            calculatedPriceLabel.setText("");
        };

        PaymentFrame payment = new PaymentFrame(parent, onPaid);
        payment.setLocationRelativeTo(parent);
        payment.setVisible(true);
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ticketQuantityField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        discardButton = new javax.swing.JButton();
        purchaseTicketButton = new javax.swing.JButton();
        movieNameComboBox = new javax.swing.JComboBox<>();
        showTimeComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        dayField = new javax.swing.JComboBox<>();
        monthField = new javax.swing.JComboBox<>();
        yearField = new javax.swing.JTextField();
        calculatedPriceLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ticketPriceLabel = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1300, 900));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Show Time");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ticket Quantity");

        ticketQuantityField.setMinimumSize(new java.awt.Dimension(600, 30));
        ticketQuantityField.setPreferredSize(new java.awt.Dimension(600, 30));
        ticketQuantityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketQuantityFieldActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Calculated Price");

        discardButton.setBackground(new java.awt.Color(204, 0, 51));
        discardButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        discardButton.setForeground(new java.awt.Color(255, 255, 255));
        discardButton.setText("Discard");
        discardButton.setMaximumSize(new java.awt.Dimension(190, 45));
        discardButton.setMinimumSize(new java.awt.Dimension(190, 45));
        discardButton.setPreferredSize(new java.awt.Dimension(190, 45));
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        purchaseTicketButton.setBackground(new java.awt.Color(0, 153, 153));
        purchaseTicketButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        purchaseTicketButton.setForeground(new java.awt.Color(255, 255, 255));
        purchaseTicketButton.setText("Purchase Ticket");
        purchaseTicketButton.setMaximumSize(new java.awt.Dimension(190, 45));
        purchaseTicketButton.setMinimumSize(new java.awt.Dimension(190, 45));
        purchaseTicketButton.setPreferredSize(new java.awt.Dimension(190, 45));
        purchaseTicketButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                purchaseTicketButtonMousePressed(evt);
            }
        });
        purchaseTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseTicketButtonActionPerformed(evt);
            }
        });

        movieNameComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        movieNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Movie" }));
        movieNameComboBox.setMinimumSize(new java.awt.Dimension(72, 30));
        movieNameComboBox.setPreferredSize(new java.awt.Dimension(600, 30));

        showTimeComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        showTimeComboBox.setMinimumSize(new java.awt.Dimension(72, 30));
        showTimeComboBox.setPreferredSize(new java.awt.Dimension(600, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Date");

        dayField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        dayField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayFieldActionPerformed(evt);
            }
        });

        monthField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        yearField.setText("2025");
        yearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearFieldActionPerformed(evt);
            }
        });

        calculatedPriceLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        calculatedPriceLabel.setForeground(new java.awt.Color(0, 153, 153));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Movie Name");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Ticket Price");

        ticketPriceLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(discardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(purchaseTicketButton, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                                .addComponent(calculatedPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(dayField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(monthField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(movieNameComboBox, 0, 624, Short.MAX_VALUE)
                                .addComponent(showTimeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ticketPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel8)
                                .addComponent(jLabel14)
                                .addComponent(jLabel12)
                                .addComponent(ticketQuantityField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movieNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showTimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ticketPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ticketQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calculatedPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(purchaseTicketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1088, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1088, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ticketQuantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticketQuantityFieldActionPerformed
        
        updateUnitPriceAndTotal();
    }//GEN-LAST:event_ticketQuantityFieldActionPerformed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        
        movieNameComboBox.setSelectedIndex(0);
        dayField.setSelectedIndex(0);
        monthField.setSelectedIndex(0);
        yearField.setText("2025");
        showTimeComboBox.removeAllItems();
        ticketQuantityField.setText("");
        ticketPriceLabel.setText("");
        calculatedPriceLabel.setText("");
        currentRows.clear();
        currentUnitPrice = 0;
    }//GEN-LAST:event_discardButtonActionPerformed

    private void purchaseTicketButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseTicketButtonMousePressed
       
    }//GEN-LAST:event_purchaseTicketButtonMousePressed

    private void purchaseTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseTicketButtonActionPerformed
       
        saveBookingWithPayment();
    }//GEN-LAST:event_purchaseTicketButtonActionPerformed

    private void dayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayFieldActionPerformed
        
    }//GEN-LAST:event_dayFieldActionPerformed

    private void yearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearFieldActionPerformed
        
    }//GEN-LAST:event_yearFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel calculatedPriceLabel;
    private javax.swing.JComboBox<String> dayField;
    private javax.swing.JButton discardButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> monthField;
    private javax.swing.JComboBox<String> movieNameComboBox;
    private javax.swing.JButton purchaseTicketButton;
    private javax.swing.JComboBox<String> showTimeComboBox;
    private javax.swing.JLabel ticketPriceLabel;
    private javax.swing.JTextField ticketQuantityField;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables
}
