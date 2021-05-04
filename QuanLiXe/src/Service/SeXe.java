
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
    
    //5. lay ma loi
    public String layMaLoi(String tenLoi){
        String maLoi = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT MA_SU_CO FROM SU_CO WHERE TEN_SU_CO = N'" + tenLoi + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                maLoi = rs.getString("MA_SU_CO");
            }
        } 
        catch (Exception e) {
        }
        return maLoi;
    }
    
    //6. lay hieu xe
    public String layHieuXe(String bienSo){
        String hieuXe = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT HIEU_XE FROM XE WHERE BIEN_SO_XE = '" + bienSo + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                hieuXe = rs.getString("HIEU_XE");
            }
        } 
        catch (Exception e) {
        }
        return hieuXe;
    }
    
    //7. lay mauXe
    public String layMauXe(String bienSo){
        String mauXe = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT MAU_XE FROM XE WHERE BIEN_SO_XE = '" + bienSo + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                mauXe = rs.getString("MAU_XE");
            }
        } 
        catch (Exception e) {
        }
        return mauXe;
    }
    
    //8.lay ma ca truc tu ten ca truc cua nhanvien
    public String layMaCaTruc(String maNV, Date ngayLam){
        String maCa = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT MA_CA FROM PHAN_CONG_TRUC WHERE MA_NHAN_VIEN = '" + maNV + "' AND NGAY_LAM = '" + ngayLam + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                maCa = rs.getString("MA_CA");
            }
        } 
        catch (Exception e) {
        }
        return maCa;
    }
    
    //9. lay ma ca truc tu ten ca
    public String layMaCaTrucTuTenCa(String tenCa){
        String maCa = "";
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "SELECT MA_CA FROM CA_TRUC WHERE TEN_CA = N'" + tenCa + "'";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                maCa = rs.getString("MA_CA");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return maCa;
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
    
    //6. them lich truc
    public void themCaTruc(String maCa, Date ngayLam, String maNV){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "insert into PHAN_CONG_TRUC values (?, ?, ?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maCa);
            ps.setDate(2, ngayLam);
            ps.setString(3, maNV);
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
    public void xoaCaTruc(String maCa, Date ngayLam){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "DELETE FROM PHAN_CONG_TRUC WHERE MA_CA = ? AND NGAY_LAM = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maCa);
            ps.setDate(2, ngayLam);
            ps.executeUpdate();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
    public void capNhatLaiTTVeThang(String bienSo, Date ngayLam, Date ngayHH ,String trangThai){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update QL_VE_THANG set NGAY_LAM_VE = ?, NGAY_HET_HAN =?, TRANG_THAI = ? where BIEN_SO_XE = '" + bienSo + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setDate(1, ngayLam);
            ps.setDate(2, ngayHH);
            ps.setString(3, trangThai);
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
    
    //goi function
    public String maKhungGio(Time gio){
        String maKH = "";
        
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "{? = call dbo.KT_KH(?)}";
        try {
            CallableStatement cs = ketNoi.prepareCall(sql);
            cs.registerOutParameter(1, java.sql.Types.NVARCHAR);
            cs.setTime(2, gio);
            cs.execute();
            maKH = cs.getString(1);
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return maKH;
    }
    
    //3. Cap nhat khung gio
    public void capNhatKhungGio(String tenKH, Time gioBD, Time gioKT){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update KHUNG_GIO set GIO_BAT_DAU = ?, GIO_KET_THUC = ? where TEN_KHUNG_GIO = N'" + tenKH + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setTime(1, gioBD);
            ps.setTime(2, gioKT);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //4. Cap nhat gia tien khung gio
    public void capNhatGiaTienKhungGio(String tenLoaiVe, String tenKH, int giaTien){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "Update GIA_TIEN set GIA_TIEN = ?\n" +
"where MA_LOAI_VE = (\n" +
"						select MA_LOAI_VE\n" +
"						from LOAI_VE \n" +
"						where TEN_LOAI = N'" + tenLoaiVe + "'\n" +
"						) and\n" +
"      MA_KHUNG_GIO = (\n" +
"						select MA_KHUNG_GIO\n" +
"						from KHUNG_GIO\n" +
"						where TEN_KHUNG_GIO = N'" + tenKH + "'\n" +
"						)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setInt(1, giaTien);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //5. cap nhat khung gio ca truc
    public void capNhatKhungGioCaTruc(String tenCa, Time gioBD, Time gioKT){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update CA_TRUC set GIO_BAT_DAU = ?, GIO_KET_THUC = ? where TEN_CA = N'" + tenCa+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setTime(1, gioBD);
            ps.setTime(2, gioKT);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //6. Cap nhat ca truc
    public void capNhatLichTruc(String maCaOld, Date ngayLamOld, String maNV, String maCaNew, Date ngayLamNew){
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "update PHAN_CONG_TRUC set MA_CA = ?, NGAY_LAM = ?, MA_NHAN_VIEN = ? where MA_CA = '" + maCaOld+"' AND NGAY_LAM = '" + ngayLamOld + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maCaNew);
            ps.setDate(2, ngayLamNew);
            ps.setString(3, maNV);
            ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //Cac ham THONG KE----------------------------------------------------------
    //I. Bang doanh thu
    //1. Tinh tien 
    public String tinhTien(String sql){
        int tinhTien = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                
                tinhTien = rs.getInt("GIA_TIEN");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return String.valueOf(tinhTien);
    }
    
    //2. Tinh tien luot
    public String tinhTienLuot(String sql){
        int tinhTien = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();

        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tinhTien = rs.getInt("GIA_TIEN");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return String.valueOf(tinhTien);
    }
    
    //3. Tinh tien thang
    public String tinhTienThang(String sql){
        int tinhTien = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tinhTien = rs.getInt("GIA_TIEN");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return String.valueOf(tinhTien);
    }
    
    //4. tinh tong so xe
    public String tongXe(){
        int tongXe = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        String sql = "select count(MA_VE) as TONG_XE\n" +
                    "from VE_XE";
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tongXe = rs.getInt("TONG_XE");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return String.valueOf(tongXe);
    }
    
    //II. Bang su co
    //5. tinh tong tien xu phat
    public String tienSuCo(String sql){
        int tienSuCo = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tienSuCo = rs.getInt("XU_PHAT");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return String.valueOf(tienSuCo);
    }
    
    //6. tinh tong so su co
    public String tongSuCo(String sql){
        int tienSuCo = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tienSuCo = rs.getInt("SU_CO");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return String.valueOf(tienSuCo);
    }
    
    //7. Tinh tong so xe trong bai
    public String tongSoXeTrongBai(String sql){
        int tongSoXe = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tongSoXe = rs.getInt("XE");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return String.valueOf(tongSoXe);
    }
    
    //III. Bang Ve thang
    //7. Tinh tong xe DK thang
    public String tongXeDKT(String sql){
        int tienSuCo = 0;
        Connection ketNoi = KetNoiCSDL.ketNoi();
        try {
            PreparedStatement pr = ketNoi.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                tienSuCo = rs.getInt("VE_THANG");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return String.valueOf(tienSuCo);
    }
}
