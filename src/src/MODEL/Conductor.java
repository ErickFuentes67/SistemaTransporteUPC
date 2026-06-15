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

    public double calcularValorNeto(double tarifaBase){
        return tarifaBase;
    };

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", telefono=" + telefono ;
    }

    public void setNombre(String nombre) {
    }

    public void setTelefono(String telefono) {

    }
}
