package Ciclista;

import java.sql.SQLException;
import java.util.Scanner;

public class MainCiclista {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Número de Etapa: ");
        int numeroEtapa = sc.nextInt();

        sc.nextLine();
        System.out.println("Origen: ");
        String origen = sc.nextLine();

        System.out.println("Destino: ");
        String destino = sc.nextLine();

        System.out.println("Distancia: ");
        double distancia = sc.nextInt();

        sc.nextLine();
        System.out.println("Fecha: ");
        String fecha = sc.nextLine();

        //Crear el objeto etapa, además de insertar los datos en la tabla de SQL
        Etapa etapa = InsertarEtapa.insertarNuevaEtapa(numeroEtapa,origen,destino,distancia,fecha);

        //Insertar los datos en participación una vez ejecutado lo del primero método
        InsertarParticipacion.insertarRegistroCiclista(etapa);

        //Mostrar el resumen
        Resumen.mostrarResumenEtapa(etapa);
    }
}
