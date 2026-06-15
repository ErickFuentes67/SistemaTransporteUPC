package MODEL;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Turno {
    String idTurno;
    Conductor conductor;
    LocalDateTime fechaInicio;
    LocalDateTime fechaFin;
    boolean activo;

    public Turno(String idTurno, LocalDateTime fechaInicio, LocalDateTime fechaFin, Conductor conductor) {
        this.idTurno = idTurno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.conductor = conductor;
    }

    public Turno(String idTurno, Conductor conductor, LocalDateTime inicio, LocalDateTime fin) {
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    @Override
    public String toString() {
        return "id Turno='" + idTurno + '\'' +
                ", cedulaconductor ='" + conductor.cedula + '\'' +
                ", fecha inicio ='" + fechaInicio + '\'' +
                ", fecha fin ='" + fechaFin + '\'' +
                ", activo =" + activo ;
    }
}
