
package clockapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class ClockFrame extends javax.swing.JFrame {

    private boolean running = false; // Status thread
    private Thread clockThread; // Thread untuk jam

    public ClockFrame() {
        initComponents();
        setLocationRelativeTo(null); // Agar berada di tengah layar
    
        // Tambahkan event listener untuk tombol Start
        btnStart.addActionListener(e -> startClock());
    
        // Tambahkan event listener untuk tombol Stop
        btnStop.addActionListener(e -> stopClock());
    }

    private void startClock() {
    if (running) return; // Jika sudah berjalan, jangan buat thread baru

    running = true;
    lblClock.setText("00:00:00"); // Reset ke 00:00:00 saat mulai

    clockThread = new Thread(() -> {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while (running) {
            String time = sdf.format(new Date());
            System.out.println("Waktu saat ini: " + time); // Cetak waktu ke Output NetBeans
            SwingUtilities.invokeLater(() -> lblClock.setText(time)); // Update label di Swing Thread
            try {
                Thread.sleep(1000); // Tunggu 1 detik
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Hentikan thread dengan benar
            }
        }
    });
    clockThread.start();
}


    private void stopClock() {
        running = false;
        if (clockThread != null) {
            clockThread.interrupt(); // Hentikan thread dengan baik
        }
    }
    
//    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblClock = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblClock.setText("00:00:00");
        getContentPane().add(lblClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 86, -1, -1));

        btnStart.setText("Start");
        getContentPane().add(btnStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 145, -1, -1));

        btnStop.setText("Stop");
        getContentPane().add(btnStop, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 145, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ClockFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JLabel lblClock;
    // End of variables declaration//GEN-END:variables
}
