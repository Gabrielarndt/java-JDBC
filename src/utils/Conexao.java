package utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String senha = "admin";
        return DriverManager.getConnection(url, user, senha);
    }
}
