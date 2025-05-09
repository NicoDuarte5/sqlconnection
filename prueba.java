import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class prueba {
    private JButton button1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel panel1;
    private Connection conn;
    private Statement stmt;




    DefaultTableModel dtm = new DefaultTableModel();
    private String [] titulo = new String[]{"usuario","contraseña","email"};

    public void conexion() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/users?&serverTimezone=UTC";
        String user = "root";
        String password = "root";

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

    public prueba() {
        JFrame frame = new JFrame("titulo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        dtm.setColumnIdentifiers(titulo);




        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conexion();

                PreparedStatement pst = null;
                ResultSet rs = null;
                try{

                    pst=conn.prepareStatement("insert into users (username,email,password) values (?,?,?);");

                    pst.setString(1,textField1.getText());
                    pst.setString(2,textField2.getText());
                    pst.setString(3,textField3.getText());
                    pst.executeUpdate();



                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dtm.addRow(new Object[]{textField1.getText(),textField2.getText(),textField3.getText()});
                table1.setModel(dtm);



            }
        });
    }
}
