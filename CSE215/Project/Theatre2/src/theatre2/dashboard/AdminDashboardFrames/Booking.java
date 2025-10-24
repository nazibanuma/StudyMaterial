package theatre2.dashboard.AdminDashboardFrames;

import theatre2.LoginSignup.PaymentFrame;

import javax.swing.plaf.basic.BasicInternalFrameUI;


public class Booking extends javax.swing.JInternalFrame {

    private String ticketPriceFilePath = "src/theatre2/data/tickets.txt";
    private String bookingFilePath = "src/theatre2/data/bookings.txt";

    public Booking() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        // defaults
        if (yearField.getText().trim().isEmpty()) {
            yearField.setText("2025");
        }

        // load sources
        loadMoviesFromTickets();
        if (movieNameComboBox.getItemCount() > 0) {
            movieNameComboBox.setSelectedIndex(0);
        }

        // first fill of show times
        updateShowTimes();

        // refresh show list when movie/date changes
        movieNameComboBox.addActionListener(e -> updateShowTimes());
        dayField.addActionListener(e -> updateShowTimes());
        monthField.addActionListener(e -> updateShowTimes());
        yearField.addActionListener(e -> updateShowTimes());

        // recompute price when show or qty changes
        showTimeComboBox.addActionListener(e -> updateUnitPriceAndTotal());
        ticketQuantityField.addActionListener(e -> updateUnitPriceAndTotal());

