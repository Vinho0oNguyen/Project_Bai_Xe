
package Service;
import KetNoiSQL.KetNoiCSDL;
import java.sql.Connection;
import java.sql.*;

public class SeNV {
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
    public void suaDN(String maNV, String MK){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update DANG_NHAP set TAI_KHOANG = ?, MAT_KHAU = ? where TAI_KHOANG = '" + maNV + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, MK);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
