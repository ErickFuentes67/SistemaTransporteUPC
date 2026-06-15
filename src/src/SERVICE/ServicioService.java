package SERVICE;

import DAO.ServicioDAO;
import MODEL.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class ServicioService {
    private ServicioDAO dao;
    private ArrayList<Servicio> servicios;
    private TurnoService turnoService;
    private VehiculoService vehiculoService;

    public ServicioService(TurnoService turnoService, VehiculoService vehiculoService) {
        this.turnoService = turnoService;
        this.vehiculoService = vehiculoService;
        dao = new ServicioDAO();
        servicios = dao.cargarTodos(turnoService.listar(), vehiculoService.listar());
    }

    public void guardarTodos() {
        dao.guardar(servicios);
    }

    public ArrayList<Servicio> listar() {
        return new ArrayList<>(servicios);
    }

    public Servicio buscarPorId(String idServicio) {
        for (Servicio s : servicios) {
            if (s.getIdServicio().equals(idServicio)) {
                return s;
            }
        }
        return null;
    }

    // Determina temporada según fecha
    private String obtenerTemporada(LocalDate fecha) {
        int mes = fecha.getMonthValue();
        if ((mes >= 1 && mes <= 3) || (mes >= 8 && mes <= 10)) return "Baja";
        if ((mes >= 4 && mes <= 5) || mes == 11) return "Media";
        if ((mes >= 6 && mes <= 7) || mes == 12) return "Alta";
        return "Baja"; // por defecto
    }

    // Obtiene tarifa base según tipo de vehículo y temporada
    private double obtenerTarifaBase(Vehiculo v, String temporada) {
        String tipo = v.getClass().getSimpleName();
        switch (temporada) {
            case "Baja":
                switch (tipo) {
                    case "Mototaxi": return 4500;
                    case "Automovil": return 8000;
                    case "Van": return 14000;
                }
                break;
            case "Media":
                switch (tipo) {
                    case "Mototaxi": return 5500;
                    case "Automovil": return 10000;
                    case "Van": return 18000;
                }
                break;
            case "Alta":
                switch (tipo) {
                    case "Mototaxi": return 7000;
                    case "Automovil": return 13000;
                    case "Van": return 23000;
                }
                break;
        }
        return 0;
    }

    // Registra un nuevo servicio y calcula valor bruto y neto automáticamente
    public boolean registrar(String idTurno, String placaVehiculo, LocalDate fecha,
                             String origen, String destino) {
        Turno turno = turnoService.buscarPorId(idTurno);
        if (turno == null) {
            System.out.println("Error: turno no existe.");
            return false;
        }
        if (!turno.isActivo()) {
            System.out.println("Error: el turno ya está finalizado, no se pueden agregar servicios.");
            return false;
        }
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placaVehiculo);
        if (vehiculo == null) {
            System.out.println("Error: vehículo no existe.");
            return false;
        }
        if (!vehiculo.isEstado()) {
            System.out.println("Error: el vehículo no está operativo.");
            return false;
        }

        // Calcular valor bruto según temporada
        String temporada = obtenerTemporada(fecha);
        double valorBruto = obtenerTarifaBase(vehiculo, temporada);

        // Calcular valor neto usando polimorfismo del conductor
        Conductor conductor = turno.getConductor();
        double valorNeto = conductor.calcularValorNeto(valorBruto);

        String id = UUID.randomUUID().toString();
        Servicio servicio = new Servicio(id, turno, vehiculo, fecha, origen, destino);
        servicio.setValorBruto(valorBruto);
        servicio.setValorNeto(valorNeto);

        servicios.add(servicio);
        guardarTodos();
        return true;
    }

    public boolean eliminar(String idServicio) {
        Servicio s = buscarPorId(idServicio);
        if (s == null) return false;
        servicios.remove(s);
        guardarTodos();
        return true;
    }

    // Reporte total recaudado por un conductor en un período (fecha inicio y fin inclusive)
    public double totalRecaudadoPorConductor(String cedulaConductor, LocalDate fechaInicio, LocalDate fechaFin) {
        double total = 0.0;
        for (Servicio s : servicios) {
            LocalDate fechaServicio = s.getFechaServicio();
            if (!fechaServicio.isBefore(fechaInicio) && !fechaServicio.isAfter(fechaFin)) {
                Conductor conductor = s.getTurno().getConductor();
                if (conductor.getCedula().equals(cedulaConductor)) {
                    total += s.getValorNeto();
                }
            }
        }
        return total;
    }
}
