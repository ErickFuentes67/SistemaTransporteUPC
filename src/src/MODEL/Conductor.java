package MODEL;

public abstract class Conductor {
    String nombre;
    String cedula;
    double tarifaBase;

    void calcularValorNeto(double tarifaBase){};

}
