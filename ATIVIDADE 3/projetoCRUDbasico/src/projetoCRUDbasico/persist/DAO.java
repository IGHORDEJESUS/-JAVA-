package projetoCRUDbasico.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



// Data Access Object - Objeto de acesso a dados
public class DAO {
// https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html
    public DAO() {
        try {
            // informa qual classe irá ser usada para as transações com o SGBD
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.err.println("Class not found. Error: " + cnfe.getMessage());
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/db01", "root", "");
    }
}
