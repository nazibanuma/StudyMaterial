package theatre2.dashboard.AdminDashboardFrames;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Home extends javax.swing.JInternalFrame {

    // ---- simple file paths ----
    private final String MOVIES_FILE = "src/theatre2/data/movies.txt";
    private final String SCREENS_FILE = "src/theatre2/data/screens.txt";
    private final String SHOWS_FILE = "src/theatre2/data/shows.txt";
    private final String BOOKINGS_FILE = "src/theatre2/data/bookings.txt";

    public Home() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        refreshDashboard(); //load stats + table

    }

    private void refreshDashboard() {
        TotalScreenLabel.setText(String.valueOf(countScreens()));
        movieShowingLabel.setText(String.valueOf(countMoviesShowing()));
        showsRunningTodaylabel.setText(String.valueOf(countShowsToday()));

        loadBookingsAndFillTopMoviesTable();
    }

    private int countScreens() {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(SCREENS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] p = line.split("\\|"); // name|capacity|status|cols|seatType
                if (p.length >= 1 && !p[0].trim().isEmpty()) {
                    count++;
                }
            }
        } catch (Exception ignored) {
        }
        return count;
    }

    private int countMoviesShowing() {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(MOVIES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|"); // ...|duration|status|synopsis
                if (p.length >= 11) {
                    String status = p[10].trim();
                    if ("Showing".equalsIgnoreCase(status)) {
                        count++;
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return count;
    }

    private int countShowsToday() {
        // "d MMMM yyyy" e.g., "1 January 2025"
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("d MMMM yyyy", java.util.Locale.ENGLISH);
        String today = sdf.format(new java.util.Date());
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(SHOWS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|"); // movie|screen|date|start|end
                if (p.length >= 3) {
                    String date = p[2].trim();
                    if (today.equals(date)) {
                        count++;
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return count;
    }

    private void loadBookingsAndFillTopMoviesTable() {
        // Aggregate per movie using parallel lists (no HashMap)
        ArrayList<String> movieNames = new ArrayList<>();
        ArrayList<Integer> totalTickets = new ArrayList<>();
        ArrayList<Double> totalRevenue = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // booking: customer|phone|movie|date|showTime|qty|calculatedPrice
                String[] p = line.split("\\|");
                if (p.length < 7) {
                    continue;
                }

                String movie = p[2].trim();
                int qty = parseIntSafe(p[6].trim());
                double price = parseMoneyToDouble(p[7].trim()); // full price of that booking line

                int idx = indexOfIgnoreCase(movieNames, movie);
                if (idx == -1) {
                    movieNames.add(movie);
                    totalTickets.add(qty);
                    totalRevenue.add(price);
                } else {
                    totalTickets.set(idx, totalTickets.get(idx) + qty);
                    totalRevenue.set(idx, totalRevenue.get(idx) + price);
                }
            }
        } catch (FileNotFoundException e) {
            // no bookings yet; just clear table
        } catch (Exception e) {
            // ignore bad lines
        }

        // Sort by tickets (desc) using selection sort; keep names & revenue aligned
        int n = movieNames.size();
        String[] names = new String[n];
        int[] tks = new int[n];
        double[] revs = new double[n];
        for (int i = 0; i < n; i++) {
            names[i] = movieNames.get(i);
            tks[i] = totalTickets.get(i);
            revs[i] = totalRevenue.get(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int best = i;
            for (int j = i + 1; j < n; j++) {
                if (tks[j] > tks[best]) {
                    best = j;
                }
            }
            if (best != i) {
                // swap tickets
                int tmpT = tks[i];
                tks[i] = tks[best];
                tks[best] = tmpT;
                // swap revenue
                double tmpR = revs[i];
                revs[i] = revs[best];
                revs[best] = tmpR;
                // swap names
                String tmpN = names[i];
                names[i] = names[best];
                names[best] = tmpN;
            }
        }

        // Fill table (top 10)
        DefaultTableModel model = (DefaultTableModel) topMoviesByTicketTable.getModel();
        model.setRowCount(0);
        int limit = Math.min(10, n);
        for (int i = 0; i < limit; i++) {
            model.addRow(new Object[]{
                i + 1,
                names[i],
                tks[i],
                "৳ " + formatMoneyBasic(revs[i])
            });
        }

        // Also update total tickets label from sum of all tickets
        int grandTickets = 0;
        for (int i = 0; i < n; i++) {
            grandTickets += tks[i];
        }
        totalTicketsSoldLabel.setText(String.valueOf(grandTickets));
    }

    private int indexOfIgnoreCase(ArrayList<String> list, String s) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase(s)) {
                return i;
            }
        }
        return -1;
    }

    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private double parseMoneyToDouble(String s) {
        // supports formats like "60000/=", "৳ 500", "500.00"
        String cleaned = s.replaceAll("[^0-9.]", "");
        if (cleaned.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(cleaned);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private String formatMoneyBasic(double val) {
        long asLong = (long) val;
        if (Math.abs(val - asLong) < 0.005) {
            return String.valueOf(asLong);
        }
        return String.format(java.util.Locale.ENGLISH, "%.2f", val);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        TotalScreenLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        movieShowingLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        showsRunningTodaylabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        totalTicketsSoldLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        topMoviesByTicketTable = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        TotalScreenLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        TotalScreenLabel.setForeground(new java.awt.Color(255, 255, 255));
        TotalScreenLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalScreenLabel.setText("5");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Screens");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TotalScreenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(TotalScreenLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(47, 47, 47))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        movieShowingLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        movieShowingLabel.setForeground(new java.awt.Color(255, 255, 255));
        movieShowingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movieShowingLabel.setText("10");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Movies Showing");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movieShowingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(movieShowingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(47, 47, 47))
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        showsRunningTodaylabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        showsRunningTodaylabel.setForeground(new java.awt.Color(255, 255, 255));
        showsRunningTodaylabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showsRunningTodaylabel.setText("10");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Shows Running Today");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showsRunningTodaylabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(showsRunningTodaylabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(47, 47, 47))
        );

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));

        totalTicketsSoldLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        totalTicketsSoldLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalTicketsSoldLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalTicketsSoldLabel.setText("600");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tickets Sold");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totalTicketsSoldLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(totalTicketsSoldLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(47, 47, 47))
        );

        topMoviesByTicketTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Movie name", "Total Tickets", "Total Revenue"
            }
        ));
        jScrollPane2.setViewportView(topMoviesByTicketTable);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Top Movies by Tickets");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalScreenLabel;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel movieShowingLabel;
    private javax.swing.JLabel showsRunningTodaylabel;
    private javax.swing.JTable topMoviesByTicketTable;
    private javax.swing.JLabel totalTicketsSoldLabel;
    // End of variables declaration//GEN-END:variables
}
