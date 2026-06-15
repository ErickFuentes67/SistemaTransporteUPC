package MODEL;

public abstract class Vehiculo {
    int capacidadMax;
    String placa;
    String ruta;
    boolean estado;
    double tarifaBaja;
    double tarifaMedia;
    double tarifaAlta;

    public Vehiculo(String ruta, String placa) {
        this.ruta = ruta;
        this.placa = placa;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getRuta() {
        return ruta;
    }

    public String getPlaca() {
        return placa;
    }

    public double getTarifaAlta() {
        return tarifaAlta;
    }

    public double getTarifaBaja() {
        return tarifaBaja;
    }

    public double getTarifaMedia() {
        return tarifaMedia;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "placa='" + placa + '\'' +
                ", ruta='" + ruta + '\'' +
                ", capacidadMax=" + capacidadMax +
                ", estado=" + estado;
    }

    public void setRuta(String ruta) {

    }
}
