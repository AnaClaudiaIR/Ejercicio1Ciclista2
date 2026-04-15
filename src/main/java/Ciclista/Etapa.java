package Ciclista;

import org.example.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarEtapa {
    public static void insertarNuevaEtapa(int numeroEtapa, String origen, String destino, double distancia, String fecha) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    DBConfig.getUrl(),
                    DBConfig.getUser(),
                    DBConfig.getPassword()
            );
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO etapa VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, numeroEtapa);
            preparedStatement.setString(2, origen);
            preparedStatement.setString(3, destino);
            preparedStatement.setDouble(4, distancia);
            preparedStatement.setString(5, fecha);
            preparedStatement.executeUpdate();

            connection.commit();
            System.out.println("Etapa insertada.");

        } catch (SQLException e) {
            System.out.println("Error --> " + e.getMessage());
            connection.rollback();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión --> " + e.getMessage());
                }
            }
        }
    }
}
