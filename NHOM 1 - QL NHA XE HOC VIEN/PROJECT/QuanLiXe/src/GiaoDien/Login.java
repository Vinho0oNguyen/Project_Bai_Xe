
package GiaoDien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import KetNoiSQL.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import api.speedsms.vn.SpeedSMSAPI;
import Service.SeQL;
import java.io.IOException;


public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        this.setTitle("Đăng nhập");
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
    
    public String layMK(String TK){
        String matKhau = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select DN.MAT_KHAU from DANG_NHAP as DN where DN.TAI_KHOANG = '" + TK + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                matKhau = rs.getString("MAT_KHAU");
            }
        } 
        catch (Exception e) {
            
        }
        return matKhau; 
    }
    
    public String laySDT(String maNV){
        String SDT = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select SDT from NHAN_VIEN where MA_NV = '" + maNV + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                SDT = rs.getString("SDT");
            }
        } 
        catch (Exception e) {
            
        }
        return SDT; 
    }
    
    public void suaNV(String maNV, String SDT){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update NHAN_VIEN set MA_NV = ?, SDT = ? where MA_NV = '" + maNV + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, SDT);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void senSMS(String SDT, String TK, String matKhau){
        SpeedSMSAPI api = new SpeedSMSAPI("Ug9Kw6C6CUAgmpgY6fsjWFXDSV-AafWG");
        try {
            String result = api.sendSMS(SDT, "Mau khau cua tai khoang " + TK + " la: " + matKhau, 5, "596ceb3aea858304");
            JOptionPane.showMessageDialog(null, "Gửi thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
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
        btn_Thoat = new javax.swing.JButton();
        jL_QuenMK = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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
        btn_DN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DNMouseExited(evt);
            }
        });
        btn_DN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DNActionPerformed(evt);
            }
        });

        btn_Thoat.setBackground(new java.awt.Color(255, 255, 255));
        btn_Thoat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Thoat.setText("Thoát");
        btn_Thoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ThoatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ThoatMouseExited(evt);
            }
        });
        btn_Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThoatActionPerformed(evt);
            }
        });

        jL_QuenMK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_QuenMK.setForeground(new java.awt.Color(0, 51, 51));
        jL_QuenMK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_QuenMK.setText("Quên mật khẩu?");
        jL_QuenMK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jL_QuenMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jL_QuenMKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jL_QuenMKMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_QuenMKMousePressed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_eye_24px_1.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel2MouseReleased(evt);
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
                                        .addComponent(btn_Thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jT_Acc, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPass_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jLabel1)))
                        .addGap(48, 48, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jL_QuenMK, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215))))
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jPass_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jL_Pss)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_DN)
                            .addComponent(btn_Thoat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jL_QuenMK))
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
            
            JOptionPane.showMessageDialog(null, "Tài khoảng hoặc mật khẩu không tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
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
               
                JOptionPane.showMessageDialog(null, "Tài khoảng không tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_btn_DNActionPerformed

    private void btn_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_ThoatActionPerformed

    private void jT_AccKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_AccKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_ENTER){
            String TK = jT_Acc.getText();
            String MK = jPass_Pass.getText();



            if (TK.equals("") || MK.equals("")){

                JOptionPane.showMessageDialog(null, "Tài khoảng hoặc mật khẩu không tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
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

                    JOptionPane.showMessageDialog(null, "Tài khoảng không tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
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

                JOptionPane.showMessageDialog(null, "Tài khoảng hoặc mật khẩu không tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
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

                    JOptionPane.showMessageDialog(null, "Tài khoảng không tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jPass_PassKeyPressed

    private void jL_QuenMKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_QuenMKMouseEntered
        // TODO add your handling code here:
        jL_QuenMK.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_jL_QuenMKMouseEntered

    private void jL_QuenMKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_QuenMKMouseExited
        // TODO add your handling code here:
        jL_QuenMK.setForeground(new Color(0,51,51));
    }//GEN-LAST:event_jL_QuenMKMouseExited

    private void jL_QuenMKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_QuenMKMousePressed
        // TODO add your handling code here:
        String TK = JOptionPane.showInputDialog("Nhập mã nhân viên:");
        String matKhau = this.layMK(TK);
        String SDT = this.laySDT(TK);
        
        SeQL thaoTac = new SeQL();
        
        String []luaChon = {"Số điện thoại đã đăng ký.", "Số điện thoại mới."};

        if (matKhau.equals("")){
            JOptionPane.showMessageDialog(null, "Tài khoảng không tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else if (!matKhau.equals("")){//nhan vien da co so dien thoai
            //lua chon cach gui tin nhan
            int option = JOptionPane.showOptionDialog(null, "Lựa chọn số điện thoại nhận tin nhắn", 
                    "Thông báo", 0, JOptionPane.QUESTION_MESSAGE, null, luaChon, "Số điện thoại đăng ký");
            if (option == 0){
                if(!SDT.equals("")){
                    this.senSMS(SDT, TK, matKhau);
                }
                else if (SDT.equals("")){
                    String newSDT = (String)JOptionPane.showInputDialog("Nhập số điện thoại muốn nhận tin nhắn:");
                    if(thaoTac.chuanHoaSDT(newSDT) == 1){
                        this.senSMS(newSDT, TK, matKhau);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Nhập sai định dạng SDT.Xin nhập lại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
            }
            else if (option == 1){
                String newSDT = JOptionPane.showInputDialog("Nhập số điện thoại muốn nhận tin nhắn:");
                if(thaoTac.chuanHoaSDT(newSDT) == 1){
                    this.senSMS(newSDT, TK, matKhau);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Nhập sai định dạng SDT.Xin nhập lại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        
    }//GEN-LAST:event_jL_QuenMKMousePressed

    private void btn_DNMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DNMouseEntered
        // TODO add your handling code here:
        btn_DN.setBackground(new Color(0, 255, 51));
    }//GEN-LAST:event_btn_DNMouseEntered

    private void btn_DNMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DNMouseExited
        // TODO add your handling code here:
        btn_DN.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_DNMouseExited

    private void btn_ThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMouseEntered
        // TODO add your handling code here:
        btn_Thoat.setBackground(new Color(0, 255, 51));
    }//GEN-LAST:event_btn_ThoatMouseEntered

    private void btn_ThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMouseExited
        // TODO add your handling code here:
        btn_Thoat.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btn_ThoatMouseExited

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        jPass_Pass.setEchoChar((char)0);
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseReleased
        // TODO add your handling code here:
        jPass_Pass.setEchoChar('*');
    }//GEN-LAST:event_jLabel2MouseReleased

    
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
    private javax.swing.JButton btn_Thoat;
    private javax.swing.JLabel jL_Acc;
    private javax.swing.JLabel jL_Icon;
    private javax.swing.JLabel jL_Pss;
    private javax.swing.JLabel jL_QuenMK;
    private javax.swing.JLabel jL_Tittle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPass_Pass;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jT_Acc;
    // End of variables declaration//GEN-END:variables
}
