package MODEL;

public class ConductorRegular extends Conductor{

    public ConductorRegular(String cedula, String nombre, String telefono) {
        super(cedula, nombre, telefono);
    }

    @Override
    double calcularValorNeto(double tarifaBase) {
        super.calcularValorNeto(tarifaBase);
        return tarifaBase;
    }

    @Override
    public String toString() {
        return "Conductor regular" + super.toString();
    }
}
