package MODEL;

public abstract class Conductor {
    String nombre;
    String cedula;
    String telefono;

    public Conductor(String cedula, String nombre, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    void calcularValorNeto(double tarifaBase){};

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", telefono=" + telefono ;
    }

}
