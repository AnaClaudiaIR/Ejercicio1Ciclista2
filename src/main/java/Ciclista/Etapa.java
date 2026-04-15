package Ciclista;

//Clase para crear el objeto Etapa --> Para tener el ID de la etapa
public class Etapa {
    private int numeroEtapa;
    private String origen;
    private String destino;
    private double distancia;
    private String fecha;

    public Etapa(int numeroEtapa, String origen, String destino, double distancia, String fecha) {
        this.numeroEtapa = numeroEtapa;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.fecha = fecha;
    }

    public int getNumeroEtapa() {
        return numeroEtapa;
    }

    public void setNumeroEtapa(int numeroEtapa) {
        this.numeroEtapa = numeroEtapa;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
