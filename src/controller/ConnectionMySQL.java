package controller;

import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;


public class ConnectionMySQL {
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lojaGames","root","");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas!");
            throw new RuntimeException(e);
        }
    }
}
