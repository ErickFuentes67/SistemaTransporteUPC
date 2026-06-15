package VIEW;

import SERVICE.*;
import MODEL.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static VehiculoService vehiculoService;
    private static ConductorService conductorService;
    private static TurnoService turnoService;
    private static ServicioService servicioService;

    public static void main(String[] args) {
        // Inicializar servicios en orden correcto
        conductorService = new ConductorService();
        vehiculoService = new VehiculoService();
        turnoService = new TurnoService(conductorService);
        servicioService = new ServicioService(turnoService, vehiculoService);

        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN TAXIUPC ===");
            System.out.println("1. Registrar conductor");
            System.out.println("2. Listar conductores");
            System.out.println("3. Buscar conductor por cédula");
            System.out.println("4. Actualizar conductor");
            System.out.println("5. Eliminar conductor");
            System.out.println("6. Registrar vehículo");
            System.out.println("7. Listar vehículos");
            System.out.println("8. Buscar vehículo por placa");
            System.out.println("9. Actualizar vehículo");
            System.out.println("10. Eliminar vehículo");
            System.out.println("11. Registrar turno");
            System.out.println("12. Listar turnos");
            System.out.println("13. Finalizar turno");
            System.out.println("14. Eliminar turno");
            System.out.println("15. Registrar servicio");
            System.out.println("16. Listar servicios");
            System.out.println("17. Eliminar servicio");
            System.out.println("18. Reporte total recaudado por conductor");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: registrarConductor(); break;
                case 2: listarConductores(); break;
                case 3: buscarConductor(); break;
                case 4: actualizarConductor(); break;
                case 5: eliminarConductor(); break;
                case 6: registrarVehiculo(); break;
                case 7: listarVehiculos(); break;
                case 8: buscarVehiculo(); break;
                case 9: actualizarVehiculo(); break;
                case 10: eliminarVehiculo(); break;
                case 11: registrarTurno(); break;
                case 12: listarTurnos(); break;
                case 13: finalizarTurno(); break;
                case 14: eliminarTurno(); break;
                case 15: registrarServicio(); break;
                case 16: listarServicios(); break;
                case 17: eliminarServicio(); break;
                case 18: reporteRecaudado(); break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void registrarConductor() {
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Tipo (Regular / Propietario / Contratado): ");
        String tipo = sc.nextLine();

        Conductor c = null;
        switch (tipo.toLowerCase()) {
            case "regular":
                c = new ConductorRegular(cedula, nombre, telefono);
                break;
            case "propietario":
                c = new ConductorPropietario(cedula, nombre, telefono);
                break;
            case "contratado":
                System.out.print("Mes del contrato (ej. Enero): ");
                String mes = sc.nextLine();
                c = new ConductorContratado(cedula, nombre, telefono, mes);
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }
        if (conductorService.registrar(c))
            System.out.println("Conductor registrado con éxito.");
    }

    private static void listarConductores() {
        for (Conductor c : conductorService.listar())
            System.out.println(c);
    }

    private static void buscarConductor() {
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        Conductor c = conductorService.buscarPorCedula(cedula);
        System.out.println(c != null ? c : "No encontrado.");
    }

    private static void actualizarConductor() {
        System.out.print("Cédula del conductor a actualizar: ");
        String cedula = sc.nextLine();
        Conductor existente = conductorService.buscarPorCedula(cedula);
        if (existente == null) {
            System.out.println("No existe.");
            return;
        }
        System.out.print("Nuevo nombre (" + existente.getNombre() + "): ");
        String nombre = sc.nextLine();
        if (nombre.isEmpty()) nombre = existente.getNombre();
        System.out.print("Nuevo teléfono (" + existente.getTelefono() + "): ");
        String telefono = sc.nextLine();
        if (telefono.isEmpty()) telefono = existente.getTelefono();

        // Se crea un conductor temporal (mismo tipo) con los nuevos datos
        Conductor nuevos = null;
        if (existente instanceof ConductorRegular)
            nuevos = new ConductorRegular(cedula, nombre, telefono);
        else if (existente instanceof ConductorPropietario)
            nuevos = new ConductorPropietario(cedula, nombre, telefono);
        else if (existente instanceof ConductorContratado) {
            String mes = ((ConductorContratado) existente).getMesContrato();
            nuevos = new ConductorContratado(cedula, nombre, telefono, mes);
        }
        if (conductorService.actualizar(cedula, nuevos))
            System.out.println("Actualizado.");
    }

    private static void eliminarConductor() {
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        if (conductorService.eliminar(cedula))
            System.out.println("Eliminado.");
        else
            System.out.println("No existe o no se pudo eliminar.");
    }

    private static void registrarVehiculo() {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Ruta (ej. Centro - Campus): ");
        String ruta = sc.nextLine();
        System.out.print("Tipo (Mototaxi / Automovil / Van): ");
        String tipo = sc.nextLine();

        Vehiculo v = null;
        switch (tipo.toLowerCase()) {
            case "mototaxi": v = new Mototaxi(placa, ruta); break;
            case "automovil": v = new Automovil(placa, ruta); break;
            case "van": v = new Van(placa, ruta); break;
            default: System.out.println("Tipo inválido."); return;
        }
        if (vehiculoService.registrar(v))
            System.out.println("Vehículo registrado.");
    }

    private static void listarVehiculos() {
        for (Vehiculo v : vehiculoService.listar())
            System.out.println(v);
    }

    private static void buscarVehiculo() {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        Vehiculo v = vehiculoService.buscarPorPlaca(placa);
        System.out.println(v != null ? v : "No encontrado.");
    }

    private static void actualizarVehiculo() {
        System.out.print("Placa del vehículo: ");
        String placa = sc.nextLine();
        Vehiculo existente = vehiculoService.buscarPorPlaca(placa);
        if (existente == null) {
            System.out.println("No existe.");
            return;
        }
        System.out.print("Nueva ruta (" + existente.getRuta() + "): ");
        String ruta = sc.nextLine();
        if (ruta.isEmpty()) ruta = existente.getRuta();
        System.out.print("Estado (true=activo/false=inactivo) actual: " + existente.isEstado() + ": ");
        String estadoStr = sc.nextLine();
        boolean estado = estadoStr.isEmpty() ? existente.isEstado() : Boolean.parseBoolean(estadoStr);

        Vehiculo nuevos = null;
        if (existente instanceof Mototaxi) nuevos = new Mototaxi(placa, ruta);
        else if (existente instanceof Automovil) nuevos = new Automovil(placa, ruta);
        else nuevos = new Van(placa, ruta);
        nuevos.setEstado(estado);

        if (vehiculoService.actualizar(placa, nuevos))
            System.out.println("Actualizado.");
    }

    private static void eliminarVehiculo() {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        if (vehiculoService.eliminar(placa))
            System.out.println("Eliminado.");
        else
            System.out.println("No existe.");
    }

    private static void registrarTurno() {
        System.out.print("Cédula del conductor: ");
        String cedula = sc.nextLine();
        System.out.print("Fecha y hora inicio (yyyy-MM-dd HH:mm): ");
        LocalDateTime inicio = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.print("Fecha y hora fin (yyyy-MM-dd HH:mm): ");
        LocalDateTime fin = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        if (turnoService.registrar(cedula, inicio, fin))
            System.out.println("Turno registrado.");
    }

    private static void listarTurnos() {
        for (Turno t : turnoService.listar())
            System.out.println(t);
    }

    private static void finalizarTurno() {
        System.out.print("ID del turno: ");
        String id = sc.nextLine();
        if (turnoService.finalizarTurno(id))
            System.out.println("Turno finalizado.");
        else
            System.out.println("Error al finalizar.");
    }

    private static void eliminarTurno() {
        System.out.print("ID del turno: ");
        String id = sc.nextLine();
        if (turnoService.eliminar(id))
            System.out.println("Eliminado.");
        else
            System.out.println("No existe.");
    }

    private static void registrarServicio() {
        System.out.print("ID del turno: ");
        String idTurno = sc.nextLine();
        System.out.print("Placa del vehículo: ");
        String placa = sc.nextLine();
        System.out.print("Fecha del servicio (yyyy-MM-dd): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine());
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        if (servicioService.registrar(idTurno, placa, fecha, origen, destino))
            System.out.println("Servicio registrado. Valor neto calculado automáticamente.");
    }

    private static void listarServicios() {
        for (Servicio s : servicioService.listar())
            System.out.println(s);
    }

    private static void eliminarServicio() {
        System.out.print("ID del servicio: ");
        String id = sc.nextLine();
        if (servicioService.eliminar(id))
            System.out.println("Eliminado.");
        else
            System.out.println("No existe.");
    }

    private static void reporteRecaudado() {
        System.out.print("Cédula del conductor: ");
        String cedula = sc.nextLine();
        System.out.print("Fecha inicio (yyyy-MM-dd): ");
        LocalDate inicio = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha fin (yyyy-MM-dd): ");
        LocalDate fin = LocalDate.parse(sc.nextLine());
        double total = servicioService.totalRecaudadoPorConductor(cedula, inicio, fin);
        System.out.printf("Total recaudado por el conductor %s entre %s y %s: $%.2f%n",
                cedula, inicio, fin, total);
    }
}
