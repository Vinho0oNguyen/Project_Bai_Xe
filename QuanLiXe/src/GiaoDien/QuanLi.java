
package GiaoDien;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import KetNoiSQL.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Service.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class QuanLi extends javax.swing.JFrame {
    SeQL thaoTac = new SeQL();
    SeXe QLXe = new SeXe();
    private String maNV;
    
    public QuanLi(String maNV) {
        this.maNV = maNV;
        initComponents();
        this.setTitle("Quản lí");
        this.setLocationRelativeTo(null);
        capNhatTTVeThang();
        layTT();
        layTTXe();
        layKhungGio();
        layTTCaTruc();
    }

    private QuanLi() {
        
    }

    
    //Lay thong tin nhan vien===================================================
    public void layTT(){
        DefaultTableModel dtm = (DefaultTableModel) jTable_NhanVien.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT NV.MA_NV, NV.HO_TEN, NV.NAM_SINH, CV.TEN_CHUC_VU, NV.DIA_CHI, NV.SDT, NV.GIOI_TINH, DN.MAT_KHAU"
                   + " FROM NHAN_VIEN AS NV, DANG_NHAP AS DN, CHUC_VU AS CV" 
                   + " WHERE NV.MA_NV = DN.TAI_KHOANG AND DN.MA_CHUC_VU = CV.MA_CHUC_VU";
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("MA_NV"));
                vt.add(rs.getString("HO_TEN"));
                vt.add(rs.getDate("NAM_SINH"));
                vt.add(rs.getString("TEN_CHUC_VU"));
                vt.add(rs.getString("DIA_CHI"));
                vt.add(rs.getString("SDT"));
                vt.add(rs.getString("GIOI_TINH"));
                vt.add(rs.getString("MAT_KHAU"));
                dtm.addRow(vt);
            }
            jTable_NhanVien.setModel(dtm);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
   }

    //lay thong tin nhung xe con gui============================================
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
    
    //lay thong tin nhung xe trong o tim kiem hieu xe===========================
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
    
    //lay thong tin nhung xe trong o tim kiem hieu xe===========================
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
    //lay thong tin nhung xe trong o tim kiem DOANH THU=========================
    public void timKiemXe(String bienSo){
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_TKDoanhThu.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql =  "SELECT LV.TEN_LOAI, VX.BIEN_SO_XE, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.GIA_TIEN, VX.TINH_TRANG" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.BIEN_SO_XE LIKE '%" + bienSo + "%'";
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("BIEN_SO_XE"));
                vt.add(rs.getString("TEN_LOAI"));
                vt.add(rs.getDate("NGAY_VAO"));
                vt.add(rs.getTime("GIO_VAO"));
                vt.add(rs.getDate("NGAY_RA"));
                vt.add(rs.getTime("GIO_RA"));
                vt.add(rs.getInt("GIA_TIEN"));
                vt.add(rs.getString("TINH_TRANG"));
                dtm1.addRow(vt);
            }
            jTable_TKDoanhThu.setModel(dtm1);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Lay nhung xe dang ky thang da het han=====================================
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
    
    //Lay thong ke==============================================================
    //lay thong tin xe cho doanh thu
    public void layDoanhThu(String sql){
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_TKDoanhThu.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("BIEN_SO_XE"));
                vt.add(rs.getString("TEN_LOAI"));
                vt.add(rs.getDate("NGAY_VAO"));
                vt.add(rs.getTime("GIO_VAO"));
                vt.add(rs.getDate("NGAY_RA"));
                vt.add(rs.getTime("GIO_RA"));
                vt.add(rs.getInt("GIA_TIEN"));
                dtm1.addRow(vt);
            }
            jTable_TKDoanhThu.setModel(dtm1);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //lay cac xe dang gui trong bang Hien Tai
    public void layHT(String sql){
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_TKHienTai.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("BIEN_SO_XE"));
                vt.add(rs.getString("TEN_LOAI"));
                vt.add(rs.getDate("NGAY_VAO"));
                vt.add(rs.getTime("GIO_VAO"));
                vt.add(rs.getDate("NGAY_RA"));
                vt.add(rs.getTime("GIO_RA"));
                vt.add(rs.getString("MA_NV_VAO"));
                vt.add(rs.getString("MA_NV_RA"));
                dtm1.addRow(vt);
            }
            jTable_TKHienTai.setModel(dtm1);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Thong ke ve thang
    public void layVeThang(String sql){
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_TKVeThang.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("BIEN_SO_XE"));
                vt.add(rs.getString("MA_SINH_VIEN"));
                vt.add(rs.getDate("NGAY_LAM_VE"));
                vt.add(rs.getDate("NGAY_HET_HAN"));
                dtm1.addRow(vt);
            }
            jTable_TKVeThang.setModel(dtm1);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Thong ke su co
    public void laySuCo(String sql){
        DefaultTableModel dtm1 = (DefaultTableModel) jTable_TKSuCo.getModel();
        dtm1.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("BIEN_SO_XE"));
                vt.add(rs.getString("TEN_SU_CO"));
                vt.add(rs.getDate("NGAY_XAY_RA"));
                vt.add(rs.getInt("XU_PHAT"));
                dtm1.addRow(vt);
            }
            jTable_TKSuCo.setModel(dtm1);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    //lay khung h gui xe
    public void layKhungGio(){
        DefaultTableModel dtm = (DefaultTableModel) jTable_KhungGio.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select LV.TEN_LOAI, KG.TEN_KHUNG_GIO, KG.GIO_BAT_DAU, KG.GIO_KET_THUC, GT.GIA_TIEN\n" +
                    "from LOAI_VE as LV, GIA_TIEN as GT, KHUNG_GIO as KG\n" +
                    "where LV.MA_LOAI_VE = GT.MA_LOAI_VE AND GT.MA_KHUNG_GIO = KG.MA_KHUNG_GIO";
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("TEN_LOAI"));
                vt.add(rs.getString("TEN_KHUNG_GIO"));
                vt.add(rs.getTime("GIO_BAT_DAU"));
                vt.add(rs.getTime("GIO_KET_THUC"));
                vt.add(rs.getInt("GIA_TIEN"));
                dtm.addRow(vt);
            }
            jTable_KhungGio.setModel(dtm);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
   }
    
    //lay thong tin ca truc
    public void layTTCaTruc(){
        DefaultTableModel dtm = (DefaultTableModel) jTable_PhanCongTruc.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select CT.TEN_CA, NV.MA_NV, NV.HO_TEN, PC.NGAY_LAM, CT.GIO_BAT_DAU, CT.GIO_KET_THUC\n" +
                    "from CA_TRUC as CT, PHAN_CONG_TRUC as PC, NHAN_VIEN as NV\n" +
                    "where NV.MA_NV = PC.MA_NHAN_VIEN AND PC.MA_CA = CT.MA_CA";
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("TEN_CA"));
                vt.add(rs.getString("MA_NV"));
                vt.add(rs.getString("HO_TEN"));
                vt.add(rs.getDate("NGAY_LAM"));
                vt.add(rs.getTime("GIO_BAT_DAU"));
                vt.add(rs.getTime("GIO_KET_THUC"));
                dtm.addRow(vt);
            }
            jTable_PhanCongTruc.setModel(dtm);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //lay thong tin ca truc theo yc loc
    public void layTTCaTrucTheoYC(String sql){
        DefaultTableModel dtm = (DefaultTableModel) jTable_PhanCongTruc.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = KetNoiCSDL.ketNoi();
        Vector vt;
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vt = new Vector();
                vt.add(rs.getString("TEN_CA"));
                vt.add(rs.getString("MA_NV"));
                vt.add(rs.getString("HO_TEN"));
                vt.add(rs.getDate("NGAY_LAM"));
                vt.add(rs.getTime("GIO_BAT_DAU"));
                vt.add(rs.getTime("GIO_KET_THUC"));
                dtm.addRow(vt);
            }
            jTable_PhanCongTruc.setModel(dtm);
            rs.close();
            pr.close();
            ketNoi.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //xuat data ra excel
    //mo file
    public void openFile(String file){
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jT_DiaChi = new javax.swing.JTextField();
        jL_Pass = new javax.swing.JLabel();
        jPass_Pass = new javax.swing.JPasswordField();
        jL_Phone = new javax.swing.JLabel();
        jT_Phone = new javax.swing.JTextField();
        btn_Edit = new javax.swing.JButton();
        btn_SaveEdit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_NhanVien = new javax.swing.JTable();
        btn_ThemNV = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_SaveAdd = new javax.swing.JButton();
        btn_HuyBo = new javax.swing.JButton();
        jPan_PhanCong = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable_PhanCongTruc = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jC_CaTruc = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jT_MaNVTruc = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jT_TenNVTruc = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jDate_NgayTruc = new com.toedter.calendar.JDateChooser();
        btn_ThemCaTruc = new javax.swing.JButton();
        btn_XoaCaTruc = new javax.swing.JButton();
        btn_XacNhanThemCT = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        btn_HuyThaoTac_CT = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jC_CaTruc1 = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        jT_CT_BD = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jT_CT_KT = new javax.swing.JTextField();
        btn_SuaCaTruc1 = new javax.swing.JButton();
        btn_LuuCaTruc1 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jC_LocCaTruc = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jC_LocNgayLam = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        btn_LocTheoYC = new javax.swing.JButton();
        jPan_ChinhSuaTTVe = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_KhungGio = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jT_GioBD = new javax.swing.JTextField();
        jT_GioKT = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jT_TienKhungGio = new javax.swing.JTextField();
        jT_KhungGio = new javax.swing.JTextField();
        btn_HuyDatLai = new javax.swing.JButton();
        btn_DatLaiVe = new javax.swing.JButton();
        btn_LuuVe = new javax.swing.JButton();
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
        jPan_ThongKe = new javax.swing.JPanel();
        btn_DoanhThu = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jP_DoanhThu = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jL_TongTienLuot = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_TKDoanhThu = new javax.swing.JTable();
        jL_TongTienSC = new javax.swing.JLabel();
        jL_TongTien = new javax.swing.JLabel();
        jT_DT_TimXe = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jL_TongTienThang = new javax.swing.JLabel();
        jP_HienTai = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jL_ThongKeTongSoXe = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jL_ThongKeXeChuaLay = new javax.swing.JLabel();
        jL_ThongKeXeDaLay = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_TKHienTai = new javax.swing.JTable();
        jT_HT_TimKiem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jC_TinhTrangGui = new javax.swing.JComboBox<>();
        jP_VeThang = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        jC_TinhTrangDKT = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jL_XeCH = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jL_XeHH = new javax.swing.JLabel();
        jL_XeDKT = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_TKVeThang = new javax.swing.JTable();
        jP_SuCo = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        jLabel56 = new javax.swing.JLabel();
        jC_SuCo = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jL_TongSC = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_TKSuCo = new javax.swing.JTable();
        jL_SuCo = new javax.swing.JLabel();
        jL_TienPaht1 = new javax.swing.JLabel();
        btn_HienTai = new javax.swing.JButton();
        btn_VeThang = new javax.swing.JButton();
        btn_SuCo = new javax.swing.JButton();
        jL_ThongKe = new javax.swing.JLabel();
        jC_ThongKe = new javax.swing.JComboBox<>();
        jDate_NgayThongKe = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jC_Thang = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jC_Quy = new javax.swing.JComboBox<>();
        jSeparator27 = new javax.swing.JSeparator();
        btn_DT_EXtoExcel = new javax.swing.JButton();
        btn_DangXuat = new javax.swing.JButton();

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

        jT_MaSV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jT_MaSVKeyPressed(evt);
            }
        });

        jT_HoTen.setEditable(false);

        jT_Lop.setEditable(false);

        jT_QueQuan.setEditable(false);

        jC_GioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jC_GioiTinh.setEnabled(false);

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

        jDate_NgaySV.setEnabled(false);

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

        jDi_ThanhTien.setMinimumSize(new java.awt.Dimension(320, 390));
        jDi_ThanhTien.getContentPane().setLayout(null);

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));
        jPanel7.setMinimumSize(new java.awt.Dimension(320, 390));
        jPanel7.setPreferredSize(new java.awt.Dimension(320, 390));

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

        jTable_NV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPan_Inf.setBackground(new java.awt.Color(255, 255, 255));
        jPan_Inf.setPreferredSize(new java.awt.Dimension(705, 499));

        jL_Tittle2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jL_Tittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Tittle2.setText("THÔNG TIN CÁ NHÂN");

        jL_ID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_ID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_ID.setText("Mã nhân viên:");

        jL_Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Name.setText("Tên nhân viên:");

        jL_Date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Date.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Date.setText("Ngày sinh:");

        jDate_Date.setBackground(new java.awt.Color(255, 255, 255));
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

        jL_Pass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Pass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Pass.setText("Mật khẩu:");

        jL_Phone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Phone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Phone.setText("Số điện thoại:");

        btn_Edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Edit.setText("Sửa thông tin");
        btn_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditActionPerformed(evt);
            }
        });

        btn_SaveEdit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_SaveEdit.setText("Lưu thông tin sửa");
        btn_SaveEdit.setEnabled(false);
        btn_SaveEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveEditActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Năm sinh", "Chức vụ", "Địa chỉ", "SĐT", "Giới tính", "Mật khẩu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable_NhanVienMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_NhanVien);
        if (jTable_NhanVien.getColumnModel().getColumnCount() > 0) {
            jTable_NhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable_NhanVien.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable_NhanVien.getColumnModel().getColumn(3).setPreferredWidth(150);
        }
        jTable_NhanVien.setAutoResizeMode(jTable_NhanVien.AUTO_RESIZE_OFF);

        btn_ThemNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_ThemNV.setText("Thêm thông tin");
        btn_ThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNVActionPerformed(evt);
            }
        });

        btn_xoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_SaveAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_SaveAdd.setText("Lưu thông tin thêm");
        btn_SaveAdd.setEnabled(false);
        btn_SaveAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveAddActionPerformed(evt);
            }
        });

        btn_HuyBo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_HuyBo.setText("Hủy bỏ");
        btn_HuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyBoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPan_InfLayout = new javax.swing.GroupLayout(jPan_Inf);
        jPan_Inf.setLayout(jPan_InfLayout);
        jPan_InfLayout.setHorizontalGroup(
            jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_InfLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPan_InfLayout.createSequentialGroup()
                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPan_InfLayout.createSequentialGroup()
                                .addComponent(jL_Sex, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPan_InfLayout.createSequentialGroup()
                                .addComponent(jL_Date)
                                .addGap(36, 36, 36)))
                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPan_InfLayout.createSequentialGroup()
                                .addComponent(jDate_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jL_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jT_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPan_InfLayout.createSequentialGroup()
                                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPan_InfLayout.createSequentialGroup()
                                        .addComponent(jC_Sex, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(jL_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPan_InfLayout.createSequentialGroup()
                                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_SaveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(69, 69, 69)))
                                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jT_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btn_HuyBo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_xoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPan_InfLayout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addGap(18, 18, 18)
                        .addComponent(jL_Tittle2)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPan_InfLayout.createSequentialGroup()
                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPan_InfLayout.createSequentialGroup()
                                .addComponent(jL_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jL_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPan_InfLayout.createSequentialGroup()
                                .addComponent(jL_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jT_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jL_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jC_Class, 0, 219, Short.MAX_VALUE)
                            .addComponent(jPass_Pass)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPan_InfLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ThemNV)
                    .addComponent(btn_SaveAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(507, 507, 507))
        );
        jPan_InfLayout.setVerticalGroup(
            jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_InfLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jL_Tittle2)
                    .addGroup(jPan_InfLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jL_ID)
                    .addComponent(jT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_Pass)
                    .addComponent(jPass_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jL_Name)
                    .addComponent(jT_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_Class)
                    .addComponent(jC_Class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDate_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jT_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPan_InfLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jL_Date)
                            .addComponent(jL_Add))))
                .addGap(24, 24, 24)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jC_Sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jL_Sex))
                    .addComponent(jL_Phone)
                    .addComponent(jT_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_HuyBo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btn_SaveEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_SaveAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        jT_ID.setEditable(false);
        jT_Name.setEditable(false);
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
        jT_DiaChi.setEditable(false);
        jPass_Pass.setEditable(false);
        jT_Phone.setEditable(false);

        jTable_NV.addTab("Thông Tin Cá Nhân", jPan_Inf);

        jPan_PhanCong.setBackground(new java.awt.Color(255, 255, 255));

        jTable_PhanCongTruc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ca trực", "Mã nhân viên", "Tên nhân viên", "Ngày trực", "Giờ bắt đầu", "Giờ kết thúc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_PhanCongTruc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable_PhanCongTrucMousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(jTable_PhanCongTruc);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("Ca trực:");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("Mã nhân viên:");

        jT_MaNVTruc.setEditable(false);
        jT_MaNVTruc.setToolTipText("Enter để xem tên nhân viên");
        jT_MaNVTruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jT_MaNVTrucKeyPressed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("Tên nhân viên:");

        jT_TenNVTruc.setEditable(false);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Ngày trực:");

        jDate_NgayTruc.setDateFormatString("yyyy-MM-dd");
        jDate_NgayTruc.setEnabled(false);

        btn_ThemCaTruc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_ThemCaTruc.setText("Thêm");
        btn_ThemCaTruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemCaTrucActionPerformed(evt);
            }
        });

        btn_XoaCaTruc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_XoaCaTruc.setText("Xóa");
        btn_XoaCaTruc.setEnabled(false);
        btn_XoaCaTruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaCaTrucActionPerformed(evt);
            }
        });

        btn_XacNhanThemCT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_XacNhanThemCT.setText("Xác nhận thêm");
        btn_XacNhanThemCT.setEnabled(false);
        btn_XacNhanThemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XacNhanThemCTActionPerformed(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("THÔNG TIN CA TRỰC CỦA NHÂN VIÊN");

        btn_HuyThaoTac_CT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_HuyThaoTac_CT.setText("Hủy");
        btn_HuyThaoTac_CT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyThaoTac_CTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel63)
                                    .addComponent(jLabel64)
                                    .addComponent(jLabel65))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jC_CaTruc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jT_MaNVTruc)
                                    .addComponent(jT_TenNVTruc)
                                    .addComponent(jDate_NgayTruc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_XacNhanThemCT)
                                    .addComponent(btn_ThemCaTruc, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_HuyThaoTac_CT, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                    .addComponent(btn_XoaCaTruc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(22, 22, 22)))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel66)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jC_CaTruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jT_MaNVTruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jT_TenNVTruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel65)
                    .addComponent(jDate_NgayTruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemCaTruc)
                    .addComponent(btn_XoaCaTruc))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_XacNhanThemCT)
                    .addComponent(btn_HuyThaoTac_CT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Connection ketNoi4 = KetNoiCSDL.ketNoi();
        String sql4 = "select TEN_CA from CA_TRUC";
        try{
            PreparedStatement pr = ketNoi4.prepareStatement(sql4);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                jC_CaTruc.addItem(rs.getString("TEN_CA"));
            }
            rs.close();
            pr.close();
            ketNoi4.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("THÔNG TIN CA TRỰC");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("Ca trực:");

        jC_CaTruc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jC_CaTruc1ActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setText("Giờ bắt đầu:");

        jT_CT_BD.setEditable(false);
        jT_CT_BD.setToolTipText("HH:mm:ss");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setText("Giờ kết thúc");

        jT_CT_KT.setEditable(false);
        jT_CT_KT.setToolTipText("HH:mm:ss");

        btn_SuaCaTruc1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_SuaCaTruc1.setText("Đặt lại");
        btn_SuaCaTruc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaCaTruc1ActionPerformed(evt);
            }
        });

        btn_LuuCaTruc1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_LuuCaTruc1.setText("Lưu");
        btn_LuuCaTruc1.setEnabled(false);
        btn_LuuCaTruc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuCaTruc1ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton10.setText("Hủy");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69)
                            .addComponent(jLabel70))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jT_CT_BD)
                            .addComponent(jC_CaTruc1, 0, 154, Short.MAX_VALUE)
                            .addComponent(jT_CT_KT)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_SuaCaTruc1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(btn_LuuCaTruc1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jC_CaTruc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jT_CT_BD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jT_CT_KT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_SuaCaTruc1)
                    .addComponent(btn_LuuCaTruc1)
                    .addComponent(jButton10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Connection ketNoi5 = KetNoiCSDL.ketNoi();
        String sql5 = "select TEN_CA from CA_TRUC";
        try{
            PreparedStatement pr = ketNoi5.prepareStatement(sql5);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                jC_CaTruc1.addItem(rs.getString("TEN_CA"));
            }
            rs.close();
            pr.close();
            ketNoi5.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        jC_LocCaTruc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TẤT CẢ" }));

        jLabel32.setText("Ca trực");

        jC_LocNgayLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TẤT CẢ", "ĐÃ LÀM", "CHƯA LÀM" }));

        jLabel37.setText("Ngày làm");

        btn_LocTheoYC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_LocTheoYC.setText("Lọc");
        btn_LocTheoYC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocTheoYCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPan_PhanCongLayout = new javax.swing.GroupLayout(jPan_PhanCong);
        jPan_PhanCong.setLayout(jPan_PhanCongLayout);
        jPan_PhanCongLayout.setHorizontalGroup(
            jPan_PhanCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_PhanCongLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPan_PhanCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPan_PhanCongLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPan_PhanCongLayout.createSequentialGroup()
                        .addComponent(jC_LocCaTruc, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jC_LocNgayLam, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel37)
                        .addGap(30, 30, 30)
                        .addComponent(btn_LocTheoYC, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPan_PhanCongLayout.setVerticalGroup(
            jPan_PhanCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPan_PhanCongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPan_PhanCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPan_PhanCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jC_LocCaTruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jC_LocNgayLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(btn_LocTheoYC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Connection ketNoi7 = KetNoiCSDL.ketNoi();
        String sql7 = "select TEN_CA from CA_TRUC";
        try{
            PreparedStatement pr = ketNoi7.prepareStatement(sql7);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                jC_LocCaTruc.addItem(rs.getString("TEN_CA"));
            }
            rs.close();
            pr.close();
            ketNoi7.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        jTable_NV.addTab("Phân Công Trực", jPan_PhanCong);

        jPan_ChinhSuaTTVe.setBackground(new java.awt.Color(255, 255, 255));
        jPan_ChinhSuaTTVe.setLayout(null);

        jTable_KhungGio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại vé", "Khung giờ", "Giờ bắt đầu", "Giờ kết thúc", "Giá tiền"
            }
        ));
        jTable_KhungGio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable_KhungGioMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(jTable_KhungGio);

        jPan_ChinhSuaTTVe.add(jScrollPane7);
        jScrollPane7.setBounds(30, 330, 650, 190);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Khung giờ");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Giờ bắt đầu:");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Giờ kết thúc:");

        jT_GioBD.setEditable(false);

        jT_GioKT.setEditable(false);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Giá tiền:");

        jT_TienKhungGio.setEditable(false);

        jT_KhungGio.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jT_GioBD)
                                .addComponent(jT_KhungGio, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jT_TienKhungGio)
                            .addComponent(jT_GioKT, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel57)
                    .addComponent(jT_KhungGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jT_GioBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jT_GioKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jT_TienKhungGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPan_ChinhSuaTTVe.add(jPanel2);
        jPanel2.setBounds(50, 40, 600, 210);

        btn_HuyDatLai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_HuyDatLai.setText("Hủy");
        btn_HuyDatLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyDatLaiActionPerformed(evt);
            }
        });
        jPan_ChinhSuaTTVe.add(btn_HuyDatLai);
        btn_HuyDatLai.setBounds(560, 270, 80, 30);

        btn_DatLaiVe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_DatLaiVe.setText("Đặt lại");
        btn_DatLaiVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DatLaiVeActionPerformed(evt);
            }
        });
        jPan_ChinhSuaTTVe.add(btn_DatLaiVe);
        btn_DatLaiVe.setBounds(340, 270, 90, 30);

        btn_LuuVe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_LuuVe.setText("Lưu");
        btn_LuuVe.setEnabled(false);
        btn_LuuVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuVeActionPerformed(evt);
            }
        });
        jPan_ChinhSuaTTVe.add(btn_LuuVe);
        btn_LuuVe.setBounds(450, 270, 90, 30);

        jTable_NV.addTab("Chỉnh Sửa Thông Tin Vé", jPan_ChinhSuaTTVe);

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
        if (jTable_GuiXe.getColumnModel().getColumnCount() > 0) {
            jTable_GuiXe.getColumnModel().getColumn(0).setMaxWidth(55);
            jTable_GuiXe.getColumnModel().getColumn(1).setMaxWidth(65);
        }

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
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

    jPan_ThongKe.setBackground(new java.awt.Color(255, 255, 255));

    btn_DoanhThu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    btn_DoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_bill_24px.png"))); // NOI18N
    btn_DoanhThu.setText("Doanh thu");
    btn_DoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    btn_DoanhThu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_DoanhThuActionPerformed(evt);
        }
    });

    jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jP_DoanhThu.setBackground(new java.awt.Color(255, 255, 255));
    jP_DoanhThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    jP_DoanhThu.setLayout(null);

    jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel19.setText("DOANH THU");
    jP_DoanhThu.add(jLabel19);
    jLabel19.setBounds(10, 23, 510, 29);
    jP_DoanhThu.add(jSeparator7);
    jSeparator7.setBounds(10, 70, 500, 10);
    jP_DoanhThu.add(jSeparator12);
    jSeparator12.setBounds(10, 212, 500, 10);

    jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jP_DoanhThu.add(jSeparator13);
    jSeparator13.setBounds(10, 70, 10, 142);

    jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jSeparator14.setPreferredSize(new java.awt.Dimension(50, 12));
    jP_DoanhThu.add(jSeparator14);
    jSeparator14.setBounds(510, 70, 10, 142);

    jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel29.setText("Tổng số tiền vé lượt thu được: ");
    jP_DoanhThu.add(jLabel29);
    jLabel29.setBounds(60, 120, 190, 15);

    jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel34.setText("Tổng số tiền thu được: ");
    jP_DoanhThu.add(jLabel34);
    jLabel34.setBounds(30, 100, 140, 15);

    jL_TongTienLuot.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_TongTienLuot.setText("0");
    jP_DoanhThu.add(jL_TongTienLuot);
    jL_TongTienLuot.setBounds(260, 120, 70, 15);

    jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel36.setText("Tổng số tiền sự cố thu được:");
    jP_DoanhThu.add(jLabel36);
    jLabel36.setBounds(60, 160, 201, 15);

    jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    jTable_TKDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Biển số xe", "Loại vé", "Ngày vào", "Giờ vào", "Ngày ra", "Giờ ra", "Thành tiền"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable_TKDoanhThu.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    jTable_TKDoanhThu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    jScrollPane1.setViewportView(jTable_TKDoanhThu);
    jTable_TKDoanhThu.setAutoResizeMode(jTable_TKDoanhThu.AUTO_RESIZE_OFF);

    jP_DoanhThu.add(jScrollPane1);
    jScrollPane1.setBounds(10, 270, 500, 190);

    jL_TongTienSC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_TongTienSC.setText("0");
    jP_DoanhThu.add(jL_TongTienSC);
    jL_TongTienSC.setBounds(260, 160, 70, 15);

    jL_TongTien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_TongTien.setText("0");
    jP_DoanhThu.add(jL_TongTien);
    jL_TongTien.setBounds(260, 100, 70, 15);

    jT_DT_TimXe.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jT_DT_TimXeKeyReleased(evt);
        }
    });
    jP_DoanhThu.add(jT_DT_TimXe);
    jT_DT_TimXe.setBounds(10, 230, 160, 30);

    jButton4.setText("Tìm kiếm theo số xe");
    jP_DoanhThu.add(jButton4);
    jButton4.setBounds(180, 240, 140, 23);

    jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel39.setText("Tổng số tiền vé tháng thu được:");
    jP_DoanhThu.add(jLabel39);
    jLabel39.setBounds(60, 140, 201, 15);

    jL_TongTienThang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_TongTienThang.setText("0");
    jP_DoanhThu.add(jL_TongTienThang);
    jL_TongTienThang.setBounds(260, 140, 70, 15);

    jLayeredPane1.add(jP_DoanhThu);
    jP_DoanhThu.setBounds(1, -2, 530, 480);
    jP_DoanhThu.setVisible(false);

    jP_HienTai.setBackground(new java.awt.Color(255, 255, 255));
    jP_HienTai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    jP_HienTai.setLayout(null);

    jLabel28.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel28.setText("THỐNG KÊ XE");
    jP_HienTai.add(jLabel28);
    jLabel28.setBounds(10, 11, 510, 29);
    jP_HienTai.add(jSeparator15);
    jSeparator15.setBounds(20, 50, 490, 2);

    jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jP_HienTai.add(jSeparator16);
    jSeparator16.setBounds(510, 50, 10, 180);

    jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jP_HienTai.add(jSeparator17);
    jSeparator17.setBounds(20, 49, 10, 180);

    jL_ThongKeTongSoXe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_ThongKeTongSoXe.setText("0");
    jP_HienTai.add(jL_ThongKeTongSoXe);
    jL_ThongKeTongSoXe.setBounds(260, 70, 40, 15);

    jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel35.setText("Tổng số xe chưa lấy:");
    jP_HienTai.add(jLabel35);
    jLabel35.setBounds(80, 130, 130, 15);

    jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel38.setText("Tổng số xe đã lấy:");
    jP_HienTai.add(jLabel38);
    jLabel38.setBounds(80, 100, 110, 15);
    jP_HienTai.add(jSeparator18);
    jSeparator18.setBounds(20, 230, 490, 10);

    jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel33.setText("Tình trạng gửi:");
    jP_HienTai.add(jLabel33);
    jLabel33.setBounds(350, 70, 100, 15);

    jL_ThongKeXeChuaLay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_ThongKeXeChuaLay.setText("0");
    jP_HienTai.add(jL_ThongKeXeChuaLay);
    jL_ThongKeXeChuaLay.setBounds(260, 130, 40, 15);

    jL_ThongKeXeDaLay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_ThongKeXeDaLay.setText("0");
    jP_HienTai.add(jL_ThongKeXeDaLay);
    jL_ThongKeXeDaLay.setBounds(260, 100, 40, 15);

    jTable_TKHienTai.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Biển số xe", "Loại vé", "Ngày vào", "Giờ vào", "Ngày ra", "Giờ ra", "NV QL Vào", "NV QL Ra"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane4.setViewportView(jTable_TKHienTai);
    jTable_TKHienTai.setAutoResizeMode(jTable_TKHienTai.AUTO_RESIZE_OFF);

    jP_HienTai.add(jScrollPane4);
    jScrollPane4.setBounds(20, 290, 490, 160);

    jT_HT_TimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jT_HT_TimKiemKeyReleased(evt);
        }
    });
    jP_HienTai.add(jT_HT_TimKiem);
    jT_HT_TimKiem.setBounds(20, 250, 170, 30);

    jButton1.setText("Tìm kiếm theo số xe");
    jP_HienTai.add(jButton1);
    jButton1.setBounds(210, 260, 140, 23);

    jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel54.setText("Tổng số xe:");
    jP_HienTai.add(jLabel54);
    jLabel54.setBounds(50, 70, 130, 15);

    jC_TinhTrangGui.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TẤT CẢ", "ĐÃ LẤY", "CHƯA LẤY" }));
    jC_TinhTrangGui.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jC_TinhTrangGuiActionPerformed(evt);
        }
    });
    jP_HienTai.add(jC_TinhTrangGui);
    jC_TinhTrangGui.setBounds(350, 90, 140, 20);

    jLayeredPane1.add(jP_HienTai);
    jP_HienTai.setBounds(0, 0, 530, 480);
    jP_HienTai.setVisible(false);

    jP_VeThang.setBackground(new java.awt.Color(255, 255, 255));
    jP_VeThang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    jP_VeThang.setLayout(null);

    jLabel47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel47.setText("THỐNG KÊ CÁC XE ĐĂNG KÍ THÁNG");
    jP_VeThang.add(jLabel47);
    jLabel47.setBounds(10, 11, 510, 29);
    jP_VeThang.add(jSeparator19);
    jSeparator19.setBounds(10, 60, 510, 10);

    jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jP_VeThang.add(jSeparator20);
    jSeparator20.setBounds(520, 60, 10, 150);

    jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jP_VeThang.add(jSeparator21);
    jSeparator21.setBounds(10, 60, 10, 150);

    jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel48.setText("Lựa chọn:");
    jP_VeThang.add(jLabel48);
    jLabel48.setBounds(30, 80, 60, 15);

    jC_TinhTrangDKT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TẤT CẢ", "CÒN HẠN", "HẾT HẠN" }));
    jC_TinhTrangDKT.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jC_TinhTrangDKTActionPerformed(evt);
        }
    });
    jP_VeThang.add(jC_TinhTrangDKT);
    jC_TinhTrangDKT.setBounds(100, 80, 200, 20);

    jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel49.setText("Hết hạn:");
    jP_VeThang.add(jLabel49);
    jLabel49.setBounds(90, 180, 70, 15);

    jL_XeCH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_XeCH.setText("0");
    jP_VeThang.add(jL_XeCH);
    jL_XeCH.setBounds(190, 160, 120, 15);

    jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel51.setText("Còn hạn:");
    jP_VeThang.add(jLabel51);
    jLabel51.setBounds(90, 160, 70, 15);

    jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel52.setText("Tổng số xe đăng kí:");
    jP_VeThang.add(jLabel52);
    jLabel52.setBounds(30, 140, 120, 15);

    jL_XeHH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_XeHH.setText("0");
    jP_VeThang.add(jL_XeHH);
    jL_XeHH.setBounds(190, 180, 110, 15);

    jL_XeDKT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_XeDKT.setText("0");
    jP_VeThang.add(jL_XeDKT);
    jL_XeDKT.setBounds(190, 140, 110, 15);
    jP_VeThang.add(jSeparator22);
    jSeparator22.setBounds(10, 210, 510, 2);

    jTable_TKVeThang.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Biển số xe", "Mã sinh viên đăng kí", "Ngày làm vé", "Ngày hết hạn"
        }
    ));
    jScrollPane5.setViewportView(jTable_TKVeThang);

    jP_VeThang.add(jScrollPane5);
    jScrollPane5.setBounds(10, 250, 510, 210);

    jLayeredPane1.add(jP_VeThang);
    jP_VeThang.setBounds(0, 0, 530, 480);
    jP_VeThang.setVisible(false);

    jP_SuCo.setBackground(new java.awt.Color(255, 255, 255));
    jP_SuCo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    jP_SuCo.setLayout(null);

    jLabel55.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel55.setText("THỐNG KÊ SỰ CỐ");
    jP_SuCo.add(jLabel55);
    jLabel55.setBounds(10, 11, 510, 29);
    jP_SuCo.add(jSeparator23);
    jSeparator23.setBounds(10, 60, 510, 10);

    jSeparator24.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jP_SuCo.add(jSeparator24);
    jSeparator24.setBounds(520, 60, 10, 150);

    jSeparator25.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jP_SuCo.add(jSeparator25);
    jSeparator25.setBounds(10, 60, 10, 150);

    jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel56.setText("Lựa chọn:");
    jP_SuCo.add(jLabel56);
    jLabel56.setBounds(30, 80, 60, 15);

    jC_SuCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TẤT CẢ" }));
    jC_SuCo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jC_SuCoActionPerformed(evt);
        }
    });
    jP_SuCo.add(jC_SuCo);
    jC_SuCo.setBounds(100, 80, 200, 20);
    Connection ketNoi3 = KetNoiCSDL.ketNoi();
    String sql3 = "select TEN_SU_CO from SU_CO";
    try{
        PreparedStatement pr = ketNoi3.prepareStatement(sql3);
        ResultSet rs = pr.executeQuery();
        while (rs.next()){
            jC_SuCo.addItem(rs.getString("TEN_SU_CO"));
        }
        rs.close();
        pr.close();
        ketNoi3.close();
    }
    catch (Exception e){
        e.printStackTrace();
    }

    jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel60.setText("Tổng số sự cố:");
    jP_SuCo.add(jLabel60);
    jLabel60.setBounds(30, 140, 120, 15);

    jL_TongSC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_TongSC.setText("0");
    jP_SuCo.add(jL_TongSC);
    jL_TongSC.setBounds(190, 140, 110, 15);
    jP_SuCo.add(jSeparator26);
    jSeparator26.setBounds(10, 210, 510, 2);

    jTable_TKSuCo.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Biển số xe", "Tên sự cố", "Ngày xảy ra", "Xử phạt"
        }
    ));
    jScrollPane6.setViewportView(jTable_TKSuCo);

    jP_SuCo.add(jScrollPane6);
    jScrollPane6.setBounds(10, 250, 510, 210);

    jL_SuCo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_SuCo.setText("0");
    jP_SuCo.add(jL_SuCo);
    jL_SuCo.setBounds(190, 160, 110, 15);

    jL_TienPaht1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_TienPaht1.setText("Tổng số tiền phạt:");
    jP_SuCo.add(jL_TienPaht1);
    jL_TienPaht1.setBounds(30, 160, 140, 15);

    jLayeredPane1.add(jP_SuCo);
    jP_SuCo.setBounds(0, 0, 530, 480);
    jP_SuCo.setVisible(false);

    btn_HienTai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    btn_HienTai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_today_30px.png"))); // NOI18N
    btn_HienTai.setText("Hiện tại");
    btn_HienTai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    btn_HienTai.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_HienTaiActionPerformed(evt);
        }
    });

    btn_VeThang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    btn_VeThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_event_24px.png"))); // NOI18N
    btn_VeThang.setText("Vé tháng");
    btn_VeThang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    btn_VeThang.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_VeThangActionPerformed(evt);
        }
    });

    btn_SuCo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    btn_SuCo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_error_24px.png"))); // NOI18N
    btn_SuCo.setText("QL Sự Cố");
    btn_SuCo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    btn_SuCo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_SuCoActionPerformed(evt);
        }
    });

    jL_ThongKe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jL_ThongKe.setText("Thống kê theo:");

    jC_ThongKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TẤT CẢ", "NGÀY", "THÁNG", "QUÝ" }));
    jC_ThongKe.setPreferredSize(new java.awt.Dimension(32, 23));
    jC_ThongKe.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jC_ThongKeActionPerformed(evt);
        }
    });

    jDate_NgayThongKe.setEnabled(false);
    jDate_NgayThongKe.setPreferredSize(new java.awt.Dimension(74, 23));

    jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel30.setText("Ngày:");

    jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel50.setText("Tháng:");

    jC_Thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " " }));
    jC_Thang.setSelectedIndex(-1);
    jC_Thang.setEnabled(false);
    jC_Thang.setPreferredSize(new java.awt.Dimension(32, 23));

    jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel53.setText("Quý:");

    jC_Quy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
    jC_Quy.setSelectedIndex(-1);
    jC_Quy.setEnabled(false);
    jC_Quy.setPreferredSize(new java.awt.Dimension(32, 23));

    btn_DT_EXtoExcel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    btn_DT_EXtoExcel.setText("Export to Excel");
    btn_DT_EXtoExcel.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_DT_EXtoExcelActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPan_ThongKeLayout = new javax.swing.GroupLayout(jPan_ThongKe);
    jPan_ThongKe.setLayout(jPan_ThongKeLayout);
    jPan_ThongKeLayout.setHorizontalGroup(
        jPan_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPan_ThongKeLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPan_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPan_ThongKeLayout.createSequentialGroup()
                    .addGroup(jPan_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_DoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_HienTai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_VeThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_SuCo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jL_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jC_ThongKe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDate_NgayThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jC_Thang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jC_Quy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator27))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPan_ThongKeLayout.createSequentialGroup()
                    .addComponent(btn_DT_EXtoExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())))
    );
    jPan_ThongKeLayout.setVerticalGroup(
        jPan_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPan_ThongKeLayout.createSequentialGroup()
            .addGroup(jPan_ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPan_ThongKeLayout.createSequentialGroup()
                    .addComponent(jL_ThongKe)
                    .addGap(5, 5, 5)
                    .addComponent(jC_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel30)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jDate_NgayThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel50)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jC_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel53)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jC_Quy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSeparator27, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)
                    .addComponent(btn_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btn_HienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btn_VeThang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btn_SuCo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btn_DT_EXtoExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 22, Short.MAX_VALUE))
    );

    jTable_NV.addTab("Thống Kê", jPan_ThongKe);

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
            .addGap(143, 143, 143)
            .addComponent(jL_Icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(26, 26, 26)
            .addComponent(btn_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jTable_NV)
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
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangXuatActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btn_DangXuatActionPerformed

    private void btn_HuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyBoActionPerformed
        // TODO add your handling code here:
        Date date =null;

        //set cac nut bam va ko cho dien thong tin
        btn_Edit.setEnabled(true);
        btn_ThemNV.setEnabled(true);
        btn_xoa.setEnabled(true);
        btn_SaveAdd.setEnabled(false);
        btn_SaveEdit.setEnabled(false);

        jT_Name.setEditable(false);
        jT_ID.setEditable(false);
        jDate_Date.setEnabled(false);
        jC_Sex.setEnabled(false);
        jPass_Pass.setEditable(false);
        jC_Class.setEnabled(false);
        jT_DiaChi.setEditable(false);
        jT_Phone.setEditable(false);

        jT_ID.setText("");
        jT_Name.setText("");
        jDate_Date.setDate(date);
        jC_Sex.setSelectedItem("");
        jPass_Pass.setText("");
        jC_Class.setSelectedItem("");
        jT_DiaChi.setText("");
        jT_Phone.setText("");
    }//GEN-LAST:event_btn_HuyBoActionPerformed

    private void btn_SaveAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveAddActionPerformed
        // TODO add your handling code here:
        Date date = null;
        //Dat cac bien de lay du lieu trong jTextF
        String maNV = jT_ID.getText();
        String tenNV = jT_Name.getText();
        String gioiTinh = (String) jC_Sex.getSelectedItem();
        String MK = jPass_Pass.getText();
        String chucVu = (String) jC_Class.getSelectedItem();
        String diaChi = jT_DiaChi.getText();
        String SDT = jT_Phone.getText();

        //Chuc nang
        if (maNV.equals("") || tenNV.equals("") || gioiTinh.equals("") || chucVu.equals("") || MK.equals("")){
            JOptionPane.showMessageDialog(null, "Các thông tin: Mã nv, tên nv, giới tính, chức vụ và mật khẩu không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);

        }
        else{
            //chuyen ngay sinh dung dang
            java.sql.Date ngaySinh = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_Date.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngaySinh = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
            }

            //Kiem tra nhan vien co ton tai chua
            if (thaoTac.checkIDNV(maNV) == 0){
                //Kiem tra SDT
                if (thaoTac.chuanHoaSDT(SDT) == 0){
                    JOptionPane.showMessageDialog(null, "SDT không hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    jT_Phone.setText("");
                }
                else{
                    //set cac nut
                    btn_Edit.setEnabled(true);
                    btn_ThemNV.setEnabled(true);
                    btn_xoa.setEnabled(true);
                    btn_SaveAdd.setEnabled(false);
                    btn_SaveEdit.setEnabled(false);

                    jT_Name.setEditable(false);
                    jT_ID.setEditable(false);
                    jDate_Date.setEnabled(false);
                    jC_Sex.setEnabled(false);
                    jPass_Pass.setEditable(false);
                    jC_Class.setEnabled(false);
                    jT_DiaChi.setEditable(false);
                    jT_Phone.setEditable(false);

                    jT_ID.setText("");
                    jT_Name.setText("");
                    jDate_Date.setDate(date);
                    jC_Sex.setSelectedItem("");
                    jPass_Pass.setText("");
                    jC_Class.setSelectedItem("");
                    jT_DiaChi.setText("");
                    jT_Phone.setText("");

                    //lay cac thong tin de them vao CSDL
                    JOptionPane.showMessageDialog(null, "Thêm thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    String maCV = thaoTac.maCV(chucVu);
                    thaoTac.themTK(maNV.toUpperCase(), MK, maCV);
                    thaoTac.themNV(maNV.toUpperCase(), tenNV.toUpperCase(), ngaySinh, diaChi.toUpperCase(), SDT, gioiTinh);
                    
                    this.layTTCaTruc();
                    layTT();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại. Không thể thêm nhân viên này.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                jT_ID.setText("");
            }
        }

    }//GEN-LAST:event_btn_SaveAddActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        Date date = null;
        String maNV = jT_ID.getText();
        if (maNV.equals("")){
            JOptionPane.showMessageDialog(null, "Không tồn tại mã nhân viên muốn xóa.", "", JOptionPane.WARNING_MESSAGE);
        }
        else{
            if (thaoTac.checkIDNV(maNV) == 1){
                int check = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa", "", JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION){
                    thaoTac.xoaTTCaMaNVNghi(maNV);
                    thaoTac.xoaNV(maNV);
                    thaoTac.xoaDangNhap(maNV);

                    //thông báo xóa thành công
                    JOptionPane.showMessageDialog(null, "Xóa thành công.", "", JOptionPane.WARNING_MESSAGE);

                    //set lại các khung là rỗng
                    jT_ID.setText("");
                    jT_Name.setText("");
                    jDate_Date.setDate(date);
                    jC_Sex.setSelectedItem("");
                    jPass_Pass.setText("");
                    jC_Class.setSelectedItem("");
                    jT_DiaChi.setText("");
                    jT_Phone.setText("");

                    //vẽ lại bảng thông tin
                    this.layTT();
                    this.layTTCaTruc();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên muốn xóa.", "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_ThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNVActionPerformed
        // TODO add your handling code here:
        Date date =null;

        //set cac nut bam va ko cho dien thong tin
        btn_Edit.setEnabled(false);
        btn_ThemNV.setEnabled(false);
        btn_xoa.setEnabled(false);
        btn_SaveAdd.setEnabled(true);
        btn_SaveEdit.setEnabled(false);

        jT_Name.setEditable(true);
        jT_ID.setEditable(true);
        jDate_Date.setEnabled(true);
        jC_Sex.setEnabled(true);
        jPass_Pass.setEditable(true);
        jC_Class.setEnabled(true);
        jT_DiaChi.setEditable(true);
        jT_Phone.setEditable(true);

        jT_ID.setText("");
        jT_Name.setText("");
        jDate_Date.setDate(date);
        jC_Sex.setSelectedItem("");
        jPass_Pass.setText("");
        jC_Class.setSelectedItem("");
        jT_DiaChi.setText("");
        jT_Phone.setText("");
    }//GEN-LAST:event_btn_ThemNVActionPerformed

    private void btn_SaveEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveEditActionPerformed
        // TODO add your handling code here:
        Date date = null;
        //Dat cac bien de lay du lieu trong jTextF
        String maNV = jT_ID.getText();
        String tenNV = jT_Name.getText();
        String gioiTinh = (String) jC_Sex.getSelectedItem();
        String MK = jPass_Pass.getText();
        String chucVu = (String) jC_Class.getSelectedItem();
        String diaChi = jT_DiaChi.getText();
        String SDT = jT_Phone.getText();

        //Chuc nang
        if (maNV.equals("") || tenNV.equals("") || gioiTinh.equals("") || chucVu.equals("") || MK.equals("")){
            JOptionPane.showMessageDialog(null, "Các thông tin: Mã nv, tên nv, giới tính, chức vụ và mật khẩu không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);

        }
        else{
            if (maNV.equals("")){
                JOptionPane.showMessageDialog(null, "Không tồn tại nhân viên. Mời chọn nhân viên dưới bảng danh sách.", "Thông báo", JOptionPane.WARNING_MESSAGE);
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

                //set cac nut
                btn_Edit.setEnabled(true);
                btn_ThemNV.setEnabled(true);
                btn_xoa.setEnabled(true);
                btn_SaveAdd.setEnabled(false);
                btn_SaveEdit.setEnabled(false);

                jT_Name.setEditable(false);
                jT_ID.setEditable(false);
                jDate_Date.setEnabled(false);
                jC_Sex.setEnabled(false);
                jPass_Pass.setEditable(false);
                jC_Class.setEnabled(false);
                jT_DiaChi.setEditable(false);
                jT_Phone.setEditable(false);

                jT_ID.setText("");
                jT_Name.setText("");
                jDate_Date.setDate(date);
                jC_Sex.setSelectedItem("");
                jPass_Pass.setText("");
                jC_Class.setSelectedItem("");
                jT_DiaChi.setText("");
                jT_Phone.setText("");

                //lay cac thong tin de them vao CSDL
                JOptionPane.showMessageDialog(null, "Sửa thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                String maCV = thaoTac.maCV(chucVu);
                thaoTac.suaDN(maNV, MK, maCV);
                thaoTac.suaNV(maNV, tenNV, ngaySinh, diaChi, SDT, gioiTinh);
                this.layTTCaTruc();
                layTT();
            }

        }
    }//GEN-LAST:event_btn_SaveEditActionPerformed

    private void btn_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditActionPerformed
        // TODO add your handling code here:
        //set cac nut bam va ko cho dien thong tin
        btn_Edit.setEnabled(false);
        btn_ThemNV.setEnabled(false);
        btn_xoa.setEnabled(false);
        jT_Name.setEditable(true);
        jT_ID.setEditable(false);
        jDate_Date.setEnabled(true);
        jC_Sex.setEnabled(true);
        jPass_Pass.setEditable(true);
        jC_Class.setEnabled(true);
        jT_DiaChi.setEditable(true);
        jT_Phone.setEditable(true);
        btn_SaveEdit.setEnabled(true);
    }//GEN-LAST:event_btn_EditActionPerformed

    private void jTable_NhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_NhanVienMousePressed
        // TODO add your handling code here:
        String maNV = (String) jTable_NhanVien.getValueAt(jTable_NhanVien.getSelectedRow(), 0);
        String tenNV = (String) jTable_NhanVien.getValueAt(jTable_NhanVien.getSelectedRow(), 1);
        Date namSinh = (Date) jTable_NhanVien.getValueAt(jTable_NhanVien.getSelectedRow(), 2);
        String chucVu = (String) jTable_NhanVien.getValueAt(jTable_NhanVien.getSelectedRow(), 3);
        String diaChi = (String) jTable_NhanVien.getValueAt(jTable_NhanVien.getSelectedRow(), 4);
        String SDT = (String) jTable_NhanVien.getValueAt(jTable_NhanVien.getSelectedRow(), 5);
        String gioiTinh = (String) jTable_NhanVien.getValueAt(jTable_NhanVien.getSelectedRow(), 6);
        String MK = (String) jTable_NhanVien.getValueAt(jTable_NhanVien.getSelectedRow(), 7);

        jT_ID.setText(maNV);
        jT_Name.setText(tenNV);
        jDate_Date.setDate(namSinh);
        jC_Sex.setSelectedItem(gioiTinh);
        jPass_Pass.setText(MK);
        jC_Class.setSelectedItem(chucVu);
        jT_DiaChi.setText(diaChi);
        jT_Phone.setText(SDT);
    }//GEN-LAST:event_jTable_NhanVienMousePressed

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
        
        if (!bienSo.equals("") && QLXe.checkTinhTrangVeThang(bienSo).equals("")){
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
        else if(QLXe.checkTinhTrangVeThang(bienSo).equals("HẾT HẠN")){
            JOptionPane.showMessageDialog(null, "Cập nhật lại thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            QLXe.capNhatLaiTTVeThang(bienSo,Date.valueOf(ngayDK), Date.valueOf(ngayHH) ,"CÒN HẠN");
        }
        else if (QLXe.checkTinhTrangVeThang(bienSo).equals("CÒN HẠN")){
            JOptionPane.showMessageDialog(null, "Xe vẫn còn hạn, không thể thêm được nữa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Cac thông tin về xe không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_btn_ThangActionPerformed

    private void btn_HuyTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyTienActionPerformed
        // TODO add your handling code here:
        jDi_XNDKThang.dispose();
        jDi_SinhVien.setLocationRelativeTo(null);
        jDi_SinhVien.setVisible(true);
    }//GEN-LAST:event_btn_HuyTienActionPerformed

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

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        // TODO add your handling code here:
        jT_BienSo.setText("");
        jT_HieuXe.setText("");
        jT_MauXe.setText("");
        btn_Vao.setEnabled(true);
        btn_Ra.setEnabled(false);
        jC_Loi.setEnabled(false);
        
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void btn_RaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RaActionPerformed
        // TODO add your handling code here:
        btn_Ra.setEnabled(false);
        btn_Vao.setEnabled(true);
        
        //kiem tra ve
        String maVe = JOptionPane.showInputDialog("Nhập mã vé: ");
        String maLoaiVe = QLXe.layMaLoaiVe(maVe);
        //tinh khung h ra
        String maKhungGio = "";

        //kiem tra h ra
        Time gioRa = Time.valueOf(LocalTime.now());
        maKhungGio = QLXe.maKhungGio(gioRa);
        if (maLoaiVe.equals("T")){
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
        else if(maVe.equals("")){
            
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

    private void jPan_GuiXeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPan_GuiXeMousePressed
        // TODO add your handling code here:
        btn_Vao.setEnabled(true);
        btn_Ra.setEnabled(false);
        jC_Loi.setEnabled(false);
        jT_BienSo.setText("");
        jT_HieuXe.setText("");
        jT_MauXe.setText("");
    }//GEN-LAST:event_jPan_GuiXeMousePressed

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

    private void btn_DoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DoanhThuActionPerformed
        // TODO add your handling code here:
        jP_DoanhThu.setVisible(true);
        jP_HienTai.setVisible(false);
        jP_VeThang.setVisible(false);
        jP_SuCo.setVisible(false);
        
        if(jC_ThongKe.getSelectedItem().equals("TẤT CẢ")){
            //lay du lieu bang theo TAT CA
            String sqlTable = "SELECT LV.TEN_LOAI, VX.BIEN_SO_XE, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.GIA_TIEN" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.TINH_TRANG = N'ĐÃ LẤY'";
            
            //Tinh tien luot
            String sqlLuot = "select sum(GIA_TIEN) as GIA_TIEN\n" +
                            "from VE_XE\n" +
                            "where TINH_TRANG = N'ĐÃ LẤY' AND MA_LOAI_VE = 'L'";
            
            String sqlSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                        "from SU_CO as SC, QL_SU_CO as QL, VE_XE as VX\n" +
                        "where QL.MA_SU_CO = SC.MA_SU_CO and QL.BIEN_SO_XE = VX.BIEN_SO_XE and vx.MA_LOAI_VE = 'L'";
            int tienLuot = Integer.parseInt(QLXe.tinhTienLuot(sqlLuot)) - Integer.parseInt(QLXe.tienSuCo(sqlSC));
            
            //tinh tong tien su co
            String sqlTongSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                        "from SU_CO as SC, QL_SU_CO as QL, VE_XE as VX\n" +
                        "where QL.MA_SU_CO = SC.MA_SU_CO and QL.BIEN_SO_XE = VX.BIEN_SO_XE ";
            
            //tinh tong tien thang
            String sqlThang = "select count(QLVT.BIEN_SO_XE)*GT.GIA_TIEN as GIA_TIEN\n" +
                            "from QL_VE_THANG as QLVT, GIA_TIEN as GT\n" +
                            "where GT.MA_LOAI_VE = 'T'\n" +
                            "GROUP BY GT.GIA_TIEN";
            //tinh tong so tien thu dc
            int tongTien = Integer.parseInt(QLXe.tienSuCo(sqlTongSC)) + Integer.parseInt(QLXe.tinhTienThang(sqlThang)) + tienLuot;
                    
            //them du lieu
            jL_TongTien.setText(String.valueOf(tongTien));
            jL_TongTienLuot.setText(String.valueOf(tienLuot));
            jL_TongTienSC.setText(QLXe.tienSuCo(sqlTongSC));
            jL_TongTienThang.setText(QLXe.tinhTienThang(sqlThang));
            this.layDoanhThu(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("NGÀY")){
            //lay ngay ra
            java.sql.Date ngayRa = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayThongKe.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngayRa = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
                ngayRa = java.sql.Date.valueOf(java.time.LocalDate.now());
            }
            //lay du lieu bang theo NGAY
            String sqlTable = "SELECT LV.TEN_LOAI, VX.BIEN_SO_XE, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.GIA_TIEN" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.TINH_TRANG = N'ĐÃ LẤY' AND VX.NGAY_RA = '" + ngayRa +"'";
            
            //Tinh tien luot theo NGAY
            String sqlLuot = "select sum(GIA_TIEN) as GIA_TIEN\n" +
                    "from VE_XE\n" +
                    "where TINH_TRANG = N'ĐÃ LẤY' AND MA_LOAI_VE = 'L' AND NGAY_RA = '" + ngayRa + "'";
            String sqlSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                        "from SU_CO as SC, QL_SU_CO as QL, VE_XE as VX\n" +
                        "where QL.MA_SU_CO = SC.MA_SU_CO and QL.BIEN_SO_XE = VX.BIEN_SO_XE and vx.MA_LOAI_VE = 'L' AND QL.NGAY_XAY_RA = '" + ngayRa + "'";
            int tienLuot = Integer.parseInt(QLXe.tinhTienLuot(sqlLuot)) - Integer.parseInt(QLXe.tienSuCo(sqlSC));
            
            //tinh tong tien su co
            String sqlTongSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                        "from SU_CO as SC, QL_SU_CO as QL, VE_XE as VX\n" +
                        "where QL.MA_SU_CO = SC.MA_SU_CO and QL.BIEN_SO_XE = VX.BIEN_SO_XE and QL.NGAY_XAY_RA = '" + ngayRa + "'";
            
            //tinh tong tien thang
            String sqlThang = "select count(QLVT.BIEN_SO_XE)*GT.GIA_TIEN as GIA_TIEN\n" +
                            "from QL_VE_THANG as QLVT, GIA_TIEN as GT\n" +
                            "where GT.MA_LOAI_VE = 'T' and QLVT.NGAY_LAM_VE = '" + ngayRa + "'\n" +
                            "GROUP BY GT.GIA_TIEN";
            //tinh tong so tien thu dc
            int tongTien = Integer.parseInt(QLXe.tienSuCo(sqlTongSC)) + Integer.parseInt(QLXe.tinhTienThang(sqlThang)) + tienLuot;
            
            //them du lieu
            jL_TongTien.setText(String.valueOf(tongTien));
            jL_TongTienLuot.setText(String.valueOf(tienLuot));
            jL_TongTienSC.setText(QLXe.tienSuCo(sqlTongSC));
            jL_TongTienThang.setText(QLXe.tinhTienThang(sqlThang));
            this.layDoanhThu(sqlTable);
            
        }
        else if(jC_ThongKe.getSelectedItem().equals("THÁNG")){
            String thang = (String) jC_Thang.getSelectedItem();
            //lay du lieu bang theo THANG
            String sqlTable = "SELECT LV.TEN_LOAI, VX.BIEN_SO_XE, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.GIA_TIEN" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.TINH_TRANG = N'ĐÃ LẤY' AND MONTH(VX.NGAY_RA) = '" + thang +"'";
            
            //Tinh tien luot theo THANG
            String sqlLuot = "select sum(GIA_TIEN) as GIA_TIEN\n" +
                    "from VE_XE\n" +
                    "where TINH_TRANG = N'ĐÃ LẤY' AND MA_LOAI_VE = 'L' AND MONTH(NGAY_RA) = '" + thang + "'";
            String sqlSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL, VE_XE as VX\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO and QL.BIEN_SO_XE = VX.BIEN_SO_XE and vx.MA_LOAI_VE = 'L' AND MONTH(QL.NGAY_XAY_RA) = '" + thang + "'";
            int tienLuot = Integer.parseInt(QLXe.tinhTienLuot(sqlLuot)) - Integer.parseInt(QLXe.tienSuCo(sqlSC));
            
            //tinh tong tien su co
            String sqlTongSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                        "from SU_CO as SC, QL_SU_CO as QL, VE_XE as VX\n" +
                        "where QL.MA_SU_CO = SC.MA_SU_CO and QL.BIEN_SO_XE = VX.BIEN_SO_XE and MONTH(QL.NGAY_XAY_RA) = '" + thang + "'";
            
            //tinh tong tien thang
            String sqlThang = "select count(QLVT.BIEN_SO_XE)*GT.GIA_TIEN as GIA_TIEN\n" +
                            "from QL_VE_THANG as QLVT, GIA_TIEN as GT\n" +
                            "where GT.MA_LOAI_VE = 'T' and MONTH(QLVT.NGAY_LAM_VE) = '" + thang + "'\n" +
                            "GROUP BY GT.GIA_TIEN";
            //tinh tong so tien thu dc
            int tongTien = Integer.parseInt(QLXe.tienSuCo(sqlTongSC)) + Integer.parseInt(QLXe.tinhTienThang(sqlThang)) + tienLuot;
            
            //them du lieu
            jL_TongTien.setText(String.valueOf(tongTien));
            jL_TongTienLuot.setText(String.valueOf(tienLuot));
            jL_TongTienSC.setText(QLXe.tienSuCo(sqlTongSC));
            jL_TongTienThang.setText(QLXe.tinhTienThang(sqlThang));
            this.layDoanhThu(sqlTable);
            
        }
        else if(jC_ThongKe.getSelectedItem().equals("QUÝ")){
            String thangBD = "", thangKT = "";
            if (jC_Quy.getSelectedItem().equals("1")){
                thangBD = "1";
                thangKT = "3";
            }
            else if (jC_Quy.getSelectedItem().equals("2")){
                thangBD = "4";
                thangKT = "6";
            }
            else if (jC_Quy.getSelectedItem().equals("3")){
                thangBD = "7";
                thangKT = "9";
            }
            else if (jC_Quy.getSelectedItem().equals("4")){
                thangBD = "10";
                thangKT = "12";
            }
            //lay du lieu bang theo QUY
            String sqlTable = "SELECT LV.TEN_LOAI, VX.BIEN_SO_XE, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.GIA_TIEN" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.TINH_TRANG = N'ĐÃ LẤY' AND MONTH(VX.NGAY_RA) BETWEEN " + thangBD + " AND " + thangKT;
           
            //Tinh tien luot theo QUY
            String sqlLuot = "select sum(GIA_TIEN) as GIA_TIEN\n" +
                    "from VE_XE\n" +
                    "where TINH_TRANG = N'ĐÃ LẤY' AND MA_LOAI_VE = 'L' AND MONTH(NGAY_RA) BETWEEN " + thangBD + " AND " + thangKT;
            String sqlSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL, VE_XE as VX\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO and QL.BIEN_SO_XE = VX.BIEN_SO_XE and vx.MA_LOAI_VE = 'L' AND MONTH(QL.NGAY_XAY_RA) BETWEEN " + thangBD + " AND " + thangKT;
            int tienLuot = Integer.parseInt(QLXe.tinhTienLuot(sqlLuot)) - Integer.parseInt(QLXe.tienSuCo(sqlSC));
            
            //tinh tong tien thang
            String sqlTongSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                        "from SU_CO as SC, QL_SU_CO as QL, VE_XE as VX\n" +
                        "where QL.MA_SU_CO = SC.MA_SU_CO and QL.BIEN_SO_XE = VX.BIEN_SO_XE and MONTH(QL.NGAY_XAY_RA) BETWEEN " + thangBD + " AND " + thangKT;
            
            //tinh tong tien thang
            String sqlThang = "select count(QLVT.BIEN_SO_XE)*GT.GIA_TIEN as GIA_TIEN\n" +
                            "from QL_VE_THANG as QLVT, GIA_TIEN as GT\n" +
                            "where GT.MA_LOAI_VE = 'T' and MONTH(QLVT.NGAY_LAM_VE) BETWEEN " + thangBD + " AND " + thangKT + "\n" +
                            "GROUP BY GT.GIA_TIEN";
            //tinh tong so tien thu dc
            int tongTien = Integer.parseInt(QLXe.tienSuCo(sqlTongSC)) + Integer.parseInt(QLXe.tinhTienThang(sqlThang)) + tienLuot;
            
            //them du lieu
            jL_TongTien.setText(String.valueOf(tongTien));
            jL_TongTienLuot.setText(String.valueOf(tienLuot));
            jL_TongTienSC.setText(QLXe.tienSuCo(sqlTongSC));
            jL_TongTienThang.setText(QLXe.tinhTienThang(sqlThang));
            this.layDoanhThu(sqlTable);
        }
    }//GEN-LAST:event_btn_DoanhThuActionPerformed

    private void jT_DT_TimXeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_DT_TimXeKeyReleased
        // TODO add your handling code here:
        String bienSo = jT_DT_TimXe.getText();
        if (bienSo.equals("")){
            String sql = "SELECT LV.TEN_LOAI, VX.BIEN_SO_XE, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.GIA_TIEN" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.TINH_TRANG = N'ĐÃ LẤY'";
            this.layDoanhThu(sql);
        }
        else if (!bienSo.equals("")){
            String sql = "SELECT LV.TEN_LOAI, VX.BIEN_SO_XE, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.GIA_TIEN" 
                    + " FROM VE_XE AS VX, LOAI_VE AS LV" 
                    + " WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.TINH_TRANG = N'ĐÃ LẤY' AND VX.BIEN_SO_XE LIKE '%" + bienSo +"%'";
            this.layDoanhThu(sql);
        }
    }//GEN-LAST:event_jT_DT_TimXeKeyReleased

    private void jC_ThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_ThongKeActionPerformed
        // TODO add your handling code here:
        if(jC_ThongKe.getSelectedItem().equals("TẤT CẢ")){
            //set lua chon
            jDate_NgayThongKe.setEnabled(false);
            jDate_NgayThongKe.setDate(null);
            
            jC_Thang.setEnabled(false);
            jC_Thang.setSelectedIndex(0);
            
            jC_Quy.setEnabled(false);
            jC_Quy.setSelectedIndex(0);
            
            
        }
        else if(jC_ThongKe.getSelectedItem().equals("NGÀY")){
            //set lua chon
            jDate_NgayThongKe.setEnabled(true);
            
            
            jC_Thang.setEnabled(false);
            jC_Thang.setSelectedIndex(0);
            
            jC_Quy.setEnabled(false);
            jC_Quy.setSelectedIndex(0);
            
        }
        else if(jC_ThongKe.getSelectedItem().equals("THÁNG")){
            //set lua chon
            jDate_NgayThongKe.setEnabled(false);
            jDate_NgayThongKe.setDate(null);
            
            jC_Thang.setEnabled(true);
            
            
            jC_Quy.setEnabled(false);
            jC_Quy.setSelectedIndex(0);
            
            
        }
        else if(jC_ThongKe.getSelectedItem().equals("QUÝ")){
            //set lua chon
            jDate_NgayThongKe.setEnabled(false);
            jDate_NgayThongKe.setDate(null);
            
            jC_Thang.setEnabled(false);
            jC_Thang.setSelectedIndex(0);
            
            jC_Quy.setEnabled(true);
            
            
        }
    }//GEN-LAST:event_jC_ThongKeActionPerformed

    private void btn_HienTaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HienTaiActionPerformed
        // TODO add your handling code here:
        jP_HienTai.setVisible(true);
        jP_DoanhThu.setVisible(false);
        jP_VeThang.setVisible(false);
        jP_SuCo.setVisible(false);
        
        //chuc nang
        if(jC_ThongKe.getSelectedItem().equals("TẤT CẢ")){
            //lay thong tin cho bang Thong ke hien tai
            String sqlTable = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                            "from VE_XE as VX, LOAI_VE as LV\n" +
                            "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE";
            //lay tong so xe gui trong bai
            String sqlTongXe = "select count(MA_VE) as XE\n" +
                            "from VE_XE";
            //lay tong so xe da lay trong bai
            String sqlXeDaLay = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE TINH_TRANG = N'ĐÃ LẤY'";
            //lay tong so xe chua lay trong bai
            String sqlXeChuaLay = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE TINH_TRANG = N'CHƯA LẤY'";
            //Them du lieu
            jL_ThongKeTongSoXe.setText(QLXe.tongSoXeTrongBai(sqlTongXe));
            jL_ThongKeXeDaLay.setText(QLXe.tongSoXeTrongBai(sqlXeDaLay));
            jL_ThongKeXeChuaLay.setText(QLXe.tongSoXeTrongBai(sqlXeChuaLay));
            this.layHT(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("NGÀY")){
            //lay ngay ra
            java.sql.Date ngayRa = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayThongKe.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngayRa = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
                ngayRa = java.sql.Date.valueOf(java.time.LocalDate.now());
            }
            //lay thong tin cho bang thong ke hien tai theo ngay
            String sqlTable = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                            "from VE_XE as VX, LOAI_VE as LV\n" +
                            "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.NGAY_VAO ='" + ngayRa + "'";
            //lay tong so xe gui trong bai
            String sqlTongXe = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE NGAY_VAO = '" + ngayRa + "'";
            //lay tong so xe da lay trong bai
            String sqlXeDaLay = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE TINH_TRANG = N'ĐÃ LẤY' AND NGAY_VAO = '" + ngayRa + "'";
            //lay tong so xe chua lay trong bai
            String sqlXeChuaLay = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE TINH_TRANG = N'CHƯA LẤY' AND NGAY_VAO = '" + ngayRa + "'";
            
            //lay du lieu
            jL_ThongKeTongSoXe.setText(QLXe.tongSoXeTrongBai(sqlTongXe));
            jL_ThongKeXeDaLay.setText(QLXe.tongSoXeTrongBai(sqlXeDaLay));
            jL_ThongKeXeChuaLay.setText(QLXe.tongSoXeTrongBai(sqlXeChuaLay));
            this.layHT(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("THÁNG")){
            String thang = (String) jC_Thang.getSelectedItem();
            //lay thong tin cho bang thong ke hien tai theo ngay
            String sqlTable = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                            "from VE_XE as VX, LOAI_VE as LV\n" +
                            "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND MONTH(VX.NGAY_VAO) ='" + thang + "'";
            //lay tong so xe gui trong bai
            String sqlTongXe = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE MONTH(NGAY_VAO) = '" + thang + "'";
            //lay tong so xe da lay trong bai
            String sqlXeDaLay = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE TINH_TRANG = N'ĐÃ LẤY' AND MONTH(NGAY_VAO) = '" + thang + "'";
            //lay tong so xe chua lay trong bai
            String sqlXeChuaLay = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE TINH_TRANG = N'CHƯA LẤY' AND MONTH(NGAY_VAO) = '" + thang + "'";
            
            //lay du lieu
            jL_ThongKeTongSoXe.setText(QLXe.tongSoXeTrongBai(sqlTongXe));
            jL_ThongKeXeDaLay.setText(QLXe.tongSoXeTrongBai(sqlXeDaLay));
            jL_ThongKeXeChuaLay.setText(QLXe.tongSoXeTrongBai(sqlXeChuaLay));
            this.layHT(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("QUÝ")){
            String thangBD = "", thangKT = "";
            if (jC_Quy.getSelectedItem().equals("1")){
                thangBD = "1";
                thangKT = "3";
            }
            else if (jC_Quy.getSelectedItem().equals("2")){
                thangBD = "4";
                thangKT = "6";
            }
            else if (jC_Quy.getSelectedItem().equals("3")){
                thangBD = "7";
                thangKT = "9";
            }
            else if (jC_Quy.getSelectedItem().equals("4")){
                thangBD = "10";
                thangKT = "12";
            }
            
            //lay thong tin cho bang thong ke hien tai theo ngay
            String sqlTable = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                            "from VE_XE as VX, LOAI_VE as LV\n" +
                            "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND MONTH(VX.NGAY_VAO) BETWEEN " + thangBD + " AND " + thangKT;
            //lay tong so xe gui trong bai
            String sqlTongXe = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE MONTH(NGAY_VAO) BETWEEN " + thangBD + " AND " + thangKT;
            //lay tong so xe da lay trong bai
            String sqlXeDaLay = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE TINH_TRANG = N'ĐÃ LẤY' AND MONTH(NGAY_VAO) BETWEEN " + thangBD + " AND " + thangKT;
            //lay tong so xe chua lay trong bai
            String sqlXeChuaLay = "select count(MA_VE) as XE\n" +
                            "from VE_XE\n" +
                            "WHERE TINH_TRANG = N'CHƯA LẤY' AND MONTH(NGAY_VAO) BETWEEN " + thangBD + " AND " + thangKT;
            
            //lay du lieu
            jL_ThongKeTongSoXe.setText(QLXe.tongSoXeTrongBai(sqlTongXe));
            jL_ThongKeXeDaLay.setText(QLXe.tongSoXeTrongBai(sqlXeDaLay));
            jL_ThongKeXeChuaLay.setText(QLXe.tongSoXeTrongBai(sqlXeChuaLay));
            this.layHT(sqlTable);
        }
    }//GEN-LAST:event_btn_HienTaiActionPerformed

    private void btn_VeThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VeThangActionPerformed
        // TODO add your handling code here:
        jP_VeThang.setVisible(true);
        jP_HienTai.setVisible(false);
        jP_DoanhThu.setVisible(false);
        jP_SuCo.setVisible(false);
        
        //chuc nang
        if(jC_ThongKe.getSelectedItem().equals("TẤT CẢ")){
            //lay thong tin cho bagn thong ke
            String sqlTable = "select BIEN_SO_XE, MA_SINH_VIEN, NGAY_LAM_VE, NGAY_HET_HAN\n" +
                            "from QL_VE_THANG";
            
            //Tinh tong xe dang ki thang
            String sqlTongDKT = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG";
            
            //Tinh tong xe dang ki thang con han
            String sqlTongDKT_CH = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where TRANG_THAI = N'CÒN HẠN'";
            
            //Tinh tong xe dang ki thang het han
            String sqlTongDKT_HH = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where TRANG_THAI = N'HẾT HẠN'";
            
            //lay du lieu
            this.layVeThang(sqlTable);
            jL_XeDKT.setText(QLXe.tongXeDKT(sqlTongDKT));
            jL_XeCH.setText(QLXe.tongXeDKT(sqlTongDKT_CH));
            jL_XeHH.setText(QLXe.tongXeDKT(sqlTongDKT_HH));
            
        }
        else if(jC_ThongKe.getSelectedItem().equals("NGÀY")){
            //lay ngay ra
            java.sql.Date ngayRa = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayThongKe.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngayRa = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
                ngayRa = java.sql.Date.valueOf(java.time.LocalDate.now());
            }
            
            //lay thong tin cho bagn thong ke
            String sqlTable = "select BIEN_SO_XE, MA_SINH_VIEN, NGAY_LAM_VE, NGAY_HET_HAN\n" +
                            "from QL_VE_THANG\n" +
                            "WHERE NGAY_LAM_VE = '" + ngayRa + "'";
            
            //Tinh tong xe dang ki thang
            String sqlTongDKT = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where NGAY_LAM_VE = '" + ngayRa + "'";
            
            //Tinh tong xe dang ki thang con han
            String sqlTongDKT_CH = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where TRANG_THAI = N'CÒN HẠN' AND NGAY_LAM_VE = '" + ngayRa + "'";
            
            //Tinh tong xe dang ki thang het han
            String sqlTongDKT_HH = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where TRANG_THAI = N'HẾT HẠN' AND NGAY_LAM_VE = '" + ngayRa + "'";
            
            //lay du lieu
            this.layVeThang(sqlTable);
            jL_XeDKT.setText(QLXe.tongXeDKT(sqlTongDKT));
            jL_XeCH.setText(QLXe.tongXeDKT(sqlTongDKT_CH));
            jL_XeHH.setText(QLXe.tongXeDKT(sqlTongDKT_HH));
        }
        else if(jC_ThongKe.getSelectedItem().equals("THÁNG")){
            String thang = (String) jC_Thang.getSelectedItem();
            
            //lay thong tin cho bagn thong ke
            String sqlTable = "select BIEN_SO_XE, MA_SINH_VIEN, NGAY_LAM_VE, NGAY_HET_HAN\n" +
                            "from QL_VE_THANG\n" +
                            "WHERE MONTH(NGAY_LAM_VE) = '" + thang + "'";
            
            //Tinh tong xe dang ki thang
            String sqlTongDKT = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where MONTH(NGAY_LAM_VE) = '" + thang + "'";
            
            //Tinh tong xe dang ki thang con han
            String sqlTongDKT_CH = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where TRANG_THAI = N'CÒN HẠN' AND MONTH(NGAY_LAM_VE) = '" + thang + "'";
            
            //Tinh tong xe dang ki thang het han
            String sqlTongDKT_HH = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where TRANG_THAI = N'HẾT HẠN' AND MONTH(NGAY_LAM_VE) = '" + thang + "'";
            
            //lay du lieu
            this.layVeThang(sqlTable);
            jL_XeDKT.setText(QLXe.tongXeDKT(sqlTongDKT));
            jL_XeCH.setText(QLXe.tongXeDKT(sqlTongDKT_CH));
            jL_XeHH.setText(QLXe.tongXeDKT(sqlTongDKT_HH));
        }
        else if(jC_ThongKe.getSelectedItem().equals("QUÝ")){
            String thangBD = "", thangKT = "";
            if (jC_Quy.getSelectedItem().equals("1")){
                thangBD = "1";
                thangKT = "3";
            }
            else if (jC_Quy.getSelectedItem().equals("2")){
                thangBD = "4";
                thangKT = "6";
            }
            else if (jC_Quy.getSelectedItem().equals("3")){
                thangBD = "7";
                thangKT = "9";
            }
            else if (jC_Quy.getSelectedItem().equals("4")){
                thangBD = "10";
                thangKT = "12";
            }
            
            //lay thong tin cho bagn thong ke
            String sqlTable = "select BIEN_SO_XE, MA_SINH_VIEN, NGAY_LAM_VE, NGAY_HET_HAN\n" +
                            "from QL_VE_THANG\n" +
                            "WHERE MONTH(NGAY_LAM_VE) BETWEEN " + thangBD + " AND " + thangKT;
            
            //Tinh tong xe dang ki thang
            String sqlTongDKT = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where MONTH(NGAY_LAM_VE) BETWEEN " + thangBD + " AND " + thangKT;
            
            //Tinh tong xe dang ki thang con han
            String sqlTongDKT_CH = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where TRANG_THAI = N'CÒN HẠN' AND MONTH(NGAY_LAM_VE) BETWEEN " + thangBD + " AND " + thangKT;
            
            //Tinh tong xe dang ki thang het han
            String sqlTongDKT_HH = "select COUNT(BIEN_SO_XE) as VE_THANG\n" +
                                "from QL_VE_THANG\n" +
                                "where TRANG_THAI = N'HẾT HẠN' AND MONTH(NGAY_LAM_VE) BETWEEN " + thangBD + " AND " + thangKT;
            
            //lay du lieu
            this.layVeThang(sqlTable);
            jL_XeDKT.setText(QLXe.tongXeDKT(sqlTongDKT));
            jL_XeCH.setText(QLXe.tongXeDKT(sqlTongDKT_CH));
            jL_XeHH.setText(QLXe.tongXeDKT(sqlTongDKT_HH));
        }
    }//GEN-LAST:event_btn_VeThangActionPerformed

    private void btn_SuCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuCoActionPerformed
        // TODO add your handling code here:
        jP_SuCo.setVisible(true);
        jP_VeThang.setVisible(false);
        jP_HienTai.setVisible(false);
        jP_DoanhThu.setVisible(false);
        
        //chuc nang
        if(jC_ThongKe.getSelectedItem().equals("TẤT CẢ")){
            //lay thong tin cho cot thong ke
            String sqlTable = "select QLSC.BIEN_SO_XE, SC.TEN_SU_CO, QLSC.NGAY_XAY_RA, SC.XU_PHAT\n" +
                            "from QL_SU_CO as QLSC, SU_CO as SC\n" +
                            "WHERE QLSC.MA_SU_CO = SC.MA_SU_CO";
            
            //lay thong ke tien su co
            String sqlTienSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO";
            
            //lay thong ke tong so su cco da xay ra
            String sqltongSC = "select COUNT(MA_SU_CO) as SU_CO\n" +
                            "from QL_SU_CO";
            
            //lay du lieu
            this.laySuCo(sqlTable);
            jL_SuCo.setText(QLXe.tienSuCo(sqlTienSC));
            jL_TongSC.setText(QLXe.tongSuCo(sqltongSC));
        }
        else if(jC_ThongKe.getSelectedItem().equals("NGÀY")){
            //lay ngay ra
            java.sql.Date ngayRa = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayThongKe.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngayRa = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
                ngayRa = java.sql.Date.valueOf(java.time.LocalDate.now());
            }
            
            //lay thong tin cho cot thong ke
            String sqlTable = "select QLSC.BIEN_SO_XE, SC.TEN_SU_CO, QLSC.NGAY_XAY_RA, SC.XU_PHAT\n" +
                            "from QL_SU_CO as QLSC, SU_CO as SC\n" +
                            "WHERE QLSC.MA_SU_CO = SC.MA_SU_CO AND NGAY_XAY_RA = '" + ngayRa + "'";
            
            //lay thong ke tien su co
            String sqlTienSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO AND QL.NGAY_XAY_RA = '" + ngayRa + "'";
            
            //lay thong ke tong so su cco da xay ra
            String sqltongSC = "select COUNT(MA_SU_CO) as SU_CO\n" +
                            "from QL_SU_CO\n" +
                            "where NGAY_XAY_RA = '" + ngayRa + "'";
            
            //lay du lieu
            this.laySuCo(sqlTable);
            jL_SuCo.setText(QLXe.tienSuCo(sqlTienSC));
            jL_TongSC.setText(QLXe.tongSuCo(sqltongSC));
        }
        else if(jC_ThongKe.getSelectedItem().equals("THÁNG")){
            String thang = (String) jC_Thang.getSelectedItem();
            
            //lay thong tin cho cot thong ke
            String sqlTable = "select QLSC.BIEN_SO_XE, SC.TEN_SU_CO, QLSC.NGAY_XAY_RA, SC.XU_PHAT\n" +
                            "from QL_SU_CO as QLSC, SU_CO as SC\n" +
                            "WHERE QLSC.MA_SU_CO = SC.MA_SU_CO AND MONTH(NGAY_XAY_RA) = '" + thang + "'";
            
            //lay thong ke tien su co
            String sqlTienSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO AND MONTH(QL.NGAY_XAY_RA) = '" + thang + "'";
            
            //lay thong ke tong so su cco da xay ra
            String sqltongSC = "select COUNT(MA_SU_CO) as SU_CO\n" +
                            "from QL_SU_CO\n" +
                            "where MONTH(NGAY_XAY_RA) = '" + thang + "'";
            
            //lay du lieu
            this.laySuCo(sqlTable);
            jL_SuCo.setText(QLXe.tienSuCo(sqlTienSC));
            jL_TongSC.setText(QLXe.tongSuCo(sqltongSC));
        }
        else if(jC_ThongKe.getSelectedItem().equals("QUÝ")){
            String thangBD = "", thangKT = "";
            if (jC_Quy.getSelectedItem().equals("1")){
                thangBD = "1";
                thangKT = "3";
            }
            else if (jC_Quy.getSelectedItem().equals("2")){
                thangBD = "4";
                thangKT = "6";
            }
            else if (jC_Quy.getSelectedItem().equals("3")){
                thangBD = "7";
                thangKT = "9";
            }
            else if (jC_Quy.getSelectedItem().equals("4")){
                thangBD = "10";
                thangKT = "12";
            }
            
            //lay thong tin cho cot thong ke
            String sqlTable = "select QLSC.BIEN_SO_XE, SC.TEN_SU_CO, QLSC.NGAY_XAY_RA, SC.XU_PHAT\n" +
                            "from QL_SU_CO as QLSC, SU_CO as SC\n" +
                            "WHERE QLSC.MA_SU_CO = SC.MA_SU_CO AND MONTH(NGAY_XAY_RA) BETWEEN " + thangBD + " AND " + thangKT;
            
            //lay thong ke tien su co
            String sqlTienSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO AND MONTH(QL.NGAY_XAY_RA) BETWEEN " + thangBD + " AND " + thangKT;
            
            //lay thong ke tong so su cco da xay ra
            String sqltongSC = "select COUNT(MA_SU_CO) as SU_CO\n" +
                            "from QL_SU_CO\n" +
                            "where MONTH(NGAY_XAY_RA) BETWEEN " + thangBD + " AND " + thangKT;
            
            //lay du lieu
            this.laySuCo(sqlTable);
            jL_SuCo.setText(QLXe.tienSuCo(sqlTienSC));
            jL_TongSC.setText(QLXe.tongSuCo(sqltongSC));
        }
    }//GEN-LAST:event_btn_SuCoActionPerformed

    private void jC_SuCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_SuCoActionPerformed
        // TODO add your handling code here:
        String luaChon = (String) jC_SuCo.getSelectedItem();
        String maSC = QLXe.layMaLoi(luaChon);
        if(luaChon.equals("TẤT CẢ")){
            maSC = "in (select MA_SU_CO from SU_CO)";
        }
        else{
            maSC = "='" + maSC + "'";
        }
        //chuc nang
        if(jC_ThongKe.getSelectedItem().equals("TẤT CẢ")){
            //lay thong tin cho cot thong ke
            String sqlTable = "select QLSC.BIEN_SO_XE, SC.TEN_SU_CO, QLSC.NGAY_XAY_RA, SC.XU_PHAT\n" +
                            "from QL_SU_CO as QLSC, SU_CO as SC\n" +
                            "WHERE QLSC.MA_SU_CO = SC.MA_SU_CO AND QLSC.MA_SU_CO " + maSC;
            
            //lay thong ke tien su co
            String sqlTienSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO AND QL.MA_SU_CO " + maSC;
            
            //lay thong ke tong so su cco da xay ra
            String sqltongSC = "select COUNT(MA_SU_CO) as SU_CO\n" +
                            "from QL_SU_CO\n" +
                            "WHERE MA_SU_CO " + maSC;
            
            //lay du lieu
            this.laySuCo(sqlTable);
            jL_SuCo.setText(QLXe.tienSuCo(sqlTienSC));
            jL_TongSC.setText(QLXe.tongSuCo(sqltongSC));
        }
        else if(jC_ThongKe.getSelectedItem().equals("NGÀY")){
            //lay ngay ra
            java.sql.Date ngayRa = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayThongKe.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngayRa = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
                ngayRa = java.sql.Date.valueOf(java.time.LocalDate.now());
            }
            
            //lay thong tin cho cot thong ke
            String sqlTable = "select QLSC.BIEN_SO_XE, SC.TEN_SU_CO, QLSC.NGAY_XAY_RA, SC.XU_PHAT\n" +
                            "from QL_SU_CO as QLSC, SU_CO as SC\n" +
                            "WHERE QLSC.MA_SU_CO = SC.MA_SU_CO AND NGAY_XAY_RA = '" + ngayRa + "' AND QLSC.MA_SU_CO " + maSC;
            
            //lay thong ke tien su co
            String sqlTienSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO AND QL.NGAY_XAY_RA = '" + ngayRa + "'AND QL.MA_SU_CO " + maSC;
            
            //lay thong ke tong so su cco da xay ra
            String sqltongSC = "select COUNT(MA_SU_CO) as SU_CO\n" +
                            "from QL_SU_CO\n" +
                            "where NGAY_XAY_RA = '" + ngayRa + "' AND MA_SU_CO " + maSC;
            
            //lay du lieu
            this.laySuCo(sqlTable);
            jL_SuCo.setText(QLXe.tienSuCo(sqlTienSC));
            jL_TongSC.setText(QLXe.tongSuCo(sqltongSC));
        }
        else if(jC_ThongKe.getSelectedItem().equals("THÁNG")){
            String thang = (String) jC_Thang.getSelectedItem();
            
            //lay thong tin cho cot thong ke
            String sqlTable = "select QLSC.BIEN_SO_XE, SC.TEN_SU_CO, QLSC.NGAY_XAY_RA, SC.XU_PHAT\n" +
                            "from QL_SU_CO as QLSC, SU_CO as SC\n" +
                            "WHERE QLSC.MA_SU_CO = SC.MA_SU_CO AND MONTH(NGAY_XAY_RA) = '" + thang + "' AND QLSC.MA_SU_CO " + maSC;
            
            //lay thong ke tien su co
            String sqlTienSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO AND MONTH(QL.NGAY_XAY_RA) = '" + thang + "' AND QL.MA_SU_CO " + maSC;
            
            //lay thong ke tong so su cco da xay ra
            String sqltongSC = "select COUNT(MA_SU_CO) as SU_CO\n" +
                            "from QL_SU_CO\n" +
                            "where MONTH(NGAY_XAY_RA) = '" + thang + "' AND MA_SU_CO " + maSC;
            
            //lay du lieu
            this.laySuCo(sqlTable);
            jL_SuCo.setText(QLXe.tienSuCo(sqlTienSC));
            jL_TongSC.setText(QLXe.tongSuCo(sqltongSC));
        }
        else if(jC_ThongKe.getSelectedItem().equals("QUÝ")){
            String thangBD = "", thangKT = "";
            if (jC_Quy.getSelectedItem().equals("1")){
                thangBD = "1";
                thangKT = "3";
            }
            else if (jC_Quy.getSelectedItem().equals("2")){
                thangBD = "4";
                thangKT = "6";
            }
            else if (jC_Quy.getSelectedItem().equals("3")){
                thangBD = "7";
                thangKT = "9";
            }
            else if (jC_Quy.getSelectedItem().equals("4")){
                thangBD = "10";
                thangKT = "12";
            }
            
            //lay thong tin cho cot thong ke
            String sqlTable = "select QLSC.BIEN_SO_XE, SC.TEN_SU_CO, QLSC.NGAY_XAY_RA, SC.XU_PHAT\n" +
                            "from QL_SU_CO as QLSC, SU_CO as SC\n" +
                            "WHERE QLSC.MA_SU_CO = SC.MA_SU_CO AND MONTH(NGAY_XAY_RA) BETWEEN " + thangBD + " AND " + thangKT + " AND QLSC.MA_SU_CO " + maSC;
            
            //lay thong ke tien su co
            String sqlTienSC = "select sum(XU_PHAT) as XU_PHAT\n" +
                            "from SU_CO as SC, QL_SU_CO as QL\n" +
                            "where QL.MA_SU_CO = SC.MA_SU_CO AND MONTH(QL.NGAY_XAY_RA) BETWEEN " + thangBD + " AND " + thangKT + " AND QL.MA_SU_CO " + maSC;
            
            //lay thong ke tong so su cco da xay ra
            String sqltongSC = "select COUNT(MA_SU_CO) as SU_CO\n" +
                            "from QL_SU_CO\n" +
                            "where MONTH(NGAY_XAY_RA) BETWEEN " + thangBD + " AND " + thangKT + " AND MA_SU_CO " + maSC;
            
            //lay du lieu
            this.laySuCo(sqlTable);
            jL_SuCo.setText(QLXe.tienSuCo(sqlTienSC));
            jL_TongSC.setText(QLXe.tongSuCo(sqltongSC));
        }
    }//GEN-LAST:event_jC_SuCoActionPerformed

    private void jC_TinhTrangDKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_TinhTrangDKTActionPerformed
        // TODO add your handling code here:
        String trangThai = (String) jC_TinhTrangDKT.getSelectedItem();
        if(trangThai.equals("TẤT CẢ")){
            trangThai = "CÒN HẠN' OR TRANG_THAI = N'HẾT HẠN";
        }
                
        //chuc nang
        if(jC_ThongKe.getSelectedItem().equals("TẤT CẢ")){
            //lay thong tin cho bagn thong ke
            String sqlTable = "select BIEN_SO_XE, MA_SINH_VIEN, NGAY_LAM_VE, NGAY_HET_HAN\n" +
                            "from QL_VE_THANG\n" +
                            "WHERE TRANG_THAI =N'" + trangThai + "'";

            //lay du lieu
            this.layVeThang(sqlTable); 
        }
        else if(jC_ThongKe.getSelectedItem().equals("NGÀY")){
            //lay ngay ra
            java.sql.Date ngayRa = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayThongKe.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngayRa = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
                ngayRa = java.sql.Date.valueOf(java.time.LocalDate.now());
            }
            
            //lay thong tin cho bagn thong ke
            String sqlTable = "select BIEN_SO_XE, MA_SINH_VIEN, NGAY_LAM_VE, NGAY_HET_HAN\n" +
                            "from QL_VE_THANG\n" +
                            "WHERE NGAY_LAM_VE = '" + ngayRa + "' AND TRANG_THAI = N'" + trangThai + "'";

            //lay du lieu
            this.layVeThang(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("THÁNG")){
            String thang = (String) jC_Thang.getSelectedItem();
            
            //lay thong tin cho bagn thong ke
            String sqlTable = "select BIEN_SO_XE, MA_SINH_VIEN, NGAY_LAM_VE, NGAY_HET_HAN\n" +
                            "from QL_VE_THANG\n" +
                            "WHERE MONTH(NGAY_LAM_VE) = '" + thang + "' AND TRANG_THAI = N'" + trangThai + "'";
            
            //lay du lieu
            this.layVeThang(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("QUÝ")){
            String thangBD = "", thangKT = "";
            if (jC_Quy.getSelectedItem().equals("1")){
                thangBD = "1";
                thangKT = "3";
            }
            else if (jC_Quy.getSelectedItem().equals("2")){
                thangBD = "4";
                thangKT = "6";
            }
            else if (jC_Quy.getSelectedItem().equals("3")){
                thangBD = "7";
                thangKT = "9";
            }
            else if (jC_Quy.getSelectedItem().equals("4")){
                thangBD = "10";
                thangKT = "12";
            }
            
            //lay thong tin cho bagn thong ke
            String sqlTable = "select BIEN_SO_XE, MA_SINH_VIEN, NGAY_LAM_VE, NGAY_HET_HAN\n" +
                            "from QL_VE_THANG\n" +
                            "WHERE MONTH(NGAY_LAM_VE) BETWEEN " + thangBD + " AND " + thangKT + " AND TRANG_THAI = N'" + trangThai + "'";
            
            //lay du lieu
            this.layVeThang(sqlTable);
        }
    }//GEN-LAST:event_jC_TinhTrangDKTActionPerformed

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

    private void jT_MaSVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_MaSVKeyPressed
        // TODO add your handling code here:
        String maSV = jT_MaSV.getText();
        String hoTen = "";
        String lop = "";
        Date namSinh = null;
        String queQuan = "";
        String gioiTinh = "";
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(QLXe.checkSV(maSV) == 1){
                Connection ketNoi = KetNoiCSDL.ketNoi();
                String sql = "SELECT HO_TEN, LOP, NAM_SINH, QUE_QUAN, GIOI_TINH FROM SINH_VIEN WHERE MA_SINH_VIEN = '" + maSV + "'";
                try {
                    PreparedStatement pr = ketNoi.prepareStatement(sql);
                    ResultSet rs = pr.executeQuery();
                    while (rs.next()){
                        hoTen = rs.getString("HO_TEN");
                        lop = (rs.getString("LOP"));
                        namSinh = rs.getDate("NAM_SINH");
                        queQuan = rs.getString("QUE_QUAN");
                        gioiTinh = rs.getString("GIOI_TINH");
                    }
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
                jT_HoTen.setText(hoTen);
                jT_Lop.setText(lop);
                jDate_NgaySV.setDate(namSinh);
                jT_QueQuan.setText(queQuan);
                jC_GioiTinh.setSelectedItem(gioiTinh);
            }
            else{
                jT_HoTen.setText("");
                jT_Lop.setText("");
                jDate_NgaySV.setDate(null);
                jT_QueQuan.setText("");
                jC_GioiTinh.setSelectedItem(0);
                jT_HoTen.setEditable(true);
                jT_Lop.setEditable(true);
                jDate_NgaySV.setEnabled(true);
                jT_QueQuan.setEditable(true);
                jC_GioiTinh.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jT_MaSVKeyPressed

    private void jTable_KhungGioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_KhungGioMousePressed
        // TODO add your handling code here:
        String khungGio = (String) jTable_KhungGio.getValueAt(jTable_KhungGio.getSelectedRow(), 1);
        Time gioBD = (Time) jTable_KhungGio.getValueAt(jTable_KhungGio.getSelectedRow(), 2);
        Time gioKT = (Time) jTable_KhungGio.getValueAt(jTable_KhungGio.getSelectedRow(), 3);
        int giaTien = (int) jTable_KhungGio.getValueAt(jTable_KhungGio.getSelectedRow(), 4);
        
        jT_KhungGio.setText(khungGio);
        jT_GioBD.setText(String.valueOf(gioBD));
        jT_GioKT.setText(String.valueOf(gioKT));
        jT_TienKhungGio.setText(String.valueOf(giaTien));
        
    }//GEN-LAST:event_jTable_KhungGioMousePressed

    private void btn_DatLaiVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DatLaiVeActionPerformed
        // TODO add your handling code here:
        
        
        String khungGio = jT_KhungGio.getText();
        if(khungGio.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa lụa chọn khung giờ để sửa. Xin vui lòng chọn khung giờ bên dưới bảng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            btn_DatLaiVe.setEnabled(true);
            btn_LuuVe.setEnabled(false);

            jT_GioBD.setEditable(false);
            jT_GioKT.setEditable(false);
            jT_TienKhungGio.setEditable(false);
        }
        else{
            btn_DatLaiVe.setEnabled(false);
            btn_LuuVe.setEnabled(true);

            jT_GioBD.setEditable(true);
            jT_GioKT.setEditable(true);
            jT_TienKhungGio.setEditable(true);
        }
    }//GEN-LAST:event_btn_DatLaiVeActionPerformed

    private void btn_LuuVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuVeActionPerformed
        // TODO add your handling code here:
        btn_DatLaiVe.setEnabled(true);
        btn_LuuVe.setEnabled(false);
        
            String tenKH = jT_KhungGio.getText();
            String loaiVe = (String) jTable_KhungGio.getValueAt(jTable_KhungGio.getSelectedRow(), 0);
            String giaTienString = jT_TienKhungGio.getText();
            int giaTien = Integer.parseInt(giaTienString);
            Time gioBD = Time.valueOf(jT_GioBD.getText());
            Time gioKT = Time.valueOf(jT_GioKT.getText());
            QLXe.capNhatKhungGio(tenKH, gioBD, gioKT);
            QLXe.capNhatGiaTienKhungGio(loaiVe, tenKH, giaTien);
            JOptionPane.showMessageDialog(null, "Thay đổi thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            this.layKhungGio();
    }//GEN-LAST:event_btn_LuuVeActionPerformed

    private void btn_HuyDatLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyDatLaiActionPerformed
        // TODO add your handling code here:
        btn_DatLaiVe.setEnabled(true);
        btn_LuuVe.setEnabled(false);
        
        jT_GioBD.setEditable(false);
        jT_GioKT.setEditable(false);
        jT_TienKhungGio.setEditable(false);
        
        jT_KhungGio.setText("");
        jT_GioBD.setText(null);
        jT_GioKT.setText(null);
        jT_TienKhungGio.setText("");
    }//GEN-LAST:event_btn_HuyDatLaiActionPerformed

    private void jC_CaTruc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_CaTruc1ActionPerformed
        // TODO add your handling code here:
        String tenCa = (String) jC_CaTruc1.getSelectedItem();
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT GIO_BAT_DAU, GIO_KET_THUC FROM CA_TRUC WHERE TEN_CA = N'" + tenCa + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                jT_CT_BD.setText(String.valueOf(rs.getTime("GIO_BAT_DAU")));
                jT_CT_KT.setText(String.valueOf(rs.getTime("GIO_KET_THUC")));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jC_CaTruc1ActionPerformed

    private void btn_SuaCaTruc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaCaTruc1ActionPerformed
        // TODO add your handling code here:
        btn_SuaCaTruc1.setEnabled(false);
        btn_LuuCaTruc1.setEnabled(true);
        jC_CaTruc1.setEnabled(false);
        jT_CT_BD.setEditable(true);
        jT_CT_KT.setEditable(true);
    }//GEN-LAST:event_btn_SuaCaTruc1ActionPerformed

    private void btn_LuuCaTruc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuCaTruc1ActionPerformed
        // TODO add your handling code here:
        btn_SuaCaTruc1.setEnabled(true);
        btn_LuuCaTruc1.setEnabled(false);
        jC_CaTruc1.setEnabled(true);
        jT_CT_BD.setEditable(false);
        jT_CT_KT.setEditable(false);
        
        try{
            String tenCa = (String) jC_CaTruc1.getSelectedItem();
            Time gioBD = Time.valueOf(jT_CT_BD.getText());
            Time gioKT = Time.valueOf(jT_CT_KT.getText());
            QLXe.capNhatKhungGioCaTruc(tenCa, gioBD, gioKT);
            this.layTTCaTruc();
            JOptionPane.showMessageDialog(null, "Thay đổi thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Thay đổi thất bại. Xin nhập đúng định dạng giờ làm (HH:mm:ss).", "Thông báo", JOptionPane.WARNING_MESSAGE);
            btn_SuaCaTruc1.setEnabled(false);
            btn_LuuCaTruc1.setEnabled(true);
            jC_CaTruc1.setEnabled(false);
            jT_CT_BD.setEditable(true);
            jT_CT_KT.setEditable(true);
        }
        
    }//GEN-LAST:event_btn_LuuCaTruc1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        btn_SuaCaTruc1.setEnabled(true);
        btn_LuuCaTruc1.setEnabled(false);
        jC_CaTruc1.setEnabled(true);
        jT_CT_BD.setEditable(false);
        jT_CT_KT.setEditable(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTable_PhanCongTrucMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PhanCongTrucMousePressed
        // TODO add your handling code here:
        String caTruc = (String) jTable_PhanCongTruc.getValueAt(jTable_PhanCongTruc.getSelectedRow(), 0);
        String maNV = (String) jTable_PhanCongTruc.getValueAt(jTable_PhanCongTruc.getSelectedRow(), 1);
        String tenNV = (String) jTable_PhanCongTruc.getValueAt(jTable_PhanCongTruc.getSelectedRow(), 2);
        java.sql.Date ngayLam = null;
        try {
            String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jTable_PhanCongTruc.getValueAt(jTable_PhanCongTruc.getSelectedRow(), 3));
            java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            ngayLam = new java.sql.Date(tmp.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        jC_CaTruc.setSelectedItem(caTruc);
        jT_MaNVTruc.setText(maNV);
        jT_TenNVTruc.setText(tenNV);
        jDate_NgayTruc.setDate(ngayLam);
        
        btn_XoaCaTruc.setEnabled(true);
        btn_ThemCaTruc.setEnabled(false);
    }//GEN-LAST:event_jTable_PhanCongTrucMousePressed

    private void btn_XoaCaTrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaCaTrucActionPerformed
        // TODO add your handling code here:
        String tenCaTruc = (String) jC_CaTruc.getSelectedItem();
        String maNV = jT_MaNVTruc.getText();
        java.sql.Date ngayLam = null;
        try {
            String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayTruc.getDate());
            java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            ngayLam = new java.sql.Date(tmp.getTime());
        }
        catch (Exception e) {
        }
        String maCa = QLXe.layMaCaTruc(maNV, ngayLam);
        
        if (maNV.equals("")){
            JOptionPane.showMessageDialog(null, "Không tồn tại mã nhân viên muốn xóa.", "", JOptionPane.WARNING_MESSAGE);
        }
        else{
            int check = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION){
                QLXe.xoaCaTruc(maCa, ngayLam);
                JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                this.layTTCaTruc();
                btn_XoaCaTruc.setEnabled(false);
                btn_ThemCaTruc.setEnabled(true);
                
                jC_CaTruc.setSelectedIndex(0);
                jT_MaNVTruc.setText("");
                jT_TenNVTruc.setText("");
                jDate_NgayTruc.setDate(null);
                
                jT_MaNVTruc.setEditable(false);
                jT_TenNVTruc.setEditable(false);
                jDate_NgayTruc.setEnabled(false);
            }
            else if (check == JOptionPane.NO_OPTION){
                btn_XoaCaTruc.setEnabled(true);
                btn_ThemCaTruc.setEnabled(false);
            } 
        }
        
    }//GEN-LAST:event_btn_XoaCaTrucActionPerformed

    private void btn_ThemCaTrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemCaTrucActionPerformed
        // TODO add your handling code here:
        btn_ThemCaTruc.setEnabled(false);
        btn_XacNhanThemCT.setEnabled(true);
        jC_CaTruc.setSelectedIndex(0);
        jT_MaNVTruc.setText("");
        jT_TenNVTruc.setText("");
        jDate_NgayTruc.setDate(null);
        
        jT_MaNVTruc.setEditable(true);
        jT_TenNVTruc.setEditable(false);
        jDate_NgayTruc.setEnabled(true);
        
    }//GEN-LAST:event_btn_ThemCaTrucActionPerformed

    private void btn_HuyThaoTac_CTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyThaoTac_CTActionPerformed
        // TODO add your handling code here:
        jC_CaTruc.setSelectedIndex(0);
        jT_MaNVTruc.setText("");
        jT_TenNVTruc.setText("");
        jDate_NgayTruc.setDate(null);
        
        jT_MaNVTruc.setEditable(false);
        jT_TenNVTruc.setEditable(false);
        jDate_NgayTruc.setEnabled(false);
        
        btn_XoaCaTruc.setEnabled(false);
        btn_ThemCaTruc.setEnabled(true);
        btn_XacNhanThemCT.setEnabled(false);
    }//GEN-LAST:event_btn_HuyThaoTac_CTActionPerformed

    private void btn_XacNhanThemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XacNhanThemCTActionPerformed
        // TODO add your handling code here:
        //lay du lieu
        String maNV = jT_MaNVTruc.getText();
        java.sql.Date ngayLam = null;
        try {
            String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayTruc.getDate());
            java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            ngayLam = new java.sql.Date(tmp.getTime());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ngày làm không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        
        //chuc nang
        if(maNV.equals("")){
            JOptionPane.showMessageDialog(null, "Mã nhân viên không được để trống.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String tenCa = (String) jC_CaTruc.getSelectedItem();
            String maCa = QLXe.layMaCaTrucTuTenCa(tenCa);
            QLXe.themCaTruc(maCa, ngayLam, maNV);
            JOptionPane.showMessageDialog(null, "Thêm thành công.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            this.layTTCaTruc();
            
            //set su kien
            jC_CaTruc.setSelectedIndex(0);
            jT_MaNVTruc.setText("");
            jT_TenNVTruc.setText("");
            jDate_NgayTruc.setDate(null);

            btn_XacNhanThemCT.setEnabled(false);
            btn_ThemCaTruc.setEnabled(true);
            jT_MaNVTruc.setEditable(false);
            jT_TenNVTruc.setEditable(false);
            jDate_NgayTruc.setEnabled(false);
        }
        
    }//GEN-LAST:event_btn_XacNhanThemCTActionPerformed

    private void jT_MaNVTrucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_MaNVTrucKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER){
            String maNV = jT_MaNVTruc.getText();
            int check = thaoTac.checkIDNV(maNV);
            if(check == 1){
                jT_TenNVTruc.setText(thaoTac.layTenNV(maNV));
            }
            else if (check == 0){
                JOptionPane.showMessageDialog(null, "Nhân viên này không tồn tại. Xin nhập lại mã nhân viên.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_jT_MaNVTrucKeyPressed

    private void jC_TinhTrangGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_TinhTrangGuiActionPerformed
        // TODO add your handling code here:
        String trangThai = (String) jC_TinhTrangGui.getSelectedItem();
        if(trangThai.equals("TẤT CẢ")){
            trangThai = "ĐÃ LẤY' OR TINH_TRANG = N'CHƯA LẤY";
        }
        if(jC_ThongKe.getSelectedItem().equals("TẤT CẢ")){
            //lay thong tin cho bang Thong ke hien tai
            String sqlTable = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                            "from VE_XE as VX, LOAI_VE as LV\n" +
                            "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND (TINH_TRANG =N'" + trangThai + "')";
            
            //Them du lieu
            this.layHT(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("NGÀY")){
            //lay ngay ra
            java.sql.Date ngayRa = null;
            try {
                String ngay = new SimpleDateFormat("yyyy-MM-dd").format(jDate_NgayThongKe.getDate());
                java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
                ngayRa = new java.sql.Date(tmp.getTime());
            }
            catch (Exception e) {
                ngayRa = java.sql.Date.valueOf(java.time.LocalDate.now());
            }
            //lay thong tin cho bang thong ke hien tai theo ngay
            String sqlTable = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                            "from VE_XE as VX, LOAI_VE as LV\n" +
                            "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.NGAY_VAO ='" + ngayRa + "' AND TINH_TRANG =N'" + trangThai + "'";
            
            //lay du lieu
            this.layHT(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("THÁNG")){
            String thang = (String) jC_Thang.getSelectedItem();
            //lay thong tin cho bang thong ke hien tai theo ngay
            String sqlTable = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                            "from VE_XE as VX, LOAI_VE as LV\n" +
                            "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND MONTH(VX.NGAY_VAO) ='" + thang + "' AND TINH_TRANG =N'" + trangThai + "'";
            
            //lay du lieu
            this.layHT(sqlTable);
        }
        else if(jC_ThongKe.getSelectedItem().equals("QUÝ")){
            String thangBD = "", thangKT = "";
            if (jC_Quy.getSelectedItem().equals("1")){
                thangBD = "1";
                thangKT = "3";
            }
            else if (jC_Quy.getSelectedItem().equals("2")){
                thangBD = "4";
                thangKT = "6";
            }
            else if (jC_Quy.getSelectedItem().equals("3")){
                thangBD = "7";
                thangKT = "9";
            }
            else if (jC_Quy.getSelectedItem().equals("4")){
                thangBD = "10";
                thangKT = "12";
            }
            
            //lay thong tin cho bang thong ke hien tai theo ngay
            String sqlTable = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                            "from VE_XE as VX, LOAI_VE as LV\n" +
                            "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND MONTH(VX.NGAY_VAO) BETWEEN " + thangBD + " AND " + thangKT + " AND TINH_TRANG =N'" + trangThai + "'";
            
            //lay du lieu
            this.layHT(sqlTable);
        }
    }//GEN-LAST:event_jC_TinhTrangGuiActionPerformed

    private void btn_LocTheoYCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocTheoYCActionPerformed
        // TODO add your handling code here:
        String YCCaTruc = (String) jC_LocCaTruc.getSelectedItem();
        String YCNgay = (String) jC_LocNgayLam.getSelectedItem();
        Date ngayHT = Date.valueOf(LocalDate.now());
        if (YCCaTruc.equals("TẤT CẢ")){
            if(YCNgay.equals("TẤT CẢ")){
                this.layTTCaTruc();
            }
            else if(YCNgay.equals("ĐÃ LÀM")){
                String sql = "select CT.TEN_CA, NV.MA_NV, NV.HO_TEN, PC.NGAY_LAM, CT.GIO_BAT_DAU, CT.GIO_KET_THUC\n" +
                    "from CA_TRUC as CT, PHAN_CONG_TRUC as PC, NHAN_VIEN as NV\n" +
                    "where NV.MA_NV = PC.MA_NHAN_VIEN AND PC.MA_CA = CT.MA_CA AND PC.NGAY_LAM < '" + ngayHT + "'";
                this.layTTCaTrucTheoYC(sql);
            }
            else if(YCNgay.equals("CHƯA LÀM")){
                String sql = "select CT.TEN_CA, NV.MA_NV, NV.HO_TEN, PC.NGAY_LAM, CT.GIO_BAT_DAU, CT.GIO_KET_THUC\n" +
                    "from CA_TRUC as CT, PHAN_CONG_TRUC as PC, NHAN_VIEN as NV\n" +
                    "where NV.MA_NV = PC.MA_NHAN_VIEN AND PC.MA_CA = CT.MA_CA AND PC.NGAY_LAM >= '" + ngayHT + "'";
                this.layTTCaTrucTheoYC(sql);
            }
        }
        else{
            if(YCNgay.equals("TẤT CẢ")){
                String sql = "select CT.TEN_CA, NV.MA_NV, NV.HO_TEN, PC.NGAY_LAM, CT.GIO_BAT_DAU, CT.GIO_KET_THUC\n" +
                    "from CA_TRUC as CT, PHAN_CONG_TRUC as PC, NHAN_VIEN as NV\n" +
                    "where NV.MA_NV = PC.MA_NHAN_VIEN AND PC.MA_CA = CT.MA_CA AND CT.TEN_CA = '" + YCCaTruc + "'";
                this.layTTCaTrucTheoYC(sql);
            }
            else if(YCNgay.equals("ĐÃ LÀM")){
                String sql = "select CT.TEN_CA, NV.MA_NV, NV.HO_TEN, PC.NGAY_LAM, CT.GIO_BAT_DAU, CT.GIO_KET_THUC\n" +
                    "from CA_TRUC as CT, PHAN_CONG_TRUC as PC, NHAN_VIEN as NV\n" +
                    "where NV.MA_NV = PC.MA_NHAN_VIEN AND PC.MA_CA = CT.MA_CA AND PC.NGAY_LAM < '" + ngayHT + "' AND CT.TEN_CA = '" + YCCaTruc + "'";
                this.layTTCaTrucTheoYC(sql);
            }
            else if(YCNgay.equals("CHƯA LÀM")){
                String sql = "select CT.TEN_CA, NV.MA_NV, NV.HO_TEN, PC.NGAY_LAM, CT.GIO_BAT_DAU, CT.GIO_KET_THUC\n" +
                    "from CA_TRUC as CT, PHAN_CONG_TRUC as PC, NHAN_VIEN as NV\n" +
                    "where NV.MA_NV = PC.MA_NHAN_VIEN AND PC.MA_CA = CT.MA_CA AND PC.NGAY_LAM >= '" + ngayHT + "' AND CT.TEN_CA = '" + YCCaTruc + "'";
                this.layTTCaTrucTheoYC(sql);
            }
        }
    }//GEN-LAST:event_btn_LocTheoYCActionPerformed

    private void jT_HT_TimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_HT_TimKiemKeyReleased
        // TODO add your handling code here:
        String bienSo = jT_HT_TimKiem.getText();
        if (bienSo.equals("")){
            String sql = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                        "from VE_XE as VX, LOAI_VE as LV\n" +
                        "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE";
            this.layHT(sql);
        }
        else if (!bienSo.equals("")){
            String sql = "select VX.BIEN_SO_XE, LV.TEN_LOAI, VX.NGAY_VAO, VX.GIO_VAO, VX.NGAY_RA, VX.GIO_RA, VX.MA_NV_VAO, VX.MA_NV_RA\n" +
                        "from VE_XE as VX, LOAI_VE as LV\n" +
                        "WHERE VX.MA_LOAI_VE = LV.MA_LOAI_VE AND VX.BIEN_SO_XE LIKE '%" + bienSo +"%'";
            this.layHT(sql);
        }
    }//GEN-LAST:event_jT_HT_TimKiemKeyReleased

    private void btn_DT_EXtoExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DT_EXtoExcelActionPerformed
        // TODO add your handling code here:
        Date ngayHT = Date.valueOf(LocalDate.now());
        try {
            JFileChooser jFile = new JFileChooser();
            jFile.showSaveDialog(this);
            File saveFile = jFile.getSelectedFile();
            if(saveFile != null){
                saveFile = new File(saveFile.toString()+"_DoanhThu.xlsx");
                Workbook wb = new XSSFWorkbook();
                //xuat bang doanh tu
                org.apache.poi.ss.usermodel.Sheet sheetDoanhThu = wb.createSheet("DOANH THU");
                Row rowCol = sheetDoanhThu.createRow(0);
                for(int i = 0; i<jTable_TKDoanhThu.getColumnCount(); i++){
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(jTable_TKDoanhThu.getColumnName(i));
                }
                
                for(int j = 0; j<jTable_TKDoanhThu.getRowCount(); j++){
                    Row row = sheetDoanhThu.createRow(j+1);
                    for (int k = 0; k<jTable_TKDoanhThu.getColumnCount(); k++){
                        Cell cell = row.createCell(k);
                        if (jTable_TKDoanhThu.getValueAt(j, k) != null){
                            cell.setCellValue(jTable_TKDoanhThu.getValueAt(j, k).toString());
                        }
                    }
                }
                
                //Xuat hien tai
                org.apache.poi.ss.usermodel.Sheet sheetHT = wb.createSheet("THONG KE XE");
                Row rowColHT = sheetHT.createRow(0);
                for(int i = 0; i<jTable_TKHienTai.getColumnCount(); i++){
                    Cell cell = rowColHT.createCell(i);
                    cell.setCellValue(jTable_TKHienTai.getColumnName(i));
                }
                
                for(int j = 0; j<jTable_TKHienTai.getRowCount(); j++){
                    Row row = sheetHT.createRow(j+1);
                    for (int k = 0; k<jTable_TKHienTai.getColumnCount(); k++){
                        Cell cell = row.createCell(k);
                        if (jTable_TKHienTai.getValueAt(j, k) != null){
                            cell.setCellValue(jTable_TKHienTai.getValueAt(j, k).toString());
                        }
                    }
                }
                
                //Xuat ve thang
                org.apache.poi.ss.usermodel.Sheet sheetVT = wb.createSheet("VE THANG");
                Row rowColVT = sheetVT.createRow(0);
                for(int i = 0; i<jTable_TKVeThang.getColumnCount(); i++){
                    Cell cell = rowColVT.createCell(i);
                    cell.setCellValue(jTable_TKVeThang.getColumnName(i));
                }
                
                for(int j = 0; j<jTable_TKVeThang.getRowCount(); j++){
                    Row row = sheetVT.createRow(j+1);
                    for (int k = 0; k<jTable_TKVeThang.getColumnCount(); k++){
                        Cell cell = row.createCell(k);
                        if (jTable_TKVeThang.getValueAt(j, k) != null){
                            cell.setCellValue(jTable_TKVeThang.getValueAt(j, k).toString());
                        }
                    }
                }
                
                //Xuat su co
                org.apache.poi.ss.usermodel.Sheet sheetSC = wb.createSheet("SU CO");
                Row rowColSC = sheetSC.createRow(0);
                for(int i = 0; i<jTable_TKSuCo.getColumnCount(); i++){
                    Cell cell = rowColSC.createCell(i);
                    cell.setCellValue(jTable_TKSuCo.getColumnName(i));
                }
                
                for(int j = 0; j<jTable_TKSuCo.getRowCount(); j++){
                    Row row = sheetSC.createRow(j+1);
                    for (int k = 0; k<jTable_TKSuCo.getColumnCount(); k++){
                        Cell cell = row.createCell(k);
                        if (jTable_TKSuCo.getValueAt(j, k) != null){
                            cell.setCellValue(jTable_TKSuCo.getValueAt(j, k).toString());
                        }
                    }
                }
                
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            }
            else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }//GEN-LAST:event_btn_DT_EXtoExcelActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DT_EXtoExcel;
    private javax.swing.JButton btn_DangXuat;
    private javax.swing.JButton btn_DatLaiVe;
    private javax.swing.JButton btn_DoanhThu;
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_FindName;
    private javax.swing.JButton btn_FindVe;
    private javax.swing.JButton btn_HienTai;
    private javax.swing.JButton btn_Huy;
    private javax.swing.JButton btn_HuyBo;
    private javax.swing.JButton btn_HuyDatLai;
    private javax.swing.JButton btn_HuySV;
    private javax.swing.JButton btn_HuyThanhTien;
    private javax.swing.JButton btn_HuyThaoTac_CT;
    private javax.swing.JButton btn_HuyTien;
    private javax.swing.JButton btn_LocTheoYC;
    private javax.swing.JButton btn_LuuCaTruc1;
    private javax.swing.JButton btn_LuuVe;
    private javax.swing.JButton btn_Ra;
    private javax.swing.JButton btn_SaveAdd;
    private javax.swing.JButton btn_SaveEdit;
    private javax.swing.JButton btn_SuCo;
    private javax.swing.JButton btn_SuaCaTruc1;
    private javax.swing.JButton btn_Thang;
    private javax.swing.JButton btn_ThemCaTruc;
    private javax.swing.JButton btn_ThemNV;
    private javax.swing.JButton btn_Vao;
    private javax.swing.JButton btn_VeThang;
    private javax.swing.JButton btn_XNSV;
    private javax.swing.JButton btn_XacNhanThanhTien;
    private javax.swing.JButton btn_XacNhanThemCT;
    private javax.swing.JButton btn_XacNhanTien;
    private javax.swing.JButton btn_XoaCaTruc;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jC_CaTruc;
    private javax.swing.JComboBox<String> jC_CaTruc1;
    private javax.swing.JComboBox<String> jC_Class;
    private javax.swing.JComboBox<String> jC_GioiTinh;
    private javax.swing.JComboBox<String> jC_LocCaTruc;
    private javax.swing.JComboBox<String> jC_LocNgayLam;
    private javax.swing.JComboBox<String> jC_Loi;
    private javax.swing.JComboBox<String> jC_Quy;
    private javax.swing.JComboBox<String> jC_Sex;
    private javax.swing.JComboBox<String> jC_SuCo;
    private javax.swing.JComboBox<String> jC_Thang;
    private javax.swing.JComboBox<String> jC_ThongKe;
    private javax.swing.JComboBox<String> jC_TinhTrangDKT;
    private javax.swing.JComboBox<String> jC_TinhTrangGui;
    private com.toedter.calendar.JDateChooser jDate_Date;
    private com.toedter.calendar.JDateChooser jDate_Ngay;
    private com.toedter.calendar.JDateChooser jDate_NgaySV;
    private com.toedter.calendar.JDateChooser jDate_NgayThongKe;
    private com.toedter.calendar.JDateChooser jDate_NgayTruc;
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
    private javax.swing.JLabel jL_SuCo;
    private javax.swing.JLabel jL_TGDK;
    private javax.swing.JLabel jL_TGHetHan;
    private javax.swing.JLabel jL_ThongKe;
    private javax.swing.JLabel jL_ThongKeTongSoXe;
    private javax.swing.JLabel jL_ThongKeXeChuaLay;
    private javax.swing.JLabel jL_ThongKeXeDaLay;
    private javax.swing.JLabel jL_Tien;
    private javax.swing.JLabel jL_Tien1;
    private javax.swing.JLabel jL_TienPaht1;
    private javax.swing.JLabel jL_Time;
    private javax.swing.JLabel jL_Tittle;
    private javax.swing.JLabel jL_Tittle2;
    private javax.swing.JLabel jL_TongSC;
    private javax.swing.JLabel jL_TongTien;
    private javax.swing.JLabel jL_TongTienLuot;
    private javax.swing.JLabel jL_TongTienSC;
    private javax.swing.JLabel jL_TongTienThang;
    private javax.swing.JLabel jL_XeCH;
    private javax.swing.JLabel jL_XeDKT;
    private javax.swing.JLabel jL_XeHH;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jP_DoanhThu;
    private javax.swing.JPanel jP_HienTai;
    private javax.swing.JPanel jP_SuCo;
    private javax.swing.JPanel jP_VeThang;
    private javax.swing.JPanel jPan_ChinhSuaTTVe;
    private javax.swing.JPanel jPan_GuiXe;
    private javax.swing.JPanel jPan_Inf;
    private javax.swing.JPanel jPan_PhanCong;
    private javax.swing.JPanel jPan_ThongKe;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPass_Pass;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jT_BienSo;
    private javax.swing.JTextField jT_CT_BD;
    private javax.swing.JTextField jT_CT_KT;
    private javax.swing.JTextField jT_DT_TimXe;
    private javax.swing.JTextField jT_DiaChi;
    private javax.swing.JTextField jT_FindBienSo;
    private javax.swing.JTextField jT_FindHieuXe;
    private javax.swing.JTextField jT_GioBD;
    private javax.swing.JTextField jT_GioKT;
    private javax.swing.JTextField jT_HT_TimKiem;
    private javax.swing.JTextField jT_HieuXe;
    private javax.swing.JTextField jT_HoTen;
    private javax.swing.JTextField jT_ID;
    private javax.swing.JTextField jT_KhungGio;
    private javax.swing.JTextField jT_Lop;
    private javax.swing.JTextField jT_MaNVTruc;
    private javax.swing.JTextField jT_MaSV;
    private javax.swing.JTextField jT_MauXe;
    private javax.swing.JTextField jT_Name;
    private javax.swing.JTextField jT_Phone;
    private javax.swing.JTextField jT_QueQuan;
    private javax.swing.JTextField jT_TenNVTruc;
    private javax.swing.JTextField jT_TienKhungGio;
    private javax.swing.JTextField jT_Time;
    private javax.swing.JTable jTable_GuiXe;
    private javax.swing.JTable jTable_KhungGio;
    private javax.swing.JTabbedPane jTable_NV;
    private javax.swing.JTable jTable_NhanVien;
    private javax.swing.JTable jTable_PhanCongTruc;
    private javax.swing.JTable jTable_TKDoanhThu;
    private javax.swing.JTable jTable_TKHienTai;
    private javax.swing.JTable jTable_TKSuCo;
    private javax.swing.JTable jTable_TKVeThang;
    // End of variables declaration//GEN-END:variables
}
