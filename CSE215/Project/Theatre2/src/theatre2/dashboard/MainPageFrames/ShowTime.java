/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package theatre2.dashboard.MainPageFrames;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;


public class ShowTime extends javax.swing.JInternalFrame {

    private String showFilePath = "src/theatre2/data/shows.txt";

    public ShowTime() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadMovieNamesToCombo();
        movieNameComboBox.setSelectedIndex(0);         // make sure "All Movies" is selected
        loadShowsToTable("All Movies");
    }

    public ShowTime(String presetMovie) {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        // load data as usual
        loadMovieNamesToCombo();

        // if the title isn't already in the combo (rare), add it
        boolean found = false;
        for (int i = 0; i < movieNameComboBox.getItemCount(); i++) {
            if (presetMovie.equalsIgnoreCase(movieNameComboBox.getItemAt(i))) {
                found = true;
                break;
            }
        }
        if (!found) {
            movieNameComboBox.addItem(presetMovie);
        }

        // select and filter table
        movieNameComboBox.setSelectedItem(presetMovie);
        loadShowsToTable(presetMovie);
    }

    private void loadMovieNamesToCombo() {
        movieNameComboBox.removeAllItems();
        movieNameComboBox.addItem("All Movies");

        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(showFilePath));
            String line;
            java.util.ArrayList<String> seen = new java.util.ArrayList<String>(); // keep order, avoid duplicates

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] p = line.split("\\|");  // movie|screen|date|start|end
                if (p.length >= 1) {
                    String movie = p[0].trim();
                    if (!movie.isEmpty() && !seen.contains(movie)) {
                        movieNameComboBox.addItem(movie);
                        seen.add(movie);
                    }
                }
            }
            br.close();
        } catch (java.io.FileNotFoundException e) {
            // file may not exist yet; ignore
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading movie list: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ignore) {
            }
        }
    }

    private void loadShowsToTable(String filterMovie) {
        javax.swing.table.DefaultTableModel model
                = (javax.swing.table.DefaultTableModel) showTable.getModel();
        model.setRowCount(0);

        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(showFilePath));
            String line;
            int no = 1;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] p = line.split("\\|"); // movie|screen|date|start|end
                if (p.length >= 5) {
                    String movie = p[0].trim();

                    boolean accept = (filterMovie == null || "All Movies".equalsIgnoreCase(filterMovie)  || movie.equalsIgnoreCase(filterMovie));
                           
                          

                    if (accept) {
                        model.addRow(new Object[]{no++, movie, p[1], p[2], p[3], p[4]});
                    }
                }
            }
            br.close();
        } catch (java.io.FileNotFoundException e) {
            // fine if it doesn't exist yet
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading shows: " + e.getMessage());
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        showTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        movieNameComboBox = new javax.swing.JComboBox<>();

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Showtime Database");

        movieNameComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        movieNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieNameComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(movieNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(movieNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void movieNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieNameComboBoxActionPerformed
        // TODO add your handling code here:
        Object sel = movieNameComboBox.getSelectedItem();
        if (sel == null) {
            return;
        }
        String choice = sel.toString().trim();
        loadShowsToTable(choice);
    }//GEN-LAST:event_movieNameComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> movieNameComboBox;
    private javax.swing.JTable showTable;
    // End of variables declaration//GEN-END:variables
}
