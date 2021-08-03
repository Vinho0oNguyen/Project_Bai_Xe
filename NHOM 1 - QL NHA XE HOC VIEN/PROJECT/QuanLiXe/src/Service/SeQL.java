
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
    
    //2. lay ten nv
    public String layTenNV(String maNV){
        String tenNV = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT HO_TEN FROM NHAN_VIEN WHERE MA_NV = '" + maNV + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tenNV = rs.getString("HO_TEN");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return tenNV;
    }
    
    //3. Lay ca lam cuoi cung
    public String layTenCaTrucCuoi(){
        String tenCa = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT TOP 1 TEN_CA FROM CA_TRUC  ORDER BY MA_CA DESC";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tenCa = rs.getString("TEN_CA");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return tenCa;
    }
    
    //4. Lay gio KT ca lam cuoi cung
    public Time layGioCaTrucCuoi(){
        Time gio = null;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT TOP 1 GIO_KET_THUC FROM CA_TRUC  ORDER BY MA_CA DESC";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                gio = rs.getTime("GIO_KET_THUC");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return gio;
    }
    
    //4. Lay gio BT ca lam dau
    public Time layGioCaLamDau(){
        Time gio = null;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT TOP 1 GIO_BAT_DAU FROM CA_TRUC  ORDER BY MA_CA ASC";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                gio = rs.getTime("GIO_BAT_DAU");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return gio;
    }
    
    //5. lay gio KT truoc do
    public Time layGioCaTrucKeCuoi(String caTruc){
        Time gio = null;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT GIO_KET_THUC FROM CA_TRUC WHERE TEN_CA = '" + caTruc + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                gio = rs.getTime("GIO_KET_THUC");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return gio;
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
    
    //3. Them ca truc moi 
    public void themCaTrucMoi(String maCa, String tenCa, Time gioBD, Time gioKT){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into CA_TRUC values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maCa);
            ps.setString(2, tenCa);
            ps.setTime(3, gioBD);
            ps.setTime(4, gioKT);
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
    
    //2. Kiem tra ca lam co ton tai hay chua
    public int checkCaLam(String caLam){
        int check = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select * from CA_TRUC where TEN_CA = '" + caLam + "'";
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
            e.printStackTrace();
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
    
    //3. Xoa thong ca truc ma nhan vien nghi viec
    public void xoaTTCaMaNVNghi(String maNV){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "DELETE FROM PHAN_CONG_TRUC WHERE MA_NHAN_VIEN = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //4. Xoa ca truc
    public void xoaCaTruc(String tenCa){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "DELETE FROM CA_TRUC WHERE TEN_CA = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tenCa);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //4. Xoa ca truc
    public void xoaLichTruc(String tenCa){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "DELETE FROM PHAN_CONG_TRUC WHERE MA_CA = (SELECT MA_CA FROM CA_TRUC WHERE TEN_CA = ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tenCa);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
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
    
    //chuan hoa ten
    public String chuanHoaTen(String ten){
        String result = "";
        ten = ten.toUpperCase();
        String[] a = ten.split(" ");
        for (String x: a){
            if (x.equals("")){
                continue;
            }
            else{
                result += String.valueOf(x.charAt(0)) + x.substring(1) + " ";
            }
        }
        return result.trim();
    }
    
    //Tu sinh id cua ca truc
    public int tuTaoIDCaTruc(){
        int ID = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT COUNT(MA_CA) AS SO_CA_TRUC FROM CA_TRUC";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                ID = rs.getInt("SO_CA_TRUC");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return ID + 1;
    }
    
    //check ca lam co dung thu tu ko
    public int checkTTCa(String tenCaCuoi, String tenCaThem){
        int check = 0;
        if (tenCaCuoi.equals("CA 1") && tenCaThem.equals("CA 2")){
            check = 1;
        }
        if (tenCaCuoi.equals("CA 2") && tenCaThem.equals("CA 3")){
            check = 1;
        }
        if (tenCaCuoi.equals("CA 3") && tenCaThem.equals("CA 4")){
            check = 1;
        }
        if (tenCaCuoi.equals("CA 4") && tenCaThem.equals("CA 5")){
            check = 1;
        }
        return check;
    }
}
