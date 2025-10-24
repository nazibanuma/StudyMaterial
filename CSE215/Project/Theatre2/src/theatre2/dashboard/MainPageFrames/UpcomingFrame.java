/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package theatre2.dashboard.MainPageFrames;

import javax.swing.plaf.basic.BasicInternalFrameUI;


public class UpcomingFrame extends javax.swing.JInternalFrame {

    // --- config you can tweak ---
    private final String moviesFile = "src/theatre2/data/movies.txt";
    private final int COLS = 4;      // posters per row
    private final int HGAP = 20;     // grid horizontal gap
    private final int VGAP = 20;     // grid vertical gap
    // -----------------------------

    public UpcomingFrame() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        // make sure grid has the layout you want
        nowShowingGridPanel.setLayout(new java.awt.GridLayout(0, COLS, HGAP, VGAP));

        // no horizontal bar; posters will scale instead
        NowShowingScrollPane.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // resize posters whenever the viewport changes size
        NowShowingScrollPane.getViewport().addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                resizePostersToFit();
            }
        });

        populateMoviesGrid();   // load from file and show
    }

    private void populateMoviesGrid() {
        nowShowingGridPanel.removeAll();

        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(moviesFile));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Expected full format:
                // name|poster|trailer|actor|director|writer|genre|releaseDate|language|duration|status|synopsis
                // But we also handle shorter lines (status then assumed to be last column).
                String[] p = line.split("\\|");
                if (p.length < 2) {
                    continue; // need at least title + poster
                }
                int statusIdx;
                if (p.length >= 12) {
                    statusIdx = 10;                 // "status" at fixed position in full format
                } else if (p.length >= 3) {
                    statusIdx = p.length - 1;       // otherwise assume last column is status
                } else {
                    continue;
                }

                String status = p[statusIdx].trim();
                if (!"Upcoming".equalsIgnoreCase(status)) {
                    continue;
                }

                final String title = p[0].trim();
                final String posterPath = p[1].trim();

                // Build a simple card (image + title)
                javax.swing.JPanel card = new javax.swing.JPanel(new java.awt.BorderLayout());
                card.setOpaque(false);

                javax.swing.JLabel imgLabel = new javax.swing.JLabel("", javax.swing.SwingConstants.CENTER);
                imgLabel.setVerticalAlignment(javax.swing.SwingConstants.CENTER);

                // Load original image ONCE and store it for scaling
                java.awt.Image master = loadImage(posterPath);
                card.putClientProperty("masterImage", master);
                card.putClientProperty("imgLabel", imgLabel);

                javax.swing.JLabel titleLabel = new javax.swing.JLabel(title, javax.swing.SwingConstants.CENTER);
                titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));

                card.add(imgLabel, java.awt.BorderLayout.CENTER);
                card.add(titleLabel, java.awt.BorderLayout.SOUTH);

                // Optional click -> simple message (you can swap to a details dialog later)
                final String[] fields = p.clone(); // keep the full movie record for the dialog

                java.awt.event.MouseAdapter click = new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        showMovieDetails(fields);
                    }
                };

                card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                card.addMouseListener(click);
                imgLabel.addMouseListener(click);
                titleLabel.addMouseListener(click);

                nowShowingGridPanel.add(card);
            }
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

        // Scale posters to current viewport width
        resizePostersToFit();

        nowShowingGridPanel.revalidate();
        nowShowingGridPanel.repaint();
    }

    private void showMovieDetails(final String[] p) {
        // 0 name | 1 poster | 2 trailer | 3 actor | 4 director | 5 writer
        // 6 genre | 7 releaseDate | 8 language | 9 duration | 10 status | 11 synopsis

        java.awt.Window owner = javax.swing.SwingUtilities.getWindowAncestor(this);
        final javax.swing.JDialog d = new javax.swing.JDialog(
                owner, "Movie Details", java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new java.awt.BorderLayout(10, 10));

        // LEFT: poster
        javax.swing.JLabel poster = new javax.swing.JLabel();
        java.awt.Image master = loadImage(safe(p, 1));
        int pw = 220;
        int mw = (master != null) ? master.getWidth(null) : 0;
        int mh = (master != null) ? master.getHeight(null) : 0;
        int ph = (mw > 0) ? (int) (pw * (mh / (double) mw)) : 320;
        if (master != null) {
            poster.setIcon(new javax.swing.ImageIcon(
                    master.getScaledInstance(pw, ph, java.awt.Image.SCALE_SMOOTH)));
        }
        poster.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        d.add(poster, java.awt.BorderLayout.WEST);

        // CENTER: info + synopsis
        javax.swing.JPanel info = new javax.swing.JPanel(new java.awt.GridLayout(0, 1, 4, 4));
        info.add(new javax.swing.JLabel("Title: " + safe(p, 0)));
        info.add(new javax.swing.JLabel("Genre: " + safe(p, 6)));
        info.add(new javax.swing.JLabel("Duration: " + safe(p, 9)));
        info.add(new javax.swing.JLabel("Language: " + safe(p, 8)));
        info.add(new javax.swing.JLabel("Director: " + safe(p, 4)));
        info.add(new javax.swing.JLabel("Writer: " + safe(p, 5)));
        info.add(new javax.swing.JLabel("Cast: " + safe(p, 3)));
        info.add(new javax.swing.JLabel("Release Date: " + safe(p, 7)));
        info.add(new javax.swing.JLabel("Status: " + safe(p, 10)));

        javax.swing.JTextArea syn = new javax.swing.JTextArea(safe(p, 11));
        syn.setLineWrap(true);
        syn.setWrapStyleWord(true);
        syn.setEditable(false);
        syn.setBorder(javax.swing.BorderFactory.createTitledBorder("Synopsis"));
        javax.swing.JScrollPane synScroll = new javax.swing.JScrollPane(syn);

        javax.swing.JPanel center = new javax.swing.JPanel(new java.awt.BorderLayout(5, 5));
        center.add(info, java.awt.BorderLayout.NORTH);
        center.add(synScroll, java.awt.BorderLayout.CENTER);
        d.add(center, java.awt.BorderLayout.CENTER);

        // SOUTH: only OK button
        javax.swing.JPanel buttons = new javax.swing.JPanel();
        javax.swing.JButton okBtn = new javax.swing.JButton("OK");
        buttons.add(okBtn);
        d.add(buttons, java.awt.BorderLayout.SOUTH);

        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                d.dispose();
            }
        });
        d.getRootPane().setDefaultButton(okBtn);

        d.setSize(700, 500);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    private String safe(String[] a, int i) {
        if (a != null && i >= 0 && i < a.length) {
            return a[i].trim();
        }
        return "";
    }

    private void resizePostersToFit() {
        int viewportW = NowShowingScrollPane.getViewport().getWidth();
        if (viewportW <= 0) {
            return;
        }

        // available width for cards = viewport - panel insets - gaps between columns
        int insets = nowShowingGridPanel.getInsets().left + nowShowingGridPanel.getInsets().right;
        int targetW = (viewportW - insets - (COLS - 1) * HGAP) / COLS;

        // scale each poster to targetW keeping its own aspect ratio
        for (java.awt.Component c : nowShowingGridPanel.getComponents()) {
            if (!(c instanceof javax.swing.JPanel)) {
                continue;
            }
            javax.swing.JPanel card = (javax.swing.JPanel) c;

            javax.swing.JLabel imgLabel = (javax.swing.JLabel) card.getClientProperty("imgLabel");
            java.awt.Image master = (java.awt.Image) card.getClientProperty("masterImage");
            if (imgLabel == null || master == null) {
                continue;
            }

            int mw = master.getWidth(null);
            int mh = master.getHeight(null);
            if (mw <= 0 || mh <= 0) {
                continue;
            }

            int targetH = (int) ((double) targetW * mh / mw);  // keep aspect ratio
            java.awt.Image scaled = master.getScaledInstance(targetW, targetH, java.awt.Image.SCALE_SMOOTH);

            imgLabel.setIcon(new javax.swing.ImageIcon(scaled));
            imgLabel.setPreferredSize(new java.awt.Dimension(targetW, targetH));
        }
    }

    private java.awt.Image loadImage(String path) {
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(new java.io.File(path));
            if (img != null) {
                return img;
            }
        } catch (Exception ignore) {
        }
        // fallback 2:3 gray placeholder
        int w = 400, h = 600;
        java.awt.image.BufferedImage ph = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g = ph.createGraphics();
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.fillRect(0, 0, w, h);
        g.setColor(java.awt.Color.DARK_GRAY);
        g.drawRect(0, 0, w - 1, h - 1);
        g.dispose();
        return ph;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NowShowingScrollPane = new javax.swing.JScrollPane();
        nowShowingGridPanel = new javax.swing.JPanel();

        NowShowingScrollPane.setViewportView(nowShowingGridPanel);

        nowShowingGridPanel.setLayout(new java.awt.GridLayout(0, 5, 20, 20));
        NowShowingScrollPane.setViewportView(nowShowingGridPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NowShowingScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NowShowingScrollPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane NowShowingScrollPane;
    private javax.swing.JPanel nowShowingGridPanel;
    // End of variables declaration//GEN-END:variables
}
