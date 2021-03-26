
package Service;

import KetNoiSQL.KetNoiCSDL;
import java.sql.Connection;
import java.sql.*;
public class SeQL {
    
    //Cac ham ve SELECT---------------------------------------------------------
    //1. Lay ma chuc vu
    public String maCV(String tenCV){
        String maCV ="";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT MA_CHUC_VU FROM CHUC_VU WHERE TEN_CHUC_VU = N'" + tenCV + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                maCV = rs.getString("MA_CHUC_VU");
            }
        } 
        catch (Exception e) {
        }
        return maCV;
    }
    
    //Cac ham UPDATE------------------------------------------------------------
    //1. Sua thong tin nhan vien
    public void suaNV(String maNV, String tenNV, Date namSinh, String diaChi, String SDT, String gioiTinh){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update NHAN_VIEN set MA_NV = ?, HO_TEN = ?, NAM_SINH = ?, DIA_CHI = ?, SDT = ?, GIOI_TINH = ? where MA_NV = '" + maNV + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, tenNV);
            ps.setDate(3, namSinh);
            ps.setString(4, diaChi);
            ps.setString(5, SDT);
            ps.setString(6, gioiTinh);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void suaDN(String maNV, String MK, String maCV){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update DANG_NHAP set TAI_KHOANG = ?, MAT_KHAU = ?, MA_CHUC_VU = ? where TAI_KHOANG = '" + maNV + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, MK);
            ps.setString(3, maCV);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //Cac ham INSERT INTO-------------------------------------------------------
    //1. Them vao bang nhan vien
    public void themNV(String maNV, String tenNV, Date namSinh, String diaChi, String SDT, String gioiTinh){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into NHAN_VIEN values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, tenNV);
            ps.setDate(3, namSinh);
            ps.setString(4, diaChi);
            ps.setString(5, SDT);
            ps.setString(6, gioiTinh);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //2.Them Tai khoang nv vao bang DANG_NHAP
    public void themTK(String maNV, String MK, String maCV){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into DANG_NHAP values (?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, MK);
            ps.setString(3, maCV);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    //Cac ham CHECK-------------------------------------------------------------
    //1. Kiem tra ma nhan vien
    public int checkIDNV(String maNV){
        int check = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select * from NHAN_VIEN where MA_NV = '" + maNV + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                check = 1;
            }
        } 
        catch (Exception e) {
        }
        return check;
    }
    
      
    //Cac ham ve DELETE---------------------------------------------------------
    //1. Xoa thong tin nhan vien
    public void xoaNV(String maNV){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "DELETE FROM NHAN_VIEN WHERE MA_NV = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.executeUpdate();
        } 
        catch (Exception e) {
        }
    }
    
    //2. Xoa thong tin nv trong bang dang nhap
    public void xoaDangNhap(String taiKhoang){
        //Tai khong cung~ la ma nv
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "DELETE FROM DANG_NHAP WHERE TAI_KHOANG = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, taiKhoang);
            ps.executeUpdate();
        } 
        catch (Exception e) {
        }
    }
    
    //Chuẩn hóa SDT
    public int chuanHoaSDT(String SDT){
        int check = 1;
        String tmp = "0\\d{9,10}";
        if(SDT.equals("")){
           check =1;
        }
        else if(!SDT.matches(tmp)){
            check = 0;
        }
        
        return check;
    }
}
