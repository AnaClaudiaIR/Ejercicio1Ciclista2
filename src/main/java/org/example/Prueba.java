package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Prueba {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()
        )) {
            System.out.println("Conectado.");
        } catch (SQLException e){
            System.out.println("ERROR --> "+e.getMessage());
        }
    }
}
