package theatre2.dashboard.AdminDashboardFrames;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Show extends javax.swing.JInternalFrame {

    private String movieFilePath = "src/theatre2/data/movies.txt";
    private String screenFilePath = "src/theatre2/data/screens.txt";
    private String showFilePath = "src/theatre2/data/shows.txt";
    

    private java.util.List<Integer> movieDurSeconds = new java.util.ArrayList<>();

    public Show() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        loadMoviesAndScreens();
        loadShowsToTable();

        // Recompute end time when inputs change (simple)
        hourComboBox.addActionListener(e -> computeEndTime());
        minuteComboBox.addActionListener(e -> computeEndTime());
        ampmComboBox.addActionListener(e -> computeEndTime());
        intervalField.addActionListener(e -> computeEndTime());
        movieNameComboBox.addActionListener(e -> computeEndTime());

    }

    private void computeEndTime() {
    try {
        int idx = movieNameComboBox.getSelectedIndex();
        if (idx < 0 || idx >= movieDurSeconds.size()) return;

        String hStr = (String) hourComboBox.getSelectedItem();
        String mStr = (String) minuteComboBox.getSelectedItem();
        String ampm = (String) ampmComboBox.getSelectedItem();
        if (hStr == null || mStr == null || ampm == null) return;

        int h12 = Integer.parseInt(hStr);     // 1..12
        int m = Integer.parseInt(mStr);       // 1..60 in your combo
        if (m == 60) m = 0;                   // normalize

        int h24 = h12 % 12;
        if ("PM".equalsIgnoreCase(ampm)) h24 += 12;

        int startTotalSec = (h24 * 60 + m) * 60;

        int durSec = movieDurSeconds.get(idx);

        int intervalMin = 0;
        String intervalText = intervalField.getText().trim();
        if (!intervalText.isEmpty()) intervalMin = Integer.parseInt(intervalText);
        int intervalSec = Math.max(0, intervalMin) * 60;

        int endTotalSec = (startTotalSec + durSec + intervalSec) % (24 * 3600);

        int endH24 = endTotalSec / 3600;
        int endM   = (endTotalSec % 3600) / 60;

        String endAmPm = (endH24 < 12) ? "AM" : "PM";
        int endH12 = endH24 % 12;
        if (endH12 == 0) endH12 = 12;

        hourLabel.setText(String.valueOf(endH12));
        minuteLabel.setText(pad2(endM));
        ampmLabel.setText(endAmPm);
    } catch (Exception ex) {
        hourLabel.setText("");
        minuteLabel.setText("");
        ampmLabel.setText("");
    }
}


    private String pad2(int x) {
        if (x < 10) {
            return "0" + x;
        } else {
            return String.valueOf(x);
        }
    }

    private int parseDurationToSeconds(String hms) {
        try {
            String[] t = hms.split(":");
            int h = 0, m = 0, s = 0;

            if (t.length > 0) {
                String hs = t[0].trim();
                if (!hs.isEmpty()) {
                    h = Integer.parseInt(hs);
                } else {
                    h = 0;
                }
            } else {
                h = 0;
            }

            if (t.length > 1) {
                String ms = t[1].trim();
                if (!ms.isEmpty()) {
                    m = Integer.parseInt(ms);
                } else {
                    m = 0;
                }
            } else {
                m = 0;
            }

            if (t.length > 2) {
                String ss = t[2].trim();
                if (!ss.isEmpty()) {
                    s = Integer.parseInt(ss);
                } else {
                    s = 0;
                }
            } else {
                s = 0;
            }

            // normalize
            if (m >= 60) {
                h += m / 60;
                m = m % 60;
            }
            if (s >= 60) {
                m += s / 60;
                s = s % 60;
            }

            return h * 3600 + m * 60 + s;
        } catch (Exception ex) {
            return 0; // fallback if malformed
        }
    }

    private void loadMoviesAndScreens() {
        // Movies from movies.txt (format: name|image|trailer|... )
        movieNameComboBox.removeAllItems();
        movieDurSeconds.clear();
        try (FileReader fr = new FileReader(movieFilePath); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length >= 10) {
                    String name = p[0].trim();
                    String durationStr = p[9].trim(); // e.g., "1:45:00"
                    movieNameComboBox.addItem(name);
                    movieDurSeconds.add(parseDurationToSeconds(durationStr));
                }
            }
        } catch (java.io.FileNotFoundException e) {
            // file may not exist yet
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading movies: " + e.getMessage());
        }

        // Screens from screens.txt (format: name|capacity|status|cols|seatType)
        screenComboBox.removeAllItems();
        try {
            FileReader fr = new FileReader(screenFilePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length >= 1) {
                    screenComboBox.addItem(p[0]);
                }
            }
            br.close();
            fr.close();
        } catch (java.io.FileNotFoundException e) {
            // first run: file might not exist; fine
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading screens: " + e.getMessage());
        }
    }

    private void loadShowsToTable() {
        DefaultTableModel model = (DefaultTableModel) showTable.getModel();
        model.setRowCount(0);

        try {
            FileReader fr = new FileReader(showFilePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int no = 1;
            while ((line = br.readLine()) != null) {
                // We'll save as: movie|screen|date|start|end
                String[] p = line.split("\\|");
                if (p.length >= 5) {
                    model.addRow(new Object[]{no++, p[0], p[1], p[2], p[3], p[4]});
                }
            }
            br.close();
            fr.close();
        } catch (java.io.FileNotFoundException e) {
            // fine if it doesn't exist yet
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading shows: " + e.getMessage());
        }
    }

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
        intervalField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        discardButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        movieNameComboBox = new javax.swing.JComboBox<>();
        screenComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        dayField = new javax.swing.JComboBox<>();
        monthField = new javax.swing.JComboBox<>();
        yearField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        ampmComboBox = new javax.swing.JComboBox<>();
        hourLabel = new javax.swing.JLabel();
        minuteLabel = new javax.swing.JLabel();
        ampmLabel = new javax.swing.JLabel();
        hourComboBox = new javax.swing.JComboBox<>();
        minuteComboBox = new javax.swing.JComboBox<>();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        showTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Movie Name", "Screen Name", "Date", "Start Time", "End Time"
            }
        ));
        jScrollPane2.setViewportView(showTable);

        deleteTicketButton.setBackground(new java.awt.Color(204, 0, 51));
        deleteTicketButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteTicketButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteTicketButton.setText("Delete Movie");
        deleteTicketButton.setMaximumSize(new java.awt.Dimension(190, 45));
        deleteTicketButton.setMinimumSize(new java.awt.Dimension(190, 45));
        deleteTicketButton.setPreferredSize(new java.awt.Dimension(190, 45));
        deleteTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTicketButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Show Database");

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
        jLabel1.setText("Screen");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Movie name");

        posterLink.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Interval (In Minutes)");

        intervalField.setMinimumSize(new java.awt.Dimension(600, 30));
        intervalField.setPreferredSize(new java.awt.Dimension(600, 30));
        intervalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalFieldActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("End Time (H/M/S)");

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

        saveButton.setBackground(new java.awt.Color(0, 153, 153));
        saveButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.setMaximumSize(new java.awt.Dimension(190, 45));
        saveButton.setMinimumSize(new java.awt.Dimension(190, 45));
        saveButton.setPreferredSize(new java.awt.Dimension(190, 45));
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                saveButtonMousePressed(evt);
            }
        });
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        movieNameComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        movieNameComboBox.setMinimumSize(new java.awt.Dimension(72, 30));
        movieNameComboBox.setPreferredSize(new java.awt.Dimension(600, 30));

        screenComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        screenComboBox.setMinimumSize(new java.awt.Dimension(72, 30));
        screenComboBox.setPreferredSize(new java.awt.Dimension(600, 30));

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

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Start Time");

        ampmComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        hourLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        minuteLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ampmLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        hourComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        hourComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourComboBoxActionPerformed(evt);
            }
        });

        minuteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" }));
        minuteComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minuteComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(hourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minuteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel11)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(movieNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(screenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(posterLink, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ampmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(intervalField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(hourComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 82, Short.MAX_VALUE)
                                    .addComponent(dayField, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(monthField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(minuteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(yearField)
                                    .addComponent(ampmComboBox, 0, 96, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(discardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movieNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(posterLink, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(screenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minuteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intervalField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minuteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 25, Short.MAX_VALUE))
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

    private void dayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayFieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        String movie = (String) movieNameComboBox.getSelectedItem();
        String screen = (String) screenComboBox.getSelectedItem();
        String day = (String) dayField.getSelectedItem();
        String month = (String) monthField.getSelectedItem();
        String year = yearField.getText().trim();
        String startH = (String) hourComboBox.getSelectedItem();
        String startM = (String) minuteComboBox.getSelectedItem();
        String startAP = (String) ampmComboBox.getSelectedItem();

        if (movie == null || screen == null || year.isEmpty() || startH == null || startM == null || startAP == null) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }
        if (intervalField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter interval (minutes).");
            return;
        }
        // Compute end time to ensure labels are updated
        computeEndTime();
        if (hourLabel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid time/interval.");
            return;
        }

        String date = day + " " + month + " " + year;
        // Normalize minute "60" to "00" in start time (to be consistent with computeEndTime)
        int mm = Integer.parseInt(startM);
        if (mm == 60) {
            mm = 0;
        }
        String startTime = Integer.parseInt(startH) + ":" + pad2(mm) + " " + startAP;
        String endTime = hourLabel.getText() + ":" + minuteLabel.getText() + " " + ampmLabel.getText();

        String line = movie + "|" + screen + "|" + date + "|" + startTime + "|" + endTime;

        try {
            FileWriter fw = new FileWriter(showFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(line);
            bw.newLine();
            bw.close();
            fw.close();

            JOptionPane.showMessageDialog(this, "Show saved.");
            loadShowsToTable();
            discardButtonActionPerformed(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving show: " + e.getMessage());
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void saveButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonMousePressed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        // TODO add your handling code here:
        movieNameComboBox.setSelectedIndex(movieNameComboBox.getItemCount() > 0 ? 0 : -1);
        screenComboBox.setSelectedIndex(screenComboBox.getItemCount() > 0 ? 0 : -1);
        dayField.setSelectedIndex(0);
        monthField.setSelectedIndex(0);
        yearField.setText("2025");
        hourComboBox.setSelectedIndex(0);
        minuteComboBox.setSelectedIndex(0);
        ampmComboBox.setSelectedIndex(0);
        intervalField.setText("");
        hourLabel.setText("");
        minuteLabel.setText("");
        ampmLabel.setText("");
    }//GEN-LAST:event_discardButtonActionPerformed

    private void intervalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_intervalFieldActionPerformed

    private void hourComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourComboBoxActionPerformed

    private void minuteComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minuteComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minuteComboBoxActionPerformed

    private void deleteTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTicketButtonActionPerformed
        // TODO add your handling code here:
        int row = showTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) showTable.getModel();
        String movie = (String) model.getValueAt(row, 1);
        String screen = (String) model.getValueAt(row, 2);
        String date = (String) model.getValueAt(row, 3);
        String start = (String) model.getValueAt(row, 4);
        String end = (String) model.getValueAt(row, 5);

        String targetLine = movie + "|" + screen + "|" + date + "|" + start + "|" + end;

        // Read file -> keep non-matching lines -> overwrite file
        List<String> lines = new ArrayList<>();
        try {
            FileReader fr = new FileReader(showFilePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(targetLine)) {
                    lines.add(line);
                }
            }
            br.close();
            fr.close();

            FileWriter fw = new FileWriter(showFilePath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();
            fw.close();

            model.removeRow(row);
            // Fix numbering
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0);
            }

            JOptionPane.showMessageDialog(this, "Show deleted.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting show: " + e.getMessage());
        }
    }//GEN-LAST:event_deleteTicketButtonActionPerformed

    private void yearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ampmComboBox;
    private javax.swing.JLabel ampmLabel;
    private javax.swing.JComboBox<String> dayField;
    private javax.swing.JButton deleteTicketButton;
    private javax.swing.JButton discardButton;
    private javax.swing.JComboBox<String> hourComboBox;
    private javax.swing.JLabel hourLabel;
    private javax.swing.JTextField intervalField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> minuteComboBox;
    private javax.swing.JLabel minuteLabel;
    private javax.swing.JComboBox<String> monthField;
    private javax.swing.JComboBox<String> movieNameComboBox;
    private javax.swing.JLabel posterLink;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> screenComboBox;
    private javax.swing.JTable showTable;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables
}
