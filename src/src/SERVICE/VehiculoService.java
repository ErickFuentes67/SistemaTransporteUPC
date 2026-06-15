package SERVICE;

import DAO.VehiculoDAO;
import MODEL.Vehiculo;

import java.util.ArrayList;

public class VehiculoService {
    private ArrayList<Vehiculo> vehiculos;
    private VehiculoDAO dao = new VehiculoDAO();

    public VehiculoService() {
        dao = new VehiculoDAO();
        vehiculos = dao.cargarTodos();
    }

    public void guardarTodos() {
        dao.guardar(vehiculos);
    }

    public ArrayList<Vehiculo> listar(){
        return new ArrayList<>(vehiculos);
    }

    public Vehiculo buscarPorPlaca(String placa){
        for(Vehiculo v : vehiculos){
            if(v.getPlaca().equals(placa)){
                return v;
            }
        }
        return null;
    }

    public boolean registrar(Vehiculo v){
        if(buscarPorPlaca(v.getPlaca())!=null){
            System.out.println("Vehiculo existente");
            return false;
        }
        vehiculos.add(v);
        guardarTodos();
        return true;
    }

    public boolean actualizar(String placa, Vehiculo nuevosDatos){
        Vehiculo v = buscarPorPlaca(placa);
        if(v==null){
            System.out.println("Vehiculo no encontrado");
            return false;
        }

        v.setRuta(nuevosDatos.getRuta());
        v.setEstado(nuevosDatos.isEstado());
        guardarTodos();
        return true;
    }

    public boolean eliminar(String placa){
        Vehiculo v = buscarPorPlaca(placa);
        if(v==null){
            return false;
        }
        vehiculos.remove(v);
        guardarTodos();
        return true;
    }
}