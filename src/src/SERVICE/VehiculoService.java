package SERVICE;

import DAO.VehiculoDAO;
import MODEL.Vehiculo;

import java.util.ArrayList;

public class VehiculoService {
    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    VehiculoDAO dao = new VehiculoDAO();
}
