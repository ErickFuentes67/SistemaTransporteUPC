package MODEL;

public class Mototaxi extends Vehiculo{

    public Mototaxi(String ruta, String placa) {
        super(ruta, placa);

        capacidadMax = 4;
        tarifaBaja = 4500;
        tarifaMedia = 5500;
        tarifaAlta = 7000;
    }

    @Override
    public String toString() {
        return "Mototaxi{" +
                super.toString();
    }
}
