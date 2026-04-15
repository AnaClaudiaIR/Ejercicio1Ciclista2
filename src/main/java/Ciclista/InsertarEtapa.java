package Ciclista;

import org.example.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsertarEtapa {

    //Método para insertar etapa que devuelve el objeto "etapa"
    public static Etapa insertarNuevaEtapa(int numeroEtapa, String origen, String destino, double distancia, String fecha) throws SQLException {
        Connection connection = null;
        Etapa etapa = new Etapa(numeroEtapa, origen, destino, distancia, fecha);
        try {
            connection = DriverManager.getConnection(
                    DBConfig.getUrl(),
                    DBConfig.getUser(),
                    DBConfig.getPassword()
            );
            connection.setAutoCommit(false);

            //Pasar el string de fecha a un objeto
            LocalDate fechaSQL = LocalDate.parse(fecha);

            //Insertar los datos recibidos por teclado
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO etapa VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, numeroEtapa);
            preparedStatement.setString(2, origen);
            preparedStatement.setString(3, destino);
            preparedStatement.setDouble(4, distancia);
            preparedStatement.setObject(5, fechaSQL);
            preparedStatement.executeUpdate();

            connection.commit();
            System.out.println("Etapa insertada.");

        } catch (SQLException e) {
            System.out.println("Error al insertar la etapa --> " + e.getMessage());
            //Si hay un error que haga rollback
            if (connection != null) {
                connection.rollback();
            }

        } finally {
            //Cerrar la conexión una vez se haya acabado de hacer todo.
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión --> " + e.getMessage());
                }
            }
        }
        return etapa;
    }
}
