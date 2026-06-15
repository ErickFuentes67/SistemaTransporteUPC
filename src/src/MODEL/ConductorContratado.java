package MODEL;

public class ConductorContratado extends Conductor{

    final double retencionTarifa = 0.15;

    public ConductorContratado(String cedula, String nombre, String telefono) {
        super(cedula, nombre, telefono);
    }

    @Override
    public double calcularValorNeto(double tarifaBase) {
        super.calcularValorNeto(tarifaBase);
        return tarifaBase - (tarifaBase * retencionTarifa);
    }

    @Override
    public String toString() {
        return "Conductor contratado{"
                + super.toString();
    }
}
