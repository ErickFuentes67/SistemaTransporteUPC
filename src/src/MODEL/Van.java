package MODEL;

public class Van extends Vehiculo{

    public Van(String ruta, String placa) {
        super(ruta, placa);

        capacidadMax = 8;
        tarifaBaja = 14000;
        tarifaMedia = 18000;
        tarifaAlta = 23000;
    }

    @Override
    public String toString() {
        return "Van{" +
                super.toString();
    }
}
