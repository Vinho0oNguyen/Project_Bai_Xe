
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
        String sql = "SELECT GIA_TIEN FROM GIA_TIEN WHERE MA_LOAI_VE = '" + maLoaiVe + "' AND MA_KHUNG_GIO = '" +  khungGio + "'";
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
    
    //2. lay ma loai ve tu bang VE_XE
    public String layMaLoaiVe(String maVe){
        String maLoaiVe = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT MA_LOAI_VE FROM VE_XE WHERE MA_VE = N'" + maVe + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                maLoaiVe = rs.getString("MA_LOAI_VE");
            }
        } 
        catch (Exception e) {
        }
        return maLoaiVe;
    }
    
    //3. tinh tien loi
    public int layTienLoi(String tenSuCo){
        int tien = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT XU_PHAT FROM SU_CO WHERE TEN_SU_CO = N'" + tenSuCo + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tien = rs.getInt("XU_PHAT");
            }
        } 
        catch (Exception e) {
        }
        
        
        return tien;
    }
    
    //4. lay ten khung h
    public String layTenKhungH(String maKhungH){
        String tenKH = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT TEN_KHUNG_GIO FROM KHUNG_GIO WHERE MA_KHUNG_GIO = '" + maKhungH + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tenKH = rs.getString("TEN_KHUNG_GIO");
            }
        } 
        catch (Exception e) {
        }
        return tenKH;
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
    
    //5. Them su co
    public void themSuCo(String maSuCo, String bienSoXe, Date ngayXayRa){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into QL_SU_CO values (?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maSuCo);
            ps.setString(2, bienSoXe);
            ps.setDate(3, ngayXayRa);
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
    //1. Cap nhat lai trang thai xe trong QL ve thang
    public void capNhatTTVeThang(String bienSo, String trangThai){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update QL_VE_THANG set TRANG_THAI = ? where BIEN_SO_XE = '" + bienSo + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, trangThai);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //2. Cap nhat cac xe da xuat ben
    public void capNhatXeXuatBen(String bienSo, String tenKhungGio, Date ngayRa, Time gioRa, String maNVRa, int giaTien, String tinhTrang){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update VE_XE set MA_KHUNG_GIO = ?, NGAY_RA = ?, GIO_RA = ?, MA_NV_RA =?, GIA_TIEN =?, TINH_TRANG = ? where BIEN_SO_XE = '" + bienSo + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tenKhungGio);
            ps.setDate(2, ngayRa);
            ps.setTime(3, gioRa);
            ps.setString(4, maNVRa);
            ps.setInt(5, giaTien);
            ps.setString(6, tinhTrang);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Goi store procedure
    public String maXe(){
        String maXe = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "{call sp_VeXe_TuSinhID}";
        try {
            CallableStatement cs = ketNoi.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                maXe = rs.getString("sp_VeXe_TuSinhID");
            }
            
                    
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return maXe;
    }
}
