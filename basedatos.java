import java.sql.*;

public class basedatos {

    public basedatos() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/users?&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        Connection conn;
        Statement stmt;
        try {
            Class.forName(driver);
            conn= DriverManager.getConnection(url,user,password);
            if(!conn.isClosed()){
                System.out.println("Conexion exitosa");
            }
            else{
                System.out.println("Conexion esta cerrada");
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }catch(ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }
}
