package Ciclista;

import org.example.DBConfig;

import java.sql.*;
import java.util.*;

public class InsertarParticipacion {
    public static void insertarRegistroCiclista(Etapa etapa){
        //Crear el objeto para hacer la conexión
        Connection connection = null;

        //Set para generar los números aleatorios
        Set<Integer> posiciones = new HashSet<>();
        Random random = new Random();

        try {
            connection = DriverManager.getConnection(
                    DBConfig.getUrl(),
                    DBConfig.getUser(),
                    DBConfig.getPassword()
            );
            //Establecer el autocommit como falso
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PARTICIPACION VALUES (?,?,?,?)");

            //Statement para tomar el ID máximo --> Para asignar las posiciones
            Statement statement = connection.createStatement();
            String idCiclistaMax = "SELECT MAX(ID_CICLISTA) AS ID FROM PARTICIPACION";
            ResultSet resultSet = statement.executeQuery(idCiclistaMax);
            int idMax = 0;
            if (resultSet.next()) {
                idMax = resultSet.getInt("ID");
            }

            //Mientras hayan números repetidos, no aumenta el tamaño del set
            while(posiciones.size() < idMax){
                posiciones.add(random.nextInt(idMax)+1);
            }

            //Convertir el set a una lista una vez estén todas las posiciones
            List<Integer> listaPosiciones = new ArrayList<>(posiciones);

            for (int i = 1; i < idMax; i++) {
                int puntos;
                //ID según el orden en el que vienen en la tabla
                preparedStatement.setInt(1, i);
                //Número de etapa del objeto
                preparedStatement.setInt(2, etapa.getNumeroEtapa());
                //Asignar las posiciones según la lista de posiciones
                preparedStatement.setInt(3, listaPosiciones.get(i));

                //Asignar las puntuaciones según la condición
                if (listaPosiciones.get(i) == 1){
                    puntos = 100;
                } else if (listaPosiciones.get(i) == 2){
                    puntos = 90;
                } else if (listaPosiciones.get(i) == 3){
                    puntos = 80;
                } else if (listaPosiciones.get(i) == 4){
                    puntos = 70;
                }  else if (listaPosiciones.get(i) == 5){
                    puntos = 60;
                } else {
                    puntos = 0;
                }
                preparedStatement.setInt(4, puntos);

                //Añadir todos los datos
                preparedStatement.addBatch();
            }
            //Ejecutar y hacer el commit
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Datos de participación insertados.");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos en Participación --> " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al hacer el rollback --> "+ ex.getMessage());
                }
            }
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
