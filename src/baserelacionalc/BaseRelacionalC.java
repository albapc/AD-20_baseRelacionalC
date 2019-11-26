package baserelacionalc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alba
 */
public class BaseRelacionalC {

    //set autocommit on;
    
    public static Connection conn = null;
    
    private Connection conexion() {
        final String driver = "jdbc:oracle:thin:";
        final String host = "localhost.localdomain";
        final String porto = "1521";
        final String sid = "orcl";
        final String usuario = "hr";
        final String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

//        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void insirePrep(String codigo, String descricion, int prezo) {
        String sql = "INSERT INTO produtos(codigo,descricion,prezo) VALUES(?,?,?)";
 
        try (Connection conn = this.conexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setString(2, descricion);
            pstmt.setInt(3, prezo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void actuPrep(String codigo, int prezo) {
        String sql = "UPDATE produtos SET prezo = ? WHERE codigo = ?";
 
        try (Connection conn = this.conexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            pstmt.setInt(1, prezo);
            pstmt.setString(2, codigo);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void consultaPrep() {
        String sql = "SELECT codigo, descricion, prezo FROM produtos";
        
        try (Connection conn = this.conexion();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             ResultSet rs    = pstmt.executeQuery()){
            
            while (rs.next()) {
                System.out.println(rs.getString("codigo") +  "\t" + 
                                   rs.getString("descricion") + "\t" +
                                   rs.getDouble("prezo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void borraPrep(String codigo) {
        String sql = "DELETE FROM produtos WHERE codigo = ?";
 
        try (Connection conn = this.conexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 

            pstmt.setString(1, codigo);

            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) throws SQLException {
        BaseRelacionalC obx = new BaseRelacionalC();
//        obx.insirePrep("p6", "libros", 5);
//        obx.actuPrep("p6", 6);
//        obx.consultaPrep();
//        obx.borraPrep("p6");
        conn.close();
    }
    
}
