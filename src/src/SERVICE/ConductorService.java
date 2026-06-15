package SERVICE;

import DAO.ConductorDAO;
import MODEL.Conductor;

import java.util.ArrayList;

public class ConductorService {

    private ConductorDAO dao;
    private ArrayList<Conductor> conductores;

    public ConductorService() {
        dao = new ConductorDAO();
        conductores = dao.cargarTodos();
    }

    public void guardarTodos(){
        dao.guardar(conductores);
    }

    public ArrayList<Conductor> listar(){
        return new ArrayList<>(conductores);
    }

    public Conductor buscarPorCedula(String cedula){
        for (Conductor c : conductores) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    public boolean registrar(Conductor c){
        if (buscarPorCedula(c.getCedula()) != null) {
            System.out.println("Ya existe una conductor con ese cedula");
            return false;
        }
        conductores.add(c);
        guardarTodos();
        return true;
    }

    public boolean actualizar(String cedula, Conductor nuevosDatos){
        Conductor c = buscarPorCedula(cedula);
        if (c == null) return false;
        c.setNombre(nuevosDatos.getNombre());
        c.setTelefono(nuevosDatos.getTelefono());

        guardarTodos();
        return true;
    }

    public boolean eliminar(String cedula){
        Conductor c = buscarPorCedula(cedula);
        if (c == null) return false;
        conductores.remove(c);
        guardarTodos();
        return true;
    }
}
