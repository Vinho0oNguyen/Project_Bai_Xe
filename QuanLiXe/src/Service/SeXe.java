
package Service;
import KetNoiSQL.KetNoiCSDL;
import java.sql.Connection;
import java.sql.*;

public class SeXe {
    //Cac ham ve SELECT---------------------------------------------------------
    //1. lay gia tien:
    public int layTien(String maLoaiVe, String khungGio){
        int giaTien = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT GIA_TIEN FROM GIA_TIEN WHERE MA_LOAI_VE = N'" + maLoaiVe + "' AND MA_KHUNG_GIO = N'" +  khungGio + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                giaTien = rs.getInt("GIA_TIEN");
            }
        } 
        catch (Exception e) {
        }
        return giaTien;
    }
    
    
    
    //Cac ham INSERT INTO-------------------------------------------------------
    //1. Them xe moi vao ql xe
    public void themVeXe(String maVe, String loaiVe, String bienSo, Date ngayVao, Time gioVao, String NVVao, String tinhTrang){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into VE_XE (MA_VE, MA_LOAI_VE, BIEN_SO_XE, NGAY_VAO, GIO_VAO, MA_NV_VAO, TINH_TRANG) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maVe);
            ps.setString(2, loaiVe);
            ps.setString(3, bienSo);
            ps.setDate(4, ngayVao);
            ps.setTime(5, gioVao);
            ps.setString(6, NVVao);
            ps.setString(7, tinhTrang);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //2. Them xe vao bang XE
    public void themXe(String bienSo, String hieuXe, String mauXe){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into XE values (?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, bienSo);
            ps.setString(2, hieuXe);
            ps.setString(3, mauXe);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //3. Them sinh vien
    public void themSV(String maSo, String hoTen, String lop, Date namSinh, String queQuan, String gioiTinh){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into SINH_VIEN values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maSo);
            ps.setString(2, hoTen);
            ps.setString(3, lop);
            ps.setDate(4, namSinh);
            ps.setString(5, queQuan);
            ps.setString(6, gioiTinh);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //4. Them ve thang
    public void themVeThang(String bienSo, String maSV, Date ngayDK, Date ngayHH, String trangThai){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into QL_VE_THANG values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, bienSo);
            ps.setString(2, maSV);
            ps.setDate(3, ngayDK);
            ps.setDate(4, ngayHH);
            ps.setString(5, trangThai);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Cac ham CHECK-------------------------------------------------------------
    //1. Check ma ve xe
    public int checkVeXe(String maVe){
        int check = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select * from VE_XE where MA_VE = '" + maVe + "'";
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
    
    //2. Check bien so xe trong bang XE
    public int checkBienSo(String bienSo){
        int check = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select * from XE where BIEN_SO_XE = '" + bienSo + "'";
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
    //3. check bien so trong bang VE_XE
    public int checkBienSoInVX(String bienSo){
        int check = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select * from VE_XE where BIEN_SO_XE = '" + bienSo + "'";
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
    
    //4. Check tinh trang xe trong bai
    public String checkTinhTrangVe(String bienSo){
        String check ="";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select TINH_TRANG from VE_XE where BIEN_SO_XE = '" + bienSo + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                check = rs.getString("TINH_TRANG");
            }
        } 
        catch (Exception e) {
        }
        return check;
    }
    
    //4. Check xe co ve thang
    public String checkTinhTrangVeThang(String bienSo){
        String check ="";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select TRANG_THAI from QL_VE_THANG where BIEN_SO_XE = '" + bienSo + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                check = rs.getString("TRANG_THAI");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    //5. Kiem tra sinh vien co dang ky ve thang chua
    public int checkSV(String maSV){
        int check = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select * from SINH_VIEN where MA_SINH_VIEN = '" + maSV + "'";
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
    
    //Cac ham UPDATE------------------------------------------------------------
    //1. Cap nhat lai xe trong QL ve thang
    public void capNhatVeThang(String bienSo, String maSV, Date ngayDK, Date ngayHH, String trangThai){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update QL_VE_THANG set MA_SINH_VIEN = ?, NGAY_LAM_VE = ?, NGAY_HET_HAN = ?, TRANG_THAI = ? where TAI_KHOANG = '" + bienSo + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maSV);
            ps.setDate(2, ngayDK);
            ps.setDate(3, ngayHH);
            ps.setString(4, trangThai);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
