
package KetNoiSQL;

import java.sql.Connection;
import java.sql.DriverManager;


public class KetNoiCSDL {
    public static Connection ketNoi(){
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://;databaseName=QLBAIXE";
        String user = "sa";
        String pass = "123";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = (Connection) DriverManager.getConnection(uRL, user, pass);
            System.out.print("Ket noi thanh cong.");
        } 
        catch (Exception e) {
            System.out.print("Ket noi that bai.");
        }
        
        
        return ketNoi;
    }
    
    public static void main(String[] args) {
        KetNoiCSDL.ketNoi();
    }
}
