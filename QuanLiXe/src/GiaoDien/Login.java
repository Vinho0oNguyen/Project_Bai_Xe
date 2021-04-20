
package GiaoDien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import KetNoiSQL.*;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame {

    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public String kiemTraTK(String TK, String MK){
        String chucVu = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select * from DANG_NHAP as DN where DN.TAI_KHOANG = '" + TK + "' and DN.MAT_KHAU = '" + MK +"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                chucVu = rs.getString("MA_CHUC_VU");
            }
        } 
        catch (Exception e) {
            
        }
        return chucVu; 
    }
    
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jL_Acc = new javax.swing.JLabel();
        jL_Pss = new javax.swing.JLabel();
        jT_Acc = new javax.swing.JTextField();
        jPass_Pass = new javax.swing.JPasswordField();
        jL_Icon = new javax.swing.JLabel();
        jL_Tittle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btn_DN = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jL_Acc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Acc.setForeground(new java.awt.Color(255, 255, 255));
        jL_Acc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_Acc.setText("Tên đăng nhập");

        jL_Pss.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Pss.setForeground(new java.awt.Color(255, 255, 255));
        jL_Pss.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jL_Pss.setText("Mật khẩu");

        jT_Acc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jT_AccKeyPressed(evt);
            }
        });

        jPass_Pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPass_PassKeyPressed(evt);
            }
        });

        jL_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_garage_48px.png"))); // NOI18N

        jL_Tittle.setFont(new java.awt.Font("UTM Akashi", 1, 36)); // NOI18N
        jL_Tittle.setForeground(new java.awt.Color(204, 204, 204));
        jL_Tittle.setText("PARKING");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ĐĂNG NHẬP THÔNG TIN");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_DN.setBackground(new java.awt.Color(255, 255, 255));
        btn_DN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_DN.setText("Đăng nhập");
        btn_DN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DNActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jL_Icon)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jL_Tittle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jL_Pss)
                            .addComponent(jL_Acc))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_DN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jT_Acc, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPass_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel1)))
                .addGap(48, 48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jL_Icon)
                            .addComponent(jL_Tittle))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL_Acc)
                            .addComponent(jT_Acc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPass_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_Pss))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_DN)
                            .addComponent(jButton1)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DNActionPerformed
        // TODO add your handling code here:
        String TK = jT_Acc.getText();
        String MK = jPass_Pass.getText();
        
        
        
        if (TK.equals("") || MK.equals("")){
            
            JOptionPane.showMessageDialog(null, "Tài khoảng hoặc mật khẩu không tồn tại.", "", JOptionPane.WARNING_MESSAGE);
        }
        else{
            TK = TK.toUpperCase();
            MK = MK.toUpperCase();
            if (this.kiemTraTK(TK, MK).equals("QL")){
                this.dispose();
                new QuanLi(TK).setVisible(true);
            }
            else if(this.kiemTraTK(TK, MK).equals("TT")){
                this.dispose();
                new NhanVien(TK).setVisible(true);
            }
            else{
               
                JOptionPane.showMessageDialog(null, "Tai khoang khong ton tai.", "", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_btn_DNActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jT_AccKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_AccKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_ENTER){
            String TK = jT_Acc.getText();
            String MK = jPass_Pass.getText();



            if (TK.equals("") || MK.equals("")){

                JOptionPane.showMessageDialog(null, "Tài khoảng hoặc mật khẩu không tồn tại.", "", JOptionPane.WARNING_MESSAGE);
            }
            else{
                TK = TK.toUpperCase();
                MK = MK.toUpperCase();
                if (this.kiemTraTK(TK, MK).equals("QL")){
                    this.dispose();
                    new QuanLi(TK).setVisible(true);
                }
                else if(this.kiemTraTK(TK, MK).equals("TT")){
                    this.dispose();
                    new NhanVien(TK).setVisible(true);
                }
                else{

                    JOptionPane.showMessageDialog(null, "Tai khoang khong ton tai.", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jT_AccKeyPressed

    private void jPass_PassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPass_PassKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_ENTER){
            String TK = jT_Acc.getText();
            String MK = jPass_Pass.getText();



            if (TK.equals("") || MK.equals("")){

                JOptionPane.showMessageDialog(null, "Tài khoảng hoặc mật khẩu không tồn tại.", "", JOptionPane.WARNING_MESSAGE);
            }
            else{
                TK = TK.toUpperCase();
                MK = MK.toUpperCase();
                if (this.kiemTraTK(TK, MK).equals("QL")){
                    this.dispose();
                    new QuanLi(TK).setVisible(true);
                }
                else if(this.kiemTraTK(TK, MK).equals("TT")){
                    this.dispose();
                    new NhanVien(TK).setVisible(true);
                }
                else{

                    JOptionPane.showMessageDialog(null, "Tai khoang khong ton tai.", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jPass_PassKeyPressed

    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DN;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jL_Acc;
    private javax.swing.JLabel jL_Icon;
    private javax.swing.JLabel jL_Pss;
    private javax.swing.JLabel jL_Tittle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPass_Pass;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jT_Acc;
    // End of variables declaration//GEN-END:variables
}
