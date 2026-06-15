package MODEL;

public class Automovil extends Vehiculo {

    public Automovil(String ruta, String placa) {
        super(ruta, placa);
        capacidadMax = 4;
        tarifaBaja = 8000;
        tarifaMedia = 10000;
        tarifaAlta = 13000;
    }

    @Override
    public String toString() {
        return "Automovil{" +
                super.toString();
    }
}
