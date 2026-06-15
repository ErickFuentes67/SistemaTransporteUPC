package MODEL;

public class ConductorPropietario extends Conductor{

    final double tarifaAdicional = 0.10;

    public ConductorPropietario(String cedula, String nombre, String telefono) {
        super(cedula, nombre, telefono);
    }

    @Override
    double calcularValorNeto(double tarifaBase) {
        super.calcularValorNeto(tarifaBase);
        return  tarifaBase + (tarifaBase * tarifaAdicional);
    }

    @Override
    public String toString() {
        return "Conductor propietario{"
                + super.toString();
    }
}
