package SERVICE;

import DAO.TurnoDAO;
import MODEL.Turno;
import MODEL.Conductor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class TurnoService {
    private TurnoDAO dao;
    private ArrayList<Turno> turnos;
    private ConductorService conductorService;

    public TurnoService(ConductorService conductorService) {
        this.conductorService = conductorService;
        dao = new TurnoDAO();
        // Al cargar turnos, necesitamos la lista actual de conductores
        turnos = dao.cargarTodos(conductorService.listar());
    }

    public void guardarTodos() {
        dao.guardar(turnos);
    }

    public ArrayList<Turno> listar() {
        return new ArrayList<>(turnos);
    }

    public Turno buscarPorId(String idTurno) {
        for (Turno t : turnos) {
            if (t.getIdTurno().equals(idTurno)) {
                return t;
            }
        }
        return null;
    }

    private boolean conductorTieneTurnoActivo(Conductor c) {
        for (Turno t : turnos) {
            if (t.getConductor().getCedula().equals(c.getCedula()) && t.isActivo()) {
                return true;
            }
        }
        return false;
    }

    public boolean registrar(String cedulaConductor, LocalDateTime inicio, LocalDateTime fin) {
        // Validar duración máxima 12 horas
        if (inicio.plusHours(12).isBefore(fin)) {
            System.out.println("Error: la duración del turno no puede exceder 12 horas.");
            return false;
        }
        Conductor conductor = conductorService.buscarPorCedula(cedulaConductor);
        if (conductor == null) {
            System.out.println("Error: conductor no existe.");
            return false;
        }
        if (conductorTieneTurnoActivo(conductor)) {
            System.out.println("Error: el conductor ya tiene un turno activo.");
            return false;
        }
        String id = UUID.randomUUID().toString();
        Turno turno = new Turno(id, conductor, inicio, fin);
        turnos.add(turno);
        guardarTodos();
        return true;
    }

    public boolean finalizarTurno(String idTurno) {
        Turno t = buscarPorId(idTurno);
        if (t == null) return false;
        if (!t.isActivo()) {
            System.out.println("El turno ya estaba finalizado.");
            return false;
        }
        t.setActivo(false);
        t.setFechaFin(LocalDateTime.now());
        guardarTodos();
        return true;
    }

    public boolean eliminar(String idTurno) {
        Turno t = buscarPorId(idTurno);
        if (t == null) return false;
        turnos.remove(t);
        guardarTodos();
        return true;
    }
}
