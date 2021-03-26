
package GiaoDien;

import KetNoiSQL.KetNoiCSDL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class NhanVien extends javax.swing.JFrame {
    private String maNV;
    
    public NhanVien(String maNV) {
        this.maNV = maNV;
        initComponents();
        this.setLocationRelativeTo(null);
        this.layTT(maNV);
    }

    private NhanVien() {
    }
    
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jT_Add = new javax.swing.JTextField();
        jL_Pass = new javax.swing.JLabel();
        jPass_Pass = new javax.swing.JPasswordField();
        jL_Phone = new javax.swing.JLabel();
        jT_Phone = new javax.swing.JTextField();
        btn_Edit = new javax.swing.JButton();
        btn_Save = new javax.swing.JButton();
        jPan_Ve = new javax.swing.JPanel();
        jL_Tittle1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jL_Tittle5 = new javax.swing.JLabel();
        jL_TotalV = new javax.swing.JLabel();
        jL_Tittle6 = new javax.swing.JLabel();
        jL_V = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Ve = new javax.swing.JTable();
        jPan_GuiXe = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jT_GuiXe = new javax.swing.JTable();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jL_BienSo = new javax.swing.JLabel();
        jT_BienSo = new javax.swing.JTextField();
        jL_VeID = new javax.swing.JLabel();
        jT_VeID = new javax.swing.JTextField();
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
        jT_FindVe = new javax.swing.JTextField();
        btn_FindVe = new javax.swing.JButton();
        jT_FindName = new javax.swing.JTextField();
        btn_FindName = new javax.swing.JButton();
        btn_DangXuat = new javax.swing.JButton();

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

        jL_Tittle2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jL_Tittle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Tittle2.setText("THÔNG TIN CÁ NHÂN");

        jL_ID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_ID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_ID.setText("Mã nhân viên:");

        jT_ID.setEnabled(false);

        jL_Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Name.setText("Tên nhân viên:");

        jT_Name.setEnabled(false);

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

        jT_Add.setEnabled(false);

        jL_Pass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Pass.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Pass.setText("Mật khẩu:");

        jPass_Pass.setEnabled(false);

        jL_Phone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jL_Phone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Phone.setText("Số điện thoại:");

        jT_Phone.setEnabled(false);

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

        javax.swing.GroupLayout jPan_InfLayout = new javax.swing.GroupLayout(jPan_Inf);
        jPan_Inf.setLayout(jPan_InfLayout);
        jPan_InfLayout.setHorizontalGroup(
            jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_InfLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPan_InfLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jL_Tittle2)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPan_InfLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jT_Name)
                                    .addComponent(jT_ID)
                                    .addComponent(jDate_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jC_Sex, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(47, 47, 47)
                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPan_InfLayout.createSequentialGroup()
                                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPan_InfLayout.createSequentialGroup()
                                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jL_Pass)
                                            .addComponent(jL_Class)
                                            .addComponent(jL_Add))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPass_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jT_Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jC_Class, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPan_InfLayout.createSequentialGroup()
                                        .addComponent(jL_Phone)
                                        .addGap(18, 18, 18)
                                        .addComponent(jT_Phone)))
                                .addGap(84, 84, 84))
                            .addGroup(jPan_InfLayout.createSequentialGroup()
                                .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPan_InfLayout.setVerticalGroup(
            jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_InfLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_Tittle2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
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
                    .addComponent(jL_Add)
                    .addComponent(jT_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jC_Sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_Phone)
                    .addComponent(jT_Phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_Sex))
                .addGap(31, 31, 31)
                .addGroup(jPan_InfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
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

        jPan_Ve.setLayout(null);

        jL_Tittle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Tittle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_Tittle1.setText("Thông tin vé");
        jPan_Ve.add(jL_Tittle1);
        jL_Tittle1.setBounds(57, 20, 73, 15);
        jPan_Ve.add(jSeparator3);
        jSeparator3.setBounds(140, 30, 550, 10);
        jPan_Ve.add(jSeparator4);
        jSeparator4.setBounds(30, 30, 30, 10);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPan_Ve.add(jSeparator5);
        jSeparator5.setBounds(690, 30, 10, 70);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPan_Ve.add(jSeparator6);
        jSeparator6.setBounds(30, 30, 10, 70);

        jL_Tittle5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Tittle5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_Tittle5.setText("Tổng số vé:");
        jPan_Ve.add(jL_Tittle5);
        jL_Tittle5.setBounds(170, 60, 70, 15);

        jL_TotalV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_TotalV.setText("0");
        jPan_Ve.add(jL_TotalV);
        jL_TotalV.setBounds(250, 60, 34, 14);

        jL_Tittle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Tittle6.setText("Vé còn lại:");
        jPan_Ve.add(jL_Tittle6);
        jL_Tittle6.setBounds(420, 60, 70, 15);

        jL_V.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_V.setText("0");
        jPan_Ve.add(jL_V);
        jL_V.setBounds(490, 60, 34, 14);
        jPan_Ve.add(jSeparator7);
        jSeparator7.setBounds(30, 100, 660, 10);

        jTable_Ve.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable_Ve.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vé ID", "Biển số xe", "Loại vé", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_Ve);

        jPan_Ve.add(jScrollPane1);
        jScrollPane1.setBounds(30, 170, 660, 170);

        jTable_NV.addTab("Quản Lí Vé", jPan_Ve);

        jT_GuiXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vé", "Biển số", "Hiệu xe", "Màu xe", "Loại vé", "TG vào bến"
            }
        ));
        jScrollPane2.setViewportView(jT_GuiXe);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jL_BienSo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_BienSo.setText("Biển số:");

        jL_VeID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_VeID.setText("Mã vé:");

        jL_Hieuxe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Hieuxe.setText("Hiệu xe:");

        jL_Mauxe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Mauxe.setText("Màu xe:");

        jL_Time.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Time.setText("Giờ vào bến:");

        jL_Ngay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Ngay.setText("Ngày vào bến:");

        jDate_Ngay.setDateFormatString("MM/dd/yyyy");

        btn_Vao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Vao.setText("Vào bến");

        btn_Ra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Ra.setText("Xuất bến");

        jL_Mauxe1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jL_Mauxe1.setText("Lỗi:");

        jC_Loi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không" }));

        btn_Thang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Thang.setText("Đăng ký tháng");

        btn_FindVe.setText("Tìm kiếm theo vé");

        btn_FindName.setText("Tìm kiếm theo biển số xe");

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
                                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jL_BienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jL_VeID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jL_Hieuxe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jL_Mauxe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jT_BienSo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jT_VeID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addGap(75, 75, 75)
                                        .addComponent(btn_Ra)
                                        .addGap(73, 73, 73)
                                        .addComponent(btn_Thang)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)))
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                        .addComponent(jT_FindVe, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_FindVe)
                        .addGap(54, 54, 54)
                        .addComponent(jT_FindName, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_FindName)))
                .addGap(15, 15, 15))
        );
        jPan_GuiXeLayout.setVerticalGroup(
            jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_GuiXeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(jL_VeID)
                                    .addComponent(jT_VeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_Ngay)))
                            .addComponent(jDate_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL_Hieuxe)
                            .addComponent(jT_HieuXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_Mauxe1)
                            .addComponent(jC_Loi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL_Mauxe)
                            .addComponent(jT_MauXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Vao)
                            .addComponent(btn_Ra)
                            .addComponent(btn_Thang)))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPan_GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jT_FindVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_FindVe)
                    .addComponent(jT_FindName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_FindName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
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

        jTable_NV.addTab("Quản Lí Gửi Xe", jPan_GuiXe);

        btn_DangXuat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_DangXuat.setText("Đăng xuất");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_DangXuat)
                .addGap(40, 40, 40))
            .addComponent(jTable_NV, javax.swing.GroupLayout.PREFERRED_SIZE, 719, Short.MAX_VALUE)
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
                            .addComponent(btn_DangXuat))))
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

    private void btn_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditActionPerformed
        // TODO add your handling code here:
        btn_Edit.setEnabled(false);
        btn_Save.setEnabled(true);
        jT_Name.setEnabled(true);
        jDate_Date.setEnabled(true);
        jC_Sex.setEnabled(true);
        jPass_Pass.setEnabled(true);
        jT_Add.setEnabled(true);
        jT_Phone.setEnabled(true);
        
    }//GEN-LAST:event_btn_EditActionPerformed

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        btn_Edit.setEnabled(true);
        btn_Save.setEnabled(false);
   
        jT_Name.setEnabled(false);
        jDate_Date.setEnabled(false);
        jC_Sex.setEnabled(false);
        jPass_Pass.setEnabled(false);
        jT_Add.setEnabled(false);
        jT_Phone.setEnabled(false);
    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangXuatActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btn_DangXuatActionPerformed

    
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
    private javax.swing.JButton btn_Ra;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Thang;
    private javax.swing.JButton btn_Vao;
    private javax.swing.JComboBox<String> jC_Class;
    private javax.swing.JComboBox<String> jC_Loi;
    private javax.swing.JComboBox<String> jC_Sex;
    private com.toedter.calendar.JDateChooser jDate_Date;
    private com.toedter.calendar.JDateChooser jDate_Ngay;
    private javax.swing.JLabel jL_Add;
    private javax.swing.JLabel jL_BienSo;
    private javax.swing.JLabel jL_Class;
    private javax.swing.JLabel jL_Date;
    private javax.swing.JLabel jL_Hieuxe;
    private javax.swing.JLabel jL_ID;
    private javax.swing.JLabel jL_Icon;
    private javax.swing.JLabel jL_Icon2;
    private javax.swing.JLabel jL_Mauxe;
    private javax.swing.JLabel jL_Mauxe1;
    private javax.swing.JLabel jL_Name;
    private javax.swing.JLabel jL_Ngay;
    private javax.swing.JLabel jL_Pass;
    private javax.swing.JLabel jL_Phone;
    private javax.swing.JLabel jL_Sex;
    private javax.swing.JLabel jL_Time;
    private javax.swing.JLabel jL_Tittle;
    private javax.swing.JLabel jL_Tittle1;
    private javax.swing.JLabel jL_Tittle2;
    private javax.swing.JLabel jL_Tittle5;
    private javax.swing.JLabel jL_Tittle6;
    private javax.swing.JLabel jL_TotalV;
    private javax.swing.JLabel jL_V;
    private javax.swing.JLabel jL_VeID;
    private javax.swing.JPanel jPan_GuiXe;
    private javax.swing.JPanel jPan_Inf;
    private javax.swing.JPanel jPan_Ve;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jT_Add;
    private javax.swing.JTextField jT_BienSo;
    private javax.swing.JTextField jT_FindName;
    private javax.swing.JTextField jT_FindVe;
    private javax.swing.JTable jT_GuiXe;
    private javax.swing.JTextField jT_HieuXe;
    private javax.swing.JTextField jT_ID;
    private javax.swing.JTextField jT_MauXe;
    private javax.swing.JTextField jT_Name;
    private javax.swing.JTextField jT_Phone;
    private javax.swing.JTextField jT_Time;
    private javax.swing.JTextField jT_VeID;
    private javax.swing.JTabbedPane jTable_NV;
    private javax.swing.JTable jTable_Ve;
    // End of variables declaration//GEN-END:variables
}
