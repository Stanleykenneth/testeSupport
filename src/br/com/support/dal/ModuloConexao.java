package br.com.support.dal;

import java.sql.*;

/*
 * @author Kenneth Stanley Neves Dos Santos
 */
public class ModuloConexao {

    public static Connection conector() {
        
        Connection conexao = null;      
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3308/support";
        String user = "root";
        String password = "1234";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;           
        }
    }
}
