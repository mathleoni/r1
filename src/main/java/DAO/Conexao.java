package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static Connection conexao;

    public static Connection getInstance() throws SQLException {
        if (conexao == null) {
            String driver = "org.postgresql.Driver";
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/amigooculto", "semana", "semana");
        }
        return conexao;
    }
}
