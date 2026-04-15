package Ciclista;

import org.example.DBConfig;

import java.sql.*;
import java.time.LocalDate;

public class Resumen {
    public static void mostrarResumenEtapa(Etapa etapa){
        try (Connection connection = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()
        );  PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(ID_CICLISTA) AS CICLISTAS, FECHA, NUMERO_ETAPA\n" +
                "FROM ETAPA  JOIN PARTICIPACION USING (NUMERO_ETAPA)\n" +
                "WHERE NUMERO_ETAPA = ?\n" +
                "GROUP BY  FECHA, NUMERO_ETAPA")) {
            //Prepared statement para hacer la consulta que generará el resumen
            preparedStatement.setInt(1, etapa.getNumeroEtapa());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ciclistas = resultSet.getInt("CICLISTAS");
                LocalDate fecha = resultSet.getDate("FECHA").toLocalDate();
                int numeroEtapa = resultSet.getInt("NUMERO_ETAPA");

                System.out.println("\n-------------------------");
                System.out.println("Número Etapa: " + numeroEtapa);
                System.out.println("Número de ciclistas añadidos: " + ciclistas);
                System.out.println("Fecha: " + fecha);
            }
        } catch (SQLException e){
            System.out.println("ERROR --> "+e.getMessage());
        }
    }
}
