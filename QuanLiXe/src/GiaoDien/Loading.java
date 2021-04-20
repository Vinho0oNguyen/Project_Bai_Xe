
package GiaoDien;


public class Loading extends javax.swing.JFrame {

   
    public Loading() {
        initComponents();
    }

    public void animation(){
        Loading lg = new Loading();
        lg.setVisible(true);
        try{
            for (int i=1; i<100; i++){
                    Thread.sleep(100);
                    lg.jL_LoadDing.setText(i + "%");
                    if(i == 10){
                        lg.asdf.setText("Turning On Modules....");
                    }
                    if(i == 20){
                        lg.asdf.setText("Loading Modules....");
                    }
                    if(i==50){
                        lg.asdf.setText("Connecting to Database....");
                    }
                    if(i==70){
                        lg.asdf.setText("Connecting Successful....");
                    }
                    if(i==90){
                        lg.asdf.setText("Lauching Application....");
                    }
                    lg.jPro_Load.setValue(i);        
            }   
            lg.dispose();
        }        
        catch (Exception e){
                    
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_Login = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        asdf = new javax.swing.JLabel();
        jL_LoadDing = new javax.swing.JLabel();
        jPro_Load = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/6ec8fa35800b339aa060d70d67edcf03.gif"))); // NOI18N

        asdf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        asdf.setText("Loanding...");

        jL_LoadDing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jL_LoadDing.setText("0 %");

        javax.swing.GroupLayout jP_LoginLayout = new javax.swing.GroupLayout(jP_Login);
        jP_Login.setLayout(jP_LoginLayout);
        jP_LoginLayout.setHorizontalGroup(
            jP_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_LoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(asdf, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jL_LoadDing, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jP_LoginLayout.createSequentialGroup()
                .addComponent(jLabel20)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPro_Load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jP_LoginLayout.setVerticalGroup(
            jP_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_LoginLayout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asdf)
                    .addComponent(jL_LoadDing))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPro_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jP_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        Loading ld = new Loading();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ld.setVisible(true);
            }
        });
       
        try{
            for (int i=1; i<100; i++){
                    Thread.sleep(60);
                    ld.jL_LoadDing.setText(i + "%");
                    if(i == 10){
                        ld.asdf.setText("Turning On Modules....");
                    }
                    if(i == 20){
                        ld.asdf.setText("Loading Modules....");
                    }
                    if(i==50){
                        ld.asdf.setText("Connecting to Database....");
                    }
                    if(i==70){
                        ld.asdf.setText("Connecting Successful....");
                    }
                    if(i==90){
                        ld.asdf.setText("Lauching Application....");
                    }
                    ld.jPro_Load.setValue(i);        
            }   
            
        }        
        catch (Exception e){
                    
        }
        ld.dispose();
        new Login().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asdf;
    private javax.swing.JLabel jL_LoadDing;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jP_Login;
    private javax.swing.JProgressBar jPro_Load;
    // End of variables declaration//GEN-END:variables
}
