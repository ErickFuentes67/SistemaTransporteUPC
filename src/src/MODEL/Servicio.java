package MODEL;

import java.time.LocalDate;

public class Servicio {
    private String idServicio;
    private Turno turno;
    private Vehiculo vehiculo;
    private LocalDate fechaServicio;
    private String origen;
    private String destino;
    private double valorBruto;
    private double valorNeto;

    public Servicio(String destino, LocalDate fechaServicio, String idServicio, String origen, Turno turno, Vehiculo vehiculo) {
        this.destino = destino;
        this.fechaServicio = fechaServicio;
        this.idServicio = idServicio;
        this.origen = origen;
        this.turno = turno;
        this.vehiculo = vehiculo;
    }

    public Servicio(String idServicio, Turno turno, Vehiculo vehiculo, LocalDate fecha, String origen, String destino) {
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public double getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(double valorNeto) {
        this.valorNeto = valorNeto;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public LocalDate getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(LocalDate fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public double getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(double valorBruto) {
        this.valorBruto = valorBruto;
    }

    @Override
    public String toString() {
        return "id Servicio='" + idServicio + '\'' +
                ", id Turno ='" + turno.idTurno + '\'' +
                ", placa ='" + vehiculo.placa + '\'' +
                ", fecha ='" + fechaServicio + '\'' +
                ", origen ='" + origen + '\'' +
                ", destino ='" + destino + '\'' +
                ", valor Bruto ='" + valorBruto + '\'' +
                ", vallor Neto =" + valorNeto ;
    }
}