        // load existing bookings to table
        loadBookingsToTable();
    }

    private void loadMoviesFromTickets() {
        movieNameComboBox.removeAllItems();
        movieNameComboBox.addItem("Select movie name"); // placeholder
        java.util.ArrayList<String> seen = new java.util.ArrayList<>();
        try {
            java.io.FileReader fr = new java.io.FileReader(ticketPriceFilePath);
            java.io.BufferedReader br = new java.io.BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|"); // movie|screen|date|start|end|price
                if (p.length >= 6) {
                    String movie = p[0].trim();
                    if (!seen.contains(movie)) {
                        seen.add(movie);
                        movieNameComboBox.addItem(movie);
                    }
                }
            }
            br.close();
            fr.close();
        } catch (java.io.FileNotFoundException e) {
            // ignore
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading movies: " + e.getMessage());
        }
    }

    private void updateShowTimes() {
        showTimeComboBox.removeAllItems();
        ticketPriceLabel.setText("");
        calculatedPriceLabel.setText("");

        String movie = (String) movieNameComboBox.getSelectedItem();
        if (movie == null) {
            return;
        }

        String day = (String) dayField.getSelectedItem();
        String month = (String) monthField.getSelectedItem();
        String year = yearField.getText().trim();

        String date = null;
        if (day != null && month != null && !year.isEmpty()) {
            date = day + " " + month + " " + year;  // must match tickets.txt
        }

        try {
            java.io.FileReader fr = new java.io.FileReader(ticketPriceFilePath);
            java.io.BufferedReader br = new java.io.BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|"); // movie|screen|date|start|end|price
                if (p.length >= 6) {
                    String m = p[0].trim();
                    String screen = p[1].trim();
                    String d = p[2].trim();
                    String start = p[3].trim();
                    String end = p[4].trim();
                    if (movie.equals(m) && (date == null || date.equals(d))) {
                        showTimeComboBox.addItem(screen + " | " + start + " - " + end);
                    }
                }
            }
            br.close();
            fr.close();
        } catch (java.io.FileNotFoundException e) {
            // ok if file not there yet
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading show times: " + e.getMessage());
        }
    }

    private void updateUnitPriceAndTotal() {
        ticketPriceLabel.setText("");
        calculatedPriceLabel.setText("");

        String movie = (String) movieNameComboBox.getSelectedItem();
        String selShow = (String) showTimeComboBox.getSelectedItem();
        String day = (String) dayField.getSelectedItem();
        String month = (String) monthField.getSelectedItem();
        String year = yearField.getText().trim();
        if (movie == null || selShow == null || day == null || month == null || year.isEmpty()) {
            return;
        }

        String date = day + " " + month + " " + year;

        String screen = "", start = "", end = "";
        try {
            String[] a = selShow.split("\\|");
            if (a.length >= 2) {
                screen = a[0].trim();
                String[] se = a[1].trim().split("-");
                if (se.length >= 2) {
                    start = se[0].trim();
                    end = se[1].trim();
                }
            }
        } catch (Exception ignore) {
        }
        if (screen.isEmpty() || start.isEmpty() || end.isEmpty()) {
            return;
        }

        String unitStr = "";
        try {
            java.io.FileReader fr = new java.io.FileReader(ticketPriceFilePath);
            java.io.BufferedReader br = new java.io.BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|"); // movie|screen|date|start|end|price
                if (p.length >= 6) {
                    if (movie.equals(p[0].trim())
                            && screen.equals(p[1].trim())
                            && date.equals(p[2].trim())
                            && start.equals(p[3].trim())
                            && end.equals(p[4].trim())) {
                        unitStr = p[5].trim();
                        break;
                    }
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error reading price: " + e.getMessage());
            return;
        }

        if (!unitStr.isEmpty()) {
            ticketPriceLabel.setText(unitStr);
            String qtyStr = ticketQuantityField.getText().trim();
            if (!qtyStr.isEmpty()) {
                try {
                    int qty = Integer.parseInt(qtyStr);
                    if (qty < 0) {
                        qty = 0;
                    }
                    int total = Integer.parseInt(unitStr) * qty;
                    calculatedPriceLabel.setText(String.valueOf(total));
                } catch (Exception ignore) {
                }
            }
        }
    }

    private void loadBookingsToTable() {
        javax.swing.table.DefaultTableModel model
                = (javax.swing.table.DefaultTableModel) showTable.getModel();
        model.setRowCount(0);
        try {
            java.io.FileReader fr = new java.io.FileReader(bookingFilePath);
            java.io.BufferedReader br = new java.io.BufferedReader(fr);
            String line;
            int no = 1;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|"); // name|phone|movie|date|show|qty|total
                if (p.length >= 7) {
                    model.addRow(new Object[]{
                        no++, p[0], p[1], p[2], p[3], p[4], p[5], p[6]
                    });
                }
            }
            br.close();
            fr.close();
        } catch (java.io.FileNotFoundException e) {
            // ok if file not there yet
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading bookings: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        showTable = new javax.swing.JTable();
        deleteTicketButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        posterLink = new javax.swing.JLabel();
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
        minuteLabel = new javax.swing.JLabel();
        ampmLabel = new javax.swing.JLabel();
        customerNameField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        phoneNumberField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ticketPriceLabel = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        showTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Customer Name", "Phone Number", "Movie Name", "Date", "Show Time", "Ticket Quantity", "Calculated Price"
            }
        ));
        jScrollPane2.setViewportView(showTable);

        deleteTicketButton.setBackground(new java.awt.Color(204, 0, 51));
        deleteTicketButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteTicketButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteTicketButton.setText("Delete Ticket");
        deleteTicketButton.setMaximumSize(new java.awt.Dimension(190, 45));
        deleteTicketButton.setMinimumSize(new java.awt.Dimension(190, 45));
        deleteTicketButton.setPreferredSize(new java.awt.Dimension(190, 45));
        deleteTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTicketButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ticket Database");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(deleteTicketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteTicketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1300, 900));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Show Time");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Customer Name");

        posterLink.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        minuteLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ampmLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Phone Number");

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
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ticketPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(141, 141, 141)))
                        .addComponent(minuteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ampmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(197, 197, 197))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(phoneNumberField)
                                .addGap(15, 15, 15)))
                        .addComponent(posterLink, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(purchaseTicketButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(monthField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(yearField))
                        .addComponent(customerNameField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(showTimeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(ticketQuantityField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(movieNameComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(discardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calculatedPriceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(671, 671, 671))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(posterLink, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movieNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showTimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ampmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minuteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ticketPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ticketQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calculatedPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(purchaseTicketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTicketButtonActionPerformed
        // TODO add your handling code here:
        int row = showTable.getSelectedRow();
        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        javax.swing.table.DefaultTableModel model
                = (javax.swing.table.DefaultTableModel) showTable.getModel();

        String cust = String.valueOf(model.getValueAt(row, 1));
        String phone = String.valueOf(model.getValueAt(row, 2));
        String movie = String.valueOf(model.getValueAt(row, 3));
        String date = String.valueOf(model.getValueAt(row, 4));
        String show = String.valueOf(model.getValueAt(row, 5));
        String qty = String.valueOf(model.getValueAt(row, 6));
        String total = String.valueOf(model.getValueAt(row, 7));

        String target = cust + "|" + phone + "|" + movie + "|" + date + "|" + show + "|" + qty + "|" + total;

        java.util.List<String> keep = new java.util.ArrayList<>();
        try {
            java.io.FileReader fr = new java.io.FileReader(bookingFilePath);
            java.io.BufferedReader br = new java.io.BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(target)) {
                    keep.add(line);
                }
            }
            br.close();
            fr.close();

            java.io.FileWriter fw = new java.io.FileWriter(bookingFilePath, false);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
            for (String s : keep) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            fw.close();

            model.removeRow(row);
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0);
            }

            javax.swing.JOptionPane.showMessageDialog(this, "Booking deleted.");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error deleting booking: " + e.getMessage());
        }
    }//GEN-LAST:event_deleteTicketButtonActionPerformed

    private void ticketQuantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticketQuantityFieldActionPerformed
        // TODO add your handling code here:
        updateUnitPriceAndTotal();
    }//GEN-LAST:event_ticketQuantityFieldActionPerformed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        // TODO add your handling code here:
        customerNameField.setText("");
        phoneNumberField.setText("");
        if (movieNameComboBox.getItemCount() > 0) {
            movieNameComboBox.setSelectedIndex(0);
        }
        dayField.setSelectedIndex(0);
        monthField.setSelectedIndex(0);
        yearField.setText("2025");
        showTimeComboBox.removeAllItems();
        ticketQuantityField.setText("");
        ticketPriceLabel.setText("");
        calculatedPriceLabel.setText("");
    }//GEN-LAST:event_discardButtonActionPerformed

    private void purchaseTicketButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseTicketButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseTicketButtonMousePressed

    private void purchaseTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseTicketButtonActionPerformed
        // TODO add your handling code here:
        // 1) Validate form (same as you already do)
        String cust = customerNameField.getText().trim();
        String phone = phoneNumberField.getText().trim();
        String movie = (String) movieNameComboBox.getSelectedItem();
        String day = (String) dayField.getSelectedItem();
        String month = (String) monthField.getSelectedItem();
        String year = yearField.getText().trim();
        String showSel = (String) showTimeComboBox.getSelectedItem();
        String qtyStr = ticketQuantityField.getText().trim();

        if (cust.isEmpty() || phone.isEmpty() || movie == null || showSel == null
                || day == null || month == null || year.isEmpty() || qtyStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(qtyStr);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Quantity must be a number.");
            return;
        }
        if (qty <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
            return;
        }

        // 2) Make sure price labels are filled
        updateUnitPriceAndTotal();
        String unitStr = ticketPriceLabel.getText().trim();
        String totalStr = calculatedPriceLabel.getText().trim();
        if (unitStr.isEmpty() || totalStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Price not available for selected show.");
            return;
        }

        final String date = day + " " + month + " " + year;
        final String line = cust + "|" + phone + "|" + movie + "|" + date + "|" + showSel + "|" + qty + "|" + totalStr + "|ADMIN";

        // 3) Open payment window, pass a callback for success
        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        PaymentFrame.PaymentListener onPaid = () -> {
            // This runs AFTER payment succeeds
            try (java.io.FileWriter fw = new java.io.FileWriter(bookingFilePath, true); java.io.BufferedWriter bw = new java.io.BufferedWriter(fw)) {
                bw.write(line);
                bw.newLine();
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error saving booking: " + e.getMessage());
                return;
            }

            // Reload table & clear form
            loadBookingsToTable();
            discardButtonActionPerformed(null);

            // Show confirmation from the parent window
            javax.swing.JOptionPane.showMessageDialog(this, "Payment confirmed");
        };

        PaymentFrame paymentFrame = new PaymentFrame(parent, onPaid);
        paymentFrame.setLocationRelativeTo(parent);
        paymentFrame.setVisible(true);
    }//GEN-LAST:event_purchaseTicketButtonActionPerformed

    private void dayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayFieldActionPerformed
        // TODO add your handling code here:
        updateShowTimes();
    }//GEN-LAST:event_dayFieldActionPerformed

    private void yearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearFieldActionPerformed
        // TODO add your handling code here:
        updateShowTimes();
    }//GEN-LAST:event_yearFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ampmLabel;
    private javax.swing.JLabel calculatedPriceLabel;
    private javax.swing.JTextField customerNameField;
    private javax.swing.JComboBox<String> dayField;
    private javax.swing.JButton deleteTicketButton;
    private javax.swing.JButton discardButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel minuteLabel;
    private javax.swing.JComboBox<String> monthField;
    private javax.swing.JComboBox<String> movieNameComboBox;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel posterLink;
    private javax.swing.JButton purchaseTicketButton;
    private javax.swing.JTable showTable;
    private javax.swing.JComboBox<String> showTimeComboBox;
    private javax.swing.JLabel ticketPriceLabel;
    private javax.swing.JTextField ticketQuantityField;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables
}
