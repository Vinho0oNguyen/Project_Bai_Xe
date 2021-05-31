
package GiaoDien;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import KetNoiSQL.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Service.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NhanVien extends javax.swing.JFrame {
    
    private String maNV;
    SeXe QLXe = new SeXe();
    SeNV thaoTac = new SeNV();
    
    public NhanVien(String maNV) {
        this.maNV = maNV;
        initComponents();
        this.setTitle("Nhân viên trực");
        this.setLocationRelativeTo(null);
        capNhatTTVeThang();
        this.layTT(maNV);
        this.layTTCaTruc(maNV);
        layTTXe();
    }

    private NhanVien() {
    }
    
    //lat TT nhan vien
    public void layTT(String TK){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT NV.MA_NV, NV.HO_TEN, NV.DIA_CHI, NV.NAM_SINH, NV.SDT, NV.GIOI_TINH, CV.TEN_CHUC_VU, DN.MAT_KHAU"
                   + " FROM NHAN_VIEN AS NV, DANG_NHAP AS DN, CHUC_VU AS CV" 
                   + " WHERE NV.MA_NV = DN.TAI_KHOANG AND DN.MA_CHUC_VU = CV.MA_CHUC_VU AND DN.TAI_KHOANG = '" + TK + "'";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                this.jT_ID.setText(rs.getString("MA_NV"));
                this.jT_Name.setText(rs.getString("HO_TEN"));
                this.jT_Add.setText(rs.getString("DIA_CHI"));
                this.jDate_Date.setDate(rs.getDate("NAM_SINH"));
                this.jT_Phone.setText(rs.getString("SDT"));
                this.jC_Sex.setSelectedItem(rs.getString("GIOI_TINH"));
                this.jC_Class.setSelectedItem(rs.getString("TEN_CHUC_VU"));
                this.jPass_Pass.setText(rs.getString("MAT_KHAU")); 
            }
            rs.close();
            ps.close();
            ketNoi.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
   }

    //lay thong tin nhung xe con gui
    public void layTTXe(){
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_GuiXe.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql =  "SELECT VX.MA_VE, LV.TEN_LOAI, VX.BIEN_SO_XE, X.HIEU_XE, X.MAU_XE, VX.NGAY_VAO, VX.GIO_VAO" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV, XE AS X" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.BIEN_SO_XE = X.BIEN_SO_XE AND VX.TINH_TRANG = N'CHƯA LẤY'";
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("MA_VE"));
                vt.add(rs.getString("TEN_LOAI"));
                vt.add(rs.getString("BIEN_SO_XE"));
                vt.add(rs.getString("HIEU_XE"));
                vt.add(rs.getString("MAU_XE"));
                vt.add(rs.getDate("NGAY_VAO"));
                vt.add(rs.getTime("GIO_VAO"));
                dtm1.addRow(vt);
            }
            jTable_GuiXe.setModel(dtm1);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //lay thong tin nhung xe trong o tim kiem hieu xe
    public void layTTXeTimKiemHieu(String hieuXe){
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_GuiXe.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql =  "SELECT VX.MA_VE, LV.TEN_LOAI, VX.BIEN_SO_XE, X.HIEU_XE, X.MAU_XE, VX.NGAY_VAO, VX.GIO_VAO" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV, XE AS X" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.BIEN_SO_XE = X.BIEN_SO_XE AND X.HIEU_XE LIKE '%" + hieuXe + "%' AND VX.TINH_TRANG = N'CHƯA LẤY'";
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("MA_VE"));
                vt.add(rs.getString("TEN_LOAI"));
                vt.add(rs.getString("BIEN_SO_XE"));
                vt.add(rs.getString("HIEU_XE"));
                vt.add(rs.getString("MAU_XE"));
                vt.add(rs.getDate("NGAY_VAO"));
                vt.add(rs.getTime("GIO_VAO"));
                dtm1.addRow(vt);
            }
            jTable_GuiXe.setModel(dtm1);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //lay thong tin nhung xe trong o tim kiem hieu xe
    public void layTTXeTimKiemSo(String bienSo){
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_GuiXe.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql =  "SELECT VX.MA_VE, LV.TEN_LOAI, VX.BIEN_SO_XE, X.HIEU_XE, X.MAU_XE, VX.NGAY_VAO, VX.GIO_VAO" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV, XE AS X" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.BIEN_SO_XE = X.BIEN_SO_XE AND VX.BIEN_SO_XE LIKE '%" + bienSo + "%' AND VX.TINH_TRANG = N'CHƯA LẤY'";
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("MA_VE"));
                vt.add(rs.getString("TEN_LOAI"));
                vt.add(rs.getString("BIEN_SO_XE"));
                vt.add(rs.getString("HIEU_XE"));
                vt.add(rs.getString("MAU_XE"));
                vt.add(rs.getDate("NGAY_VAO"));
                vt.add(rs.getTime("GIO_VAO"));
                dtm1.addRow(vt);
            }
            jTable_GuiXe.setModel(dtm1);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Lay nhung xe dang ky thang da het han
    public void capNhatTTVeThang(){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select BIEN_SO_XE\n" +
                    "FROM QL_VE_THANG\n" +
                    "WHERE NGAY_HET_HAN < '" + java.sql.Date.valueOf(java.time.LocalDate.now()) +"'";
        try {
           PreparedStatement pr = ketNoi.prepareStatement(sql);
           ResultSet rs = pr.executeQuery();
           while(rs.next()){
               QLXe.capNhatTTVeThang(rs.getString("BIEN_SO_XE"), "HẾT HẠN");
           }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void layTTCaTruc(String TK){
        Date ngayHT = Date.valueOf(LocalDate.now());
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_LichTruc.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select CT.TEN_CA, PC.NGAY_LAM, CT.GIO_BAT_DAU, CT.GIO_KET_THUC\n" +
                    "from CA_TRUC as CT, PHAN_CONG_TRUC as PC\n" +
                    "where CT.MA_CA = PC.MA_CA and PC.MA_NHAN_VIEN = '" + TK + "' AND PC.NGAY_LAM >= '" + ngayHT + "'";
        Vector vt;
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("TEN_CA"));
                vt.add(rs.getDate("NGAY_LAM"));
                vt.add(rs.getTime("GIO_BAT_DAU"));
                vt.add(rs.getTime("GIO_KET_THUC"));
                dtm1.addRow(vt);
            }
            jTable_LichTruc.setModel(dtm1);
            rs.close();
            ps.close();
            ketNoi.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDi_SinhVien = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jT_MaSV = new javax.swing.JTextField();
        jT_HoTen = new javax.swing.JTextField();
        jT_Lop = new javax.swing.JTextField();
        jT_QueQuan = new javax.swing.JTextField();
        jC_GioiTinh = new javax.swing.JComboBox<>();
        btn_XNSV = new javax.swing.JButton();
        btn_HuySV = new javax.swing.JButton();
        jDate_NgaySV = new com.toedter.calendar.JDateChooser();
        jDi_XNDKThang = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jL_Tien = new javax.swing.JLabel();
        jL_MaSV = new javax.swing.JLabel();
        jL_SoXe = new javax.swing.JLabel();
        jL_HieuXe1 = new javax.swing.JLabel();
        jL_MauXeT = new javax.swing.JLabel();
        jL_TGDK = new javax.swing.JLabel();
        jL_TGHetHan = new javax.swing.JLabel();
        btn_XacNhanTien = new javax.swing.JButton();
        btn_HuyTien = new javax.swing.JButton();
        jDi_ThanhTien = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jL_Tien1 = new javax.swing.JLabel();
        jL_SoXe1 = new javax.swing.JLabel();
        jL_HieuXe2 = new javax.swing.JLabel();
        jL_MauXeT1 = new javax.swing.JLabel();
        jL_NgayVao = new javax.swing.JLabel();
        jL_GioVao = new javax.swing.JLabel();
        btn_XacNhanThanhTien = new javax.swing.JButton();
        btn_HuyThanhTien = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jL_NgayRA = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jL_GioRa = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jL_Loi = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jL_Icon = new javax.swing.JLabel();
        jL_Tittle = new javax.swing.JLabel();
        jL_Icon2 = new javax.swing.JLabel();
        jTable_NV = new javax.swing.JTabbedPane();
        jPan_GuiXe = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_GuiXe = new javax.swing.JTable();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jL_BienSo = new javax.swing.JLabel();
        jT_BienSo = new javax.swing.JTextField();
        jT_HieuXe = new javax.swing.JTextField();
        jL_Hieuxe = new javax.swing.JLabel();
        jT_MauXe = new javax.swing.JTextField();
        jL_Mauxe = new javax.swing.JLabel();
        jL_Time = new javax.swing.JLabel();
        jT_Time = new javax.swing.JTextField();
        jL_Ngay = new javax.swing.JLabel();
        jDate_Ngay = new com.toedter.calendar.JDateChooser();
        btn_Vao = new javax.swing.JButton();
        btn_Ra = new javax.swing.JButton();
        jL_Mauxe1 = new javax.swing.JLabel();
        jC_Loi = new javax.swing.JComboBox<>();
        btn_Thang = new javax.swing.JButton();
        jT_FindHieuXe = new javax.swing.JTextField();
        btn_FindVe = new javax.swing.JButton();
        jT_FindBienSo = new javax.swing.JTextField();
        btn_FindName = new javax.swing.JButton();
        btn_Huy = new javax.swing.JButton();
        jPan_Inf = new javax.swing.JPanel();
        jL_Tittle2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jL_ID = new javax.swing.JLabel();
        jT_ID = new javax.swing.JTextField();
        jL_Name = new javax.swing.JLabel();
        jT_Name = new javax.swing.JTextField();
        jL_Date = new javax.swing.JLabel();
        jDate_Date = new com.toedter.calendar.JDateChooser();
        jL_Sex = new javax.swing.JLabel();
        jC_Sex = new javax.swing.JComboBox<>();
        jL_Class = new javax.swing.JLabel();
        jC_Class = new javax.swing.JComboBox<>();
        jL_Add = new javax.swing.JLabel();
        jT_Add = new javax.swing.JTextField();
        jL_Pass = new javax.swing.JLabel();
        jPass_Pass = new javax.swing.JPasswordField();
        jL_Phone = new javax.swing.JLabel();
        jT_Phone = new javax.swing.JTextField();
        btn_Edit = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_LichTruc = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        btn_DangXuat = new javax.swing.JButton();

        jDi_SinhVien.setMinimumSize(new java.awt.Dimension(420, 440));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("THÔNG TIN SINH VIÊN ĐĂNG KÍ THÁNG");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Mã sinh viên:");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_user_48px_1.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Họ tên:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Lớp:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Năm sinh:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Quê quán:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Giới tính:");

        jC_GioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        btn_XNSV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_XNSV.setText("Xác nhận");
        btn_XNSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XNSVActionPerformed(evt);
            }
        });

        btn_HuySV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_HuySV.setText("Hủy");
        btn_HuySV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuySVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDi_SinhVienLayout = new javax.swing.GroupLayout(jDi_SinhVien.getContentPane());
        jDi_SinhVien.getContentPane().setLayout(jDi_SinhVienLayout);
        jDi_SinhVienLayout.setHorizontalGroup(
            jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDi_SinhVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDi_SinhVienLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDi_SinhVienLayout.createSequentialGroup()
                        .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(73, 73, 73)
                        .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jT_MaSV)
                            .addComponent(jT_HoTen)
                            .addComponent(jT_Lop)
                            .addComponent(jT_QueQuan)
                            .addComponent(jC_GioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDate_NgaySV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jDi_SinhVienLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btn_XNSV)
                .addGap(59, 59, 59)
                .addComponent(btn_HuySV, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDi_SinhVienLayout.setVerticalGroup(
            jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDi_SinhVienLayout.createSequentialGroup()
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDi_SinhVienLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDi_SinhVienLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jT_MaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jT_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jT_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jDate_NgaySV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jT_QueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jC_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jDi_SinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_XNSV)
                    .addComponent(btn_HuySV))
                .addGap(28, 28, 28))
        );

        jDi_XNDKThang.setMinimumSize(new java.awt.Dimension(400, 400));

        jPanel6.setBackground(new java.awt.Color(102, 102, 255));
        jPanel6.setMinimumSize(new java.awt.Dimension(400, 322));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("XÁC NHẬN ĐĂNG KÍ VÉ THÁNG");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 51));
        jLabel2.setText("Số tiền cần thanh toán:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã sinh viên:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Biển số xe:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Hiệu xe:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Màu xe:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Thời gian đăng kí: ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Thời gian hết hạn:");

        jL_Tien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Tien.setForeground(new java.awt.Color(255, 255, 51));
        jL_Tien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Tien.setText("jLabel9");

        jL_MaSV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_MaSV.setForeground(new java.awt.Color(255, 255, 255));
        jL_MaSV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_MaSV.setText("jLabel9");

        jL_SoXe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_SoXe.setForeground(new java.awt.Color(255, 255, 255));
        jL_SoXe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_SoXe.setText("jLabel9");

        jL_HieuXe1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_HieuXe1.setForeground(new java.awt.Color(255, 255, 255));
        jL_HieuXe1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_HieuXe1.setText("jLabel9");

        jL_MauXeT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_MauXeT.setForeground(new java.awt.Color(255, 255, 255));
        jL_MauXeT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_MauXeT.setText("jLabel9");

        jL_TGDK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_TGDK.setForeground(new java.awt.Color(255, 255, 255));
        jL_TGDK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_TGDK.setText("jLabel9");

        jL_TGHetHan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_TGHetHan.setForeground(new java.awt.Color(255, 255, 255));
        jL_TGHetHan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_TGHetHan.setText("jLabel9");

        btn_XacNhanTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_XacNhanTien.setText("Xác nhận");
        btn_XacNhanTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XacNhanTienActionPerformed(evt);
            }
        });

        btn_HuyTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_HuyTien.setText("Hủy");
        btn_HuyTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyTienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(80, 80, 80)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jL_MaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_SoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_HieuXe1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_MauXeT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_TGHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_Tien, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(jL_TGDK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btn_XacNhanTien)
                .addGap(72, 72, 72)
                .addComponent(btn_HuyTien, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel3)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel4)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel7)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel8)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel2))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jL_MaSV)
                                .addGap(15, 15, 15)
                                .addComponent(jL_SoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jL_HieuXe1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jL_MauXeT, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jL_TGHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jL_Tien, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel5))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(jLabel6))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jL_TGDK, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_XacNhanTien)
                    .addComponent(btn_HuyTien))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDi_XNDKThangLayout = new javax.swing.GroupLayout(jDi_XNDKThang.getContentPane());
        jDi_XNDKThang.getContentPane().setLayout(jDi_XNDKThangLayout);
        jDi_XNDKThangLayout.setHorizontalGroup(
            jDi_XNDKThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDi_XNDKThangLayout.setVerticalGroup(
            jDi_XNDKThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDi_ThanhTien.setMinimumSize(new java.awt.Dimension(320, 390));
        jDi_ThanhTien.getContentPane().setLayout(null);

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));
        jPanel7.setMinimumSize(new java.awt.Dimension(320, 390));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("XÁC NHẬN THANH TOÁN");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 51));
        jLabel18.setText("Số tiền cần thanh toán:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Biển số xe:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Hiệu xe:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Màu xe:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Ngày vào:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Giờ vào:");

        jL_Tien1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Tien1.setForeground(new java.awt.Color(255, 255, 51));
        jL_Tien1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Tien1.setText("jLabel9");

        jL_SoXe1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_SoXe1.setForeground(new java.awt.Color(255, 255, 255));
        jL_SoXe1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_SoXe1.setText("jLabel9");

        jL_HieuXe2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_HieuXe2.setForeground(new java.awt.Color(255, 255, 255));
        jL_HieuXe2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_HieuXe2.setText("jLabel9");

        jL_MauXeT1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_MauXeT1.setForeground(new java.awt.Color(255, 255, 255));
        jL_MauXeT1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_MauXeT1.setText("jLabel9");

        jL_NgayVao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_NgayVao.setForeground(new java.awt.Color(255, 255, 255));
        jL_NgayVao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_NgayVao.setText("jLabel9");

        jL_GioVao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_GioVao.setForeground(new java.awt.Color(255, 255, 255));
        jL_GioVao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_GioVao.setText("jLabel9");

        btn_XacNhanThanhTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_XacNhanThanhTien.setText("Xác nhận");
        btn_XacNhanThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XacNhanThanhTienActionPerformed(evt);
            }
        });

        btn_HuyThanhTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_HuyThanhTien.setText("Hủy");
        btn_HuyThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyThanhTienActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Ngày ra:");

        jL_NgayRA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_NgayRA.setForeground(new java.awt.Color(255, 255, 255));
        jL_NgayRA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_NgayRA.setText("jLabel9");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Giờ ra:");

        jL_GioRa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_GioRa.setForeground(new java.awt.Color(255, 255, 255));
        jL_GioRa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_GioRa.setText("jLabel9");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Lỗi:");

        jL_Loi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Loi.setForeground(new java.awt.Color(255, 255, 255));
        jL_Loi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Loi.setText("jLabel9");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_XacNhanThanhTien)
                        .addGap(34, 34, 34)
                        .addComponent(btn_HuyThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jL_NgayRA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_GioRa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_Loi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_Tien1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jL_NgayVao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_GioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_MauXeT1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_HieuXe2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_SoXe1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel17)
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jL_SoXe1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jL_HieuXe2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jL_MauXeT1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jL_NgayVao, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jL_GioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_NgayRA, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_GioRa, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_Loi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jL_Tien1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_XacNhanThanhTien)
                    .addComponent(btn_HuyThanhTien))
                .addGap(34, 34, 34))
        );

        jDi_ThanhTien.getContentPane().add(jPanel7);
        jPanel7.setBounds(0, 0, 320, 390);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jL_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_garage_48px.png"))); // NOI18N

        jL_Tittle.setFont(new java.awt.Font("UTM Akashi", 1, 36)); // NOI18N
        jL_Tittle.setForeground(new java.awt.Color(204, 204, 204));
        jL_Tittle.setText("PARKING");

        jL_Icon2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jL_Icon2.setForeground(new java.awt.Color(255, 255, 255));
        jL_Icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_faq_30px.png"))); // NOI18N
        jL_Icon2.setText("Hỗ Trợ");
        jL_Icon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jL_Icon2MousePressed(evt);
            }
        });

        jTable_NV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPan_GuiXe.setBackground(new java.awt.Color(255, 255, 255));
        jPan_GuiXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPan_GuiXeMousePressed(evt);
            }
        });

        jTable_GuiXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vé", "Loại vé", "Biển số", "Hiệu xe", "Màu xe", "Ngày vào", "Giờ vào"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_GuiXe.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable_GuiXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable_GuiXeMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_GuiXe);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jL_BienSo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_BienSo.setText("Biển số:");

        jT_BienSo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jT_BienSoKeyPressed(evt);
            }
        });

        jT_HieuXe.setEditable(false);

        jL_Hieuxe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Hieuxe.setText("Hiệu xe:");

        jT_MauXe.setEditable(false);

        jL_Mauxe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Mauxe.setText("Màu xe:");

        jL_Time.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Time.setText("Giờ vào bến:");

        jT_Time.setEnabled(false);

        jL_Ngay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Ngay.setText("Ngày vào bến:");

        jDate_Ngay.setDateFormatString("yyyy-MM-dd");
        jDate_Ngay.setEnabled(false);

        btn_Vao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Vao.setText("Vào bến");
        btn_Vao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VaoActionPerformed(evt);
            }
        });

        btn_Ra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Ra.setText("Xuất bến");
        btn_Ra.setEnabled(false);
        btn_Ra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RaActionPerformed(evt);
            }
        });

        jL_Mauxe1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Mauxe1.setText("Lỗi:");

        jC_Loi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));
        jC_Loi.setEnabled(false);

        btn_Thang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Thang.setText("Đăng ký tháng");
        btn_Thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThangActionPerformed(evt);
            }
        });

        jT_FindHieuXe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_FindHieuXeKeyReleased(evt);
            }
        });

        btn_FindVe.setText("Tìm kiếm theo hiệu xe");

        jT_FindBienSo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_FindBienSoKeyReleased(evt);
            }
        });

        btn_FindName.setText("Tìm kiếm theo biển số");

        btn_Huy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Huy.setText("Hủy");
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPan_GuiXeLayout = new javax.swing.GroupLayout(jPan_GuiXe);
        jPan_GuiXe.setLayout(jPan_GuiXeLayout);
        jPan_GuiXeLayout.setHorizontalGroup(
            jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jL_Hieuxe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jL_Mauxe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jL_BienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jT_BienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jT_HieuXe, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jT_MauXe, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jL_Ngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jL_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jL_Mauxe1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jT_Time, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(jDate_Ngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jC_Loi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                                        .addComponent(btn_Vao, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_Ra)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_Thang)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)))
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                        .addComponent(jT_FindHieuXe, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_FindVe)
                        .addGap(54, 54, 54)
                        .addComponent(jT_FindBienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_FindName)))
                .addGap(15, 15, 15))
        );
        jPan_GuiXeLayout.setVerticalGroup(
            jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator9)
                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jL_BienSo)
                                    .addComponent(jT_BienSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_Time)
                                    .addComponent(jT_Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jL_Ngay)
                                    .addComponent(jL_Hieuxe)
                                    .addComponent(jT_HieuXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jDate_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL_Mauxe1)
                            .addComponent(jC_Loi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_Mauxe)
                            .addComponent(jT_MauXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Vao)
                            .addComponent(btn_Ra)
                            .addComponent(btn_Thang)
                            .addComponent(btn_Huy)))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jT_FindHieuXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_FindVe)
                    .addComponent(jT_FindBienSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_FindName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        Thread t = new Thread(new Runnable() {
            @Override
            public void run(){
                while(true){
                    LocalTime LD = LocalTime.now();

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String tmp = LD.format(format);
                    jT_Time.setText(tmp);
                }
            }
        });
        t.start();
        //Thread t1 = new Thread(new Runnable() {
            //    @Override
            //    public void run(){
                //        while(true){
                    //            jDate_Ngay.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
                    //        }
                //    }
            //});
    //t1.start();
    jDate_Ngay.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
    Connection ketNoi2 = KetNoiCSDL.ketNoi();
    String sql2 = "select TEN_SU_CO from SU_CO";
    try{
        PreparedStatement pr = ketNoi2.prepareStatement(sql2);
        ResultSet rs = pr.executeQuery();
        while (rs.next()){
            jC_Loi.addItem(rs.getString("TEN_SU_CO"));
        }
        rs.close();
        pr.close();
        ketNoi2.close();
    }
    catch (Exception e){
        e.printStackTrace();
    }

    jTable_NV.addTab("Quản Lí Gửi Xe", jPan_GuiXe);

    jPan_Inf.setBackground(new java.awt.Color(255, 255, 255));

    jL_Tittle2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
    jL_Tittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jL_Tittle2.setText("THÔNG TIN CÁ NHÂN");

    jL_ID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jL_ID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jL_ID.setText("Mã nhân viên:");

    jT_ID.setEditable(false);

    jL_Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jL_Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jL_Name.setText("Tên nhân viên:");

    jT_Name.setEditable(false);

    jL_Date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jL_Date.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jL_Date.setText("Ngày sinh:");

    jDate_Date.setDateFormatString("yyyy-MM-dd");
    jDate_Date.setEnabled(false);
    jDate_Date.setMinSelectableDate(new java.util.Date(-62135791109000L));

    jL_Sex.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jL_Sex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jL_Sex.setText("Giới tính:");

    jC_Sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NAM", "NỮ" }));
    jC_Sex.setEnabled(false);

    jL_Class.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jL_Class.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jL_Class.setText("Chức vụ:");

    jC_Class.setEnabled(false);

    jL_Add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jL_Add.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jL_Add.setText("Địa chỉ:");

    jT_Add.setEditable(false);

    jL_Pass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jL_Pass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jL_Pass.setText("Mật khẩu:");

    jPass_Pass.setEditable(false);

    jL_Phone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jL_Phone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jL_Phone.setText("Số điện thoại:");

    jT_Phone.setEditable(false);

    btn_Edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btn_Edit.setText("Sửa thông tin");
    btn_Edit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_EditActionPerformed(evt);
        }
    });

    btn_Save.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btn_Save.setText("Lưu thông tin");
    btn_Save.setEnabled(false);
    btn_Save.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_SaveActionPerformed(evt);
        }
    });

    jTable_LichTruc.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Ca trực", "Ngày làm", "Giờ bắt đầu", "Giờ kết thúc"
        }
    ));
    jScrollPane1.setViewportView(jTable_LichTruc);

    jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel19.setText("Lịch trực:");

    javax.swing.GroupLayout jPan_InfLayout = new javax.swing.GroupLayout(jPan_Inf);
    jPan_Inf.setLayout(jPan_InfLayout);
    jPan_InfLayout.setHorizontalGroup(
        jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPan_InfLayout.createSequentialGroup()
            .addGap(23, 23, 23)
            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPan_InfLayout.createSequentialGroup()
                    .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPan_InfLayout.createSequentialGroup()
                            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPan_InfLayout.createSequentialGroup()
                                    .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jL_Date)
                                        .addComponent(jL_Sex))
                                    .addGap(36, 36, 36))
                                .addGroup(jPan_InfLayout.createSequentialGroup()
                                    .addComponent(jL_Name)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(jPan_InfLayout.createSequentialGroup()
                                    .addComponent(jL_ID)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jT_Name)
                                    .addComponent(jT_ID)
                                    .addComponent(jDate_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jC_Sex, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(47, 47, 47)
                            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPan_InfLayout.createSequentialGroup()
                                    .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jL_Pass)
                                        .addComponent(jL_Class)
                                        .addComponent(jL_Add))
                                    .addGap(43, 43, 43)
                                    .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jC_Class, javax.swing.GroupLayout.Alignment.LEADING, 0, 203, Short.MAX_VALUE)
                                        .addComponent(jT_Add)
                                        .addComponent(jPass_Pass)))
                                .addGroup(jPan_InfLayout.createSequentialGroup()
                                    .addComponent(jL_Phone)
                                    .addGap(18, 18, 18)
                                    .addComponent(jT_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPan_InfLayout.createSequentialGroup()
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jL_Tittle2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(13, Short.MAX_VALUE))
                .addGroup(jPan_InfLayout.createSequentialGroup()
                    .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))))
    );
    jPan_InfLayout.setVerticalGroup(
        jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPan_InfLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jL_Tittle2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jL_ID)
                .addComponent(jT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jL_Pass)
                .addComponent(jPass_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(23, 23, 23)
            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jL_Name)
                .addComponent(jT_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jL_Class)
                .addComponent(jC_Class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(24, 24, 24)
            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jL_Date)
                .addComponent(jDate_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_Add)
                    .addComponent(jT_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(21, 21, 21)
            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jC_Sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jL_Phone)
                .addComponent(jT_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jL_Sex))
            .addGap(31, 31, 31)
            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
            .addComponent(jLabel19)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    Connection ketNoi = KetNoiCSDL.ketNoi();
    String sql = "select TEN_CHUC_VU from CHUC_VU";
    try{
        PreparedStatement pr = ketNoi.prepareStatement(sql);
        ResultSet rs = pr.executeQuery();
        while (rs.next()){
            jC_Class.addItem(rs.getString("TEN_CHUC_VU"));
        }
        rs.close();
        pr.close();
        ketNoi.close();
    }
    catch (Exception e){
        e.printStackTrace();
    }

    jTable_NV.addTab("Thông Tin Cá Nhân", jPan_Inf);

    btn_DangXuat.setBackground(new java.awt.Color(255, 255, 255));
    btn_DangXuat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    btn_DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_exit_24px_1.png"))); // NOI18N
    btn_DangXuat.setText("Đăng xuất");
    btn_DangXuat.setPreferredSize(new java.awt.Dimension(123, 43));
    btn_DangXuat.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_DangXuatActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(40, 40, 40)
            .addComponent(jL_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jL_Tittle)
            .addGap(155, 155, 155)
            .addComponent(jL_Icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
            .addComponent(btn_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(32, 32, 32))
        .addComponent(jTable_NV, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jL_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jL_Tittle)
                        .addComponent(jL_Icon2)
                        .addComponent(btn_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(18, 18, 18)
            .addComponent(jTable_NV))
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

    private void btn_XNSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XNSVActionPerformed
        // TODO add your handling code here:
        //lay thong tin sinh vien
        String maSV = jT_MaSV.getText();
        String hoTenSV = jT_HoTen.getText();
        String lop = jT_Lop.getText();

        if (!maSV.equals("") && !hoTenSV.equals("") && !lop.equals("")){
            jL_MaSV.setText(maSV);

            jDi_SinhVien.dispose();
            jDi_XNDKThang.setLocationRelativeTo(null);
            jDi_XNDKThang.setVisible(true);
        }
        else if (maSV.equals("")){
            JOptionPane.showMessageDialog(null, "Mã sinh viên không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else if (hoTenSV.equals("")){
            JOptionPane.showMessageDialog(null, "Họ tên sinh viên không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else if (lop.equals("")){
            JOptionPane.showMessageDialog(null, "Lớp không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_XNSVActionPerformed

    private void btn_HuySVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuySVActionPerformed
        // TODO add your handling code here:
        jDi_SinhVien.dispose();
    }//GEN-LAST:event_btn_HuySVActionPerformed

    private void btn_XacNhanTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XacNhanTienActionPerformed
        // TODO add your handling code here:
        //gan du lieu cho bien:
        //1. lay thong tin sinh vien
        String maSV = jT_MaSV.getText();
        String hoTenSV = jT_HoTen.getText();
        String lop = jT_Lop.getText();
        String queQuan = jT_QueQuan.getText();
        String gioiTinh = (String) jC_GioiTinh.getSelectedItem();
        java.sql.Date namSinh = null;
        try {
            String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgaySV.getDate());
            java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            namSinh = new java.sql.Date(tmp.getTime());
        }
        catch (Exception e) {
        }
        //2. lay thong tin xe
        String bienSo = jT_BienSo.getText();
        String hieuXe = jT_HieuXe.getText();
        String mauXe = jT_MauXe.getText();
        //tinh thoi gian
        String ngayDK = jL_TGDK.getText();
        String ngayHH = jL_TGHetHan.getText();
        java.sql.Date TGDK = null;
        java.sql.Date TGHH = null;
        try {
            java.util.Date tmp1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngayDK);
            java.util.Date tmp2 = new SimpleDateFormat("yyyy-MM-dd").parse(ngayHH);
            TGDK = new java.sql.Date(tmp1.getTime());
            TGHH = new java.sql.Date(tmp2.getTime());
        }
        catch (ParseException ex) {
            System.out.print("");
        }
        String trangThai = "CÒN HẠN";

        //3.chuc nang
        if (QLXe.checkSV(maSV)==0){
            if (QLXe.checkBienSo(bienSo) == 0){
                QLXe.themXe(bienSo, hieuXe, mauXe);
                JOptionPane.showMessageDialog(null, "Thêm thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                QLXe.themSV(maSV, hoTenSV, lop, namSinh, queQuan, gioiTinh);
                QLXe.themVeThang(bienSo, maSV, TGDK, TGHH, trangThai);
            }
            else if (QLXe.checkBienSo(bienSo) == 1){
                JOptionPane.showMessageDialog(null, "Thêm thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                QLXe.themSV(maSV, hoTenSV, lop, namSinh, queQuan, gioiTinh);
                QLXe.themVeThang(bienSo, maSV, TGDK, TGHH, trangThai);
            }
            
        }
        else if (QLXe.checkSV(maSV)==1){
            if (QLXe.checkBienSo(bienSo) == 0){
                QLXe.themXe(bienSo, hieuXe, mauXe);
                JOptionPane.showMessageDialog(null, "Thêm thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                QLXe.themVeThang(bienSo, maSV, TGDK, TGHH, trangThai);
            }
            else if (QLXe.checkBienSo(bienSo) == 1){
                JOptionPane.showMessageDialog(null, "Thêm thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                QLXe.themVeThang(bienSo, maSV, TGDK, TGHH, trangThai);
            }
        }

        //3. dipose
        jDi_XNDKThang.dispose();
    }//GEN-LAST:event_btn_XacNhanTienActionPerformed

    private void btn_HuyTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyTienActionPerformed
        // TODO add your handling code here:
        jDi_XNDKThang.dispose();
        jDi_SinhVien.setLocationRelativeTo(null);
        jDi_SinhVien.setVisible(true);
    }//GEN-LAST:event_btn_HuyTienActionPerformed

    private void btn_XacNhanThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XacNhanThanhTienActionPerformed
        // TODO add your handling code here:
        //lay du lieu de update
        String maLoaiVe = QLXe.layMaLoaiVe((String) jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 0));
        String bienSo = jT_BienSo.getText();
        //tinh khung h ra
        String maKhungGio ="";
        Time gioRa = Time.valueOf(LocalTime.now());
        if (gioRa.getHours() > 17 && maLoaiVe.equals("L")){
            maKhungGio = "T";
        }
        else if (gioRa.getHours() <= 17 && maLoaiVe.equals("L")){
            maKhungGio = "S";
        }
        else if (maLoaiVe.equals("T")){
            maKhungGio = "A";
        }

        //ngay ra, ngay vao
        java.sql.Date ngayRa = null;
        try {
            String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_Ngay.getDate());
            java.util.Date tmp1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            ngayRa = new java.sql.Date(tmp1.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //ma nv ql ra
        String maNV = this.maNV;

        //tien
        int giaTien = Integer.parseInt(jL_Tien1.getText());

        //tinh trang
        String tinhTrang = "ĐÃ LẤY";

        //lay ten khung h
        String tenKhungGio = QLXe.layTenKhungH(maKhungGio);

        //xu li
        if(String.valueOf(jC_Loi.getSelectedItem()).equals("Không")){
            QLXe.capNhatXeXuatBen(bienSo, maKhungGio, ngayRa, gioRa, maNV, giaTien, tinhTrang);
        }
        else{
            QLXe.themSuCo(QLXe.layMaLoi(String.valueOf(jC_Loi.getSelectedItem())), bienSo, ngayRa);
            QLXe.capNhatXeXuatBen(bienSo, maKhungGio, ngayRa, gioRa, maNV, giaTien, tinhTrang);
        }

        layTTXe();
        jDi_ThanhTien.dispose();

    }//GEN-LAST:event_btn_XacNhanThanhTienActionPerformed

    private void btn_HuyThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyThanhTienActionPerformed
        // TODO add your handling code here:
        jDi_ThanhTien.dispose();
    }//GEN-LAST:event_btn_HuyThanhTienActionPerformed

    private void btn_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangXuatActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btn_DangXuatActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        Date date = null;
        //Dat cac bien de lay du lieu trong jTextF
        String maNV = jT_ID.getText();
        String tenNV = jT_Name.getText();
        String gioiTinh = (String) jC_Sex.getSelectedItem();
        String MK = jPass_Pass.getText();
        String chucVu = (String) jC_Class.getSelectedItem();
        String diaChi = jT_Add.getText();
        String SDT = jT_Phone.getText();

        //Chuc nang
        if (maNV.equals("") || tenNV.equals("") || gioiTinh.equals("") || chucVu.equals("") || MK.equals("")){
            JOptionPane.showMessageDialog(null, "Các thông tin: Tên nv, giới tính, chức vụ và mật khẩu không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);

        }
        else{
            java.sql.Date ngaySinh = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_Date.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngaySinh = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
            }
            btn_Edit.setEnabled(true);
            btn_Save.setEnabled(false);

            jT_Name.setEditable(false);
            jDate_Date.setEnabled(false);
            jC_Sex.setEnabled(false);
            jPass_Pass.setEditable(false);
            jT_Add.setEditable(false);
            jT_Phone.setEditable(false);
            JOptionPane.showMessageDialog(null, "Sửa thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);

            thaoTac.suaDN(maNV, MK);
            thaoTac.suaNV(maNV, tenNV, ngaySinh, diaChi, SDT, gioiTinh);
        }
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditActionPerformed
        // TODO add your handling code here:
        btn_Edit.setEnabled(false);
        btn_Save.setEnabled(true);
        jT_Name.setEditable(true);
        jDate_Date.setEnabled(true);
        jC_Sex.setEnabled(true);
        jPass_Pass.setEditable(true);
        jT_Add.setEditable(true);
        jT_Phone.setEditable(true);

    }//GEN-LAST:event_btn_EditActionPerformed

    private void jPan_GuiXeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPan_GuiXeMousePressed
        // TODO add your handling code here:
        btn_Vao.setEnabled(true);
        btn_Ra.setEnabled(false);
        jC_Loi.setEnabled(false);
        jT_BienSo.setText("");
        jT_HieuXe.setText("");
        jT_MauXe.setText("");
    }//GEN-LAST:event_jPan_GuiXeMousePressed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        // TODO add your handling code here:
        jT_BienSo.setText("");
        jT_HieuXe.setText("");
        jT_MauXe.setText("");
        btn_Vao.setEnabled(true);
        btn_Ra.setEnabled(false);
        jC_Loi.setEnabled(false);
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void jT_FindBienSoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_FindBienSoKeyReleased
        // TODO add your handling code here:
        String bienSo = jT_FindBienSo.getText();
        if (bienSo.equals("")){
            layTTXe();
        }
        else if (!bienSo.equals("")){
            layTTXeTimKiemSo(bienSo);
        }
    }//GEN-LAST:event_jT_FindBienSoKeyReleased

    private void jT_FindHieuXeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_FindHieuXeKeyReleased
        // TODO add your handling code here:
        String hieuXe = jT_FindHieuXe.getText();
        if (hieuXe.equals("")){
            layTTXe();
        }
        else if (!hieuXe.equals("")){
            layTTXeTimKiemHieu(hieuXe);
        }
    }//GEN-LAST:event_jT_FindHieuXeKeyReleased

    private void btn_ThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThangActionPerformed
        // TODO add your handling code here:
        //gan du lieu
        String bienSo = jT_BienSo.getText();
        String hieuXe = jT_HieuXe.getText();
        String mauXe = jT_MauXe.getText();
        String gioVao = jT_Time.getText();
        String maLoaiVe = "T";
        String maKhungGio = "A";
        String giaTien = String.valueOf(QLXe.layTien(maLoaiVe, maKhungGio));

        String ngayDK = new SimpleDateFormat("yyyy-MM-dd").format(jDate_Ngay.getDate());
        //tinh thang tiep theo
        Calendar cal = GregorianCalendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date tmp = null;
        try {
            tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngayDK);
        }
        catch (Exception e) {
        }
        cal.setTime(tmp);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
        String ngayHH = df.format(cal.getTime());
        //

        if (!bienSo.equals("") && (QLXe.checkTinhTrangVeThang(bienSo).equals("HẾT HẠN") || QLXe.checkTinhTrangVeThang(bienSo).equals(""))){
            //hien thong tin nhap dialog sv
            jL_SoXe.setText(bienSo);
            jL_HieuXe1.setText(hieuXe);
            jL_MauXeT.setText(mauXe);
            jL_TGDK.setText(ngayDK);
            jL_TGHetHan.setText(ngayHH);
            jL_Tien.setText(giaTien);
            jDi_SinhVien.setLocationRelativeTo(null);
            jDi_SinhVien.setVisible(true);

        }
        else if (QLXe.checkTinhTrangVeThang(bienSo).equals("CÒN HẠN")){
            JOptionPane.showMessageDialog(null, "Xe vẫn còn hạn, không thể thêm được nữa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Cac thông tin về xe không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThangActionPerformed

    private void btn_RaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RaActionPerformed
        // TODO add your handling code here:
        btn_Ra.setEnabled(false);
        btn_Vao.setEnabled(true);

        //kiem tra ve
        String maVe = JOptionPane.showInputDialog("Nhập mã vé: ");
        String maLoaiVe = QLXe.layMaLoaiVe(maVe);
        //tinh khung h ra
        String maKhungGio ="";

        //kiem tra h ra
        Time gioRa = Time.valueOf(LocalTime.now());

        if (gioRa.getHours() > 17 && maLoaiVe.equals("L")){
            maKhungGio = "T";
        }
        else if (gioRa.getHours() <= 17 && maLoaiVe.equals("L")){
            maKhungGio = "S";
        }
        else if (maLoaiVe.equals("T")){
            maKhungGio = "A";
        }

        //tinh ngay ra
        java.sql.Date ngayRa = null;
        java.sql.Date ngayVao = null;
        try {
            String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_Ngay.getDate());
            String ngay2 = new SimpleDateFormat("yyyy-MM-dd").format(jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 5));
            java.util.Date tmp1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            java.util.Date tmp2 = new SimpleDateFormat("yyyy-MM-dd").parse(ngay2);
            ngayVao = new java.sql.Date(tmp2.getTime());
            ngayRa = new java.sql.Date(tmp1.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        long ngayVaoRa = (ngayRa.getTime()- ngayVao.getTime())/(24*3600*1000);

        //xu li
        int tienLoi = 0;
        if (String.valueOf(jC_Loi.getSelectedItem()).equals("Không")) {
            tienLoi = 0;
        }
        else {
            tienLoi = QLXe.layTienLoi((String) jC_Loi.getSelectedItem());
        }

        int tinhTien = 0;
        if (!maVe.equals(jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 0))){
            JOptionPane.showMessageDialog(null, "Sai vé xe.", "Thông báo", JOptionPane.WARNING_MESSAGE);

        }
        else if (maVe.equals(jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 0))){
            if (maLoaiVe.equals("L")){
                if(ngayVaoRa == 0){
                    tinhTien = QLXe.layTien(maLoaiVe, maKhungGio) + tienLoi;
                }
                else if (ngayVaoRa != 0){
                    if (maKhungGio.equals("S")){
                        tinhTien = (int) (ngayVaoRa*QLXe.layTien(maLoaiVe, "T") + QLXe.layTien(maLoaiVe, "S") + tienLoi);
                    }
                    else  if (maKhungGio.equals("T")){
                        tinhTien = (int) (ngayVaoRa*QLXe.layTien(maLoaiVe, "T") + QLXe.layTien(maLoaiVe, "T") + tienLoi);
                    }
                }
            }
            else if (maLoaiVe.equals("T")){
                tinhTien = tienLoi;
            }

            //them cac thong tin vao bang xac nhan tinh tien
            jL_SoXe1.setText(jT_BienSo.getText());
            jL_HieuXe2.setText(jT_HieuXe.getText());
            jL_MauXeT1.setText(jT_MauXe.getText());
            jL_NgayVao.setText(String.valueOf(ngayVao));
            jL_GioVao.setText(String.valueOf(jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 6)));
            jL_Tien1.setText(String.valueOf(tinhTien));
            jL_NgayRA.setText(String.valueOf(ngayRa));
            jL_GioRa.setText(String.valueOf(gioRa));
            jL_Loi.setText((String) jC_Loi.getSelectedItem());

            jDi_ThanhTien.setLocationRelativeTo(null);
            jDi_ThanhTien.setVisible(true);
        }
    }//GEN-LAST:event_btn_RaActionPerformed

    private void btn_VaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VaoActionPerformed
        // TODO add your handling code here:
        //gan du lieu
        String bienSo = jT_BienSo.getText();
        String maVe = QLXe.maXe();
        String hieuXe = jT_HieuXe.getText();
        String mauXe = jT_MauXe.getText();
        Time gioVao = Time.valueOf(LocalTime.now());

        java.sql.Date ngayVao = null;
        try {
            String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_Ngay.getDate());
            java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            ngayVao = new java.sql.Date(tmp.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if(bienSo.equals("")){
            JOptionPane.showMessageDialog(null, "Xin nhập mã vé và biển số xe.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else if(QLXe.checkTinhTrangVe(bienSo).equals("CHƯA LẤY")){
            JOptionPane.showMessageDialog(null, "Xe này vần còn trong bãi, không thể thêm được.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else if(QLXe.checkBienSo(bienSo) == 1){
            if(QLXe.checkBienSoInVX(bienSo) == 1){
                if(QLXe.checkTinhTrangVe(bienSo).equals("ĐÃ LẤY")){
                    //Ve luot
                    if (QLXe.checkTinhTrangVeThang(bienSo).equals("") || QLXe.checkTinhTrangVeThang(bienSo).equals("HẾT HẠN")){
                        String maLoaiVe = "L";
                        QLXe.themVeXe(maVe, maLoaiVe, bienSo, ngayVao, gioVao, this.maNV, "CHƯA LẤY");

                        //set lai cai jT
                        jT_BienSo.setText("");
                        jT_HieuXe.setText("");
                        jT_MauXe.setText("");

                        this.layTTXe();
                    }
                    //Ve thang
                    else if (QLXe.checkTinhTrangVeThang(bienSo).equals("CÒN HẠN")){
                        String maLoaiVe = "T";
                        QLXe.themVeXe(maVe, maLoaiVe, bienSo, ngayVao, gioVao, this.maNV, "CHƯA LẤY");

                        //set lai cai jT
                        jT_BienSo.setText("");
                        jT_HieuXe.setText("");
                        jT_MauXe.setText("");

                        this.layTTXe();
                    }
                }
                else if (QLXe.checkTinhTrangVe(bienSo).equals("CHƯA LẤY")){
                    JOptionPane.showMessageDialog(null, "Xe này vần còn trong bãi, không thể thêm được.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
            else if(QLXe.checkBienSoInVX(bienSo) == 0){
                if (QLXe.checkTinhTrangVeThang(bienSo).equals("") || QLXe.checkTinhTrangVeThang(bienSo).equals("HẾT HẠN")){
                    String maLoaiVe = "L";
                    QLXe.themVeXe(maVe, maLoaiVe, bienSo, ngayVao, gioVao, this.maNV, "CHƯA LẤY");

                    //set lai cai jT
                    jT_BienSo.setText("");
                    jT_HieuXe.setText("");
                    jT_MauXe.setText("");

                    this.layTTXe();
                }
                //Ve thang
                else if (QLXe.checkTinhTrangVeThang(bienSo).equals("CÒN HẠN")){
                    String maLoaiVe = "T";
                    QLXe.themVeXe(maVe, maLoaiVe, bienSo, ngayVao, gioVao, this.maNV, "CHƯA LẤY");

                    //set lai cai jT
                    jT_BienSo.setText("");
                    jT_HieuXe.setText("");
                    jT_MauXe.setText("");

                    this.layTTXe();
                }
            }

        }
        else if (QLXe.checkBienSo(bienSo) == 0){
            //them
            String maLoaiVe = "L";
            QLXe.themXe(bienSo, hieuXe, mauXe);
            QLXe.themVeXe(maVe, maLoaiVe, bienSo, ngayVao, gioVao, this.maNV, "CHƯA LẤY");

            //set lai cai jT
            jT_BienSo.setText("");
            jT_HieuXe.setText("");
            jT_MauXe.setText("");
            this.layTTXe();
        }
    }//GEN-LAST:event_btn_VaoActionPerformed

    private void jTable_GuiXeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_GuiXeMousePressed
        // TODO add your handling code here:
        btn_Ra.setEnabled(true);
        btn_Vao.setEnabled(false);
        jC_Loi.setEnabled(true);

        String bienSo = (String) jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 2);
        String maVe = (String) jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 0);
        String hieuXe = (String) jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 3);
        String mauXe = (String) jTable_GuiXe.getValueAt(jTable_GuiXe.getSelectedRow(), 4);

        jT_BienSo.setText(bienSo);
        jT_HieuXe.setText(hieuXe);
        jT_MauXe.setText(mauXe);
    }//GEN-LAST:event_jTable_GuiXeMousePressed

    private void jT_BienSoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_BienSoKeyPressed
        // TODO add your handling code here:
        String bienSo = jT_BienSo.getText();
        if (evt.getKeyCode() == evt.VK_ENTER){
            if (QLXe.checkBienSo(bienSo) == 1){
                jT_HieuXe.setEditable(false);
                jT_MauXe.setEditable(false);
                jT_HieuXe.setText(QLXe.layHieuXe(bienSo));
                jT_MauXe.setText(QLXe.layMauXe(bienSo));
            }
            else if (QLXe.checkBienSo(bienSo) == 0){
                jT_HieuXe.setText("");
                jT_MauXe.setText("");
                jT_HieuXe.setEditable(true);
                jT_MauXe.setEditable(true);
            }
        }
    }//GEN-LAST:event_jT_BienSoKeyPressed

    private void jL_Icon2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jL_Icon2MousePressed
        // TODO add your handling code here:
        GioiThieu formGT = new GioiThieu();
        formGT.setLocationRelativeTo(null);
        formGT.setVisible(true);
    }//GEN-LAST:event_jL_Icon2MousePressed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DangXuat;
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_FindName;
    private javax.swing.JButton btn_FindVe;
    private javax.swing.JButton btn_Huy;
    private javax.swing.JButton btn_HuySV;
    private javax.swing.JButton btn_HuyThanhTien;
    private javax.swing.JButton btn_HuyTien;
    private javax.swing.JButton btn_Ra;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Thang;
    private javax.swing.JButton btn_Vao;
    private javax.swing.JButton btn_XNSV;
    private javax.swing.JButton btn_XacNhanThanhTien;
    private javax.swing.JButton btn_XacNhanTien;
    private javax.swing.JComboBox<String> jC_Class;
    private javax.swing.JComboBox<String> jC_GioiTinh;
    private javax.swing.JComboBox<String> jC_Loi;
    private javax.swing.JComboBox<String> jC_Sex;
    private com.toedter.calendar.JDateChooser jDate_Date;
    private com.toedter.calendar.JDateChooser jDate_Ngay;
    private com.toedter.calendar.JDateChooser jDate_NgaySV;
    private javax.swing.JDialog jDi_SinhVien;
    private javax.swing.JDialog jDi_ThanhTien;
    private javax.swing.JDialog jDi_XNDKThang;
    private javax.swing.JLabel jL_Add;
    private javax.swing.JLabel jL_BienSo;
    private javax.swing.JLabel jL_Class;
    private javax.swing.JLabel jL_Date;
    private javax.swing.JLabel jL_GioRa;
    private javax.swing.JLabel jL_GioVao;
    private javax.swing.JLabel jL_HieuXe1;
    private javax.swing.JLabel jL_HieuXe2;
    private javax.swing.JLabel jL_Hieuxe;
    private javax.swing.JLabel jL_ID;
    private javax.swing.JLabel jL_Icon;
    private javax.swing.JLabel jL_Icon2;
    private javax.swing.JLabel jL_Loi;
    private javax.swing.JLabel jL_MaSV;
    private javax.swing.JLabel jL_MauXeT;
    private javax.swing.JLabel jL_MauXeT1;
    private javax.swing.JLabel jL_Mauxe;
    private javax.swing.JLabel jL_Mauxe1;
    private javax.swing.JLabel jL_Name;
    private javax.swing.JLabel jL_Ngay;
    private javax.swing.JLabel jL_NgayRA;
    private javax.swing.JLabel jL_NgayVao;
    private javax.swing.JLabel jL_Pass;
    private javax.swing.JLabel jL_Phone;
    private javax.swing.JLabel jL_Sex;
    private javax.swing.JLabel jL_SoXe;
    private javax.swing.JLabel jL_SoXe1;
    private javax.swing.JLabel jL_TGDK;
    private javax.swing.JLabel jL_TGHetHan;
    private javax.swing.JLabel jL_Tien;
    private javax.swing.JLabel jL_Tien1;
    private javax.swing.JLabel jL_Time;
    private javax.swing.JLabel jL_Tittle;
    private javax.swing.JLabel jL_Tittle2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPan_GuiXe;
    private javax.swing.JPanel jPan_Inf;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPass_Pass;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jT_Add;
    private javax.swing.JTextField jT_BienSo;
    private javax.swing.JTextField jT_FindBienSo;
    private javax.swing.JTextField jT_FindHieuXe;
    private javax.swing.JTextField jT_HieuXe;
    private javax.swing.JTextField jT_HoTen;
    private javax.swing.JTextField jT_ID;
    private javax.swing.JTextField jT_Lop;
    private javax.swing.JTextField jT_MaSV;
    private javax.swing.JTextField jT_MauXe;
    private javax.swing.JTextField jT_Name;
    private javax.swing.JTextField jT_Phone;
    private javax.swing.JTextField jT_QueQuan;
    private javax.swing.JTextField jT_Time;
    private javax.swing.JTable jTable_GuiXe;
    private javax.swing.JTable jTable_LichTruc;
    private javax.swing.JTabbedPane jTable_NV;
    // End of variables declaration//GEN-END:variables
}
