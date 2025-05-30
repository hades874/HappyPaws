/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happypaws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Reservation extends javax.swing.JFrame {

    /**
     * Creates new form Reservation
     */
    
    Connection con = null;
    PreparedStatement pre = null;
    PreparedStatement pre2 = null;
    PreparedStatement pre3 = null;
    PreparedStatement pre4 = null;
    PreparedStatement pre5 = null;
    ResultSet res = null;
    ResultSet res2 = null;
    ResultSet res4 = null;
    
    public Reservation() {
        initComponents();
        con = Connect.connect();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1064, 468, 480, 30));

        jDateChooser2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1095, 512, 450, 30));

        jButton1.setBackground(new java.awt.Color(211, 167, 128));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        jButton1.setText("SUBMIT");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 620, 100, 40));

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 890, 80, 70));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(31, 59, 77));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("A bit under the weather? Drop it's medications");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 690, 400, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/happypaws/reservation.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1920, 970));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            
            SimpleDateFormat date_formate = new SimpleDateFormat("MM-dd-yyyy");
            String inDate = date_formate.format(jDateChooser3.getDate());
            String outDate = date_formate.format(jDateChooser2.getDate());
            
            pre2 = con.prepareStatement("select top 1 Customer_ID from customer order by Customer_ID desc");
            res = pre2.executeQuery();
            int cusID = 0;
            while(res.next())
            {
                cusID = res.getInt("Customer_ID");
            }
            
            pre3 = con.prepareStatement("select top 1 Pet_Id from pets order by Pet_Id desc");
            res2 = pre3.executeQuery();
            int petID = 0;
            while(res2.next())
            {
                petID = res2.getInt("Pet_Id");
            }
          

            pre = con.prepareStatement("insert into reservationInfo(Check_In_Date,Check_Out_Date, CustomerID, Pet_ID) values(?,?,?,?)");
            pre.setString(1, inDate);
            pre.setString(2, outDate);
            pre.setInt(3, cusID);
            pre.setInt(4, petID);

            pre.executeUpdate();

            jDateChooser3.setDate(null);
            jDateChooser2.setDate(null);
            
            pre4 = con.prepareStatement("select top 1 Pet_Id from pets order by Pet_Id desc");
            res4 = pre4.executeQuery();
            int pID = 0;
            while(res4.next())
            {
                pID = res4.getInt("Pet_Id");
            }
            
            pre5 = con.prepareStatement("insert into medicine(Pet_ID) values(?)");
            
            pre5.setInt(1, pID);
            
            pre5.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "New Pet Registered");

        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        new petDetails().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        new medicines().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reservation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
