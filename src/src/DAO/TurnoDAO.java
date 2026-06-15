package DAO;

import MODEL.Turno;
import MODEL.Conductor;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TurnoDAO {
    private String archivo = "turnos.txt";

    public void guardar(ArrayList<Turno> t) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(t.toString());
        } catch (IOException e) {
            System.out.println("Error al guardar turno: " + e.getMessage());
        }
    }

    public ArrayList<Turno> cargarTodos(ArrayList<Conductor> conductores) {
        ArrayList<Turno> lista = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                // formato: idTurno;cedulaConductor;fechaInicio;fechaFin;activo
                String idTurno        = p[0];
                String cedulaConductor = p[1];
                LocalDateTime inicio  = LocalDateTime.parse(p[2]);
                LocalDateTime fin     = LocalDateTime.parse(p[3]);
                boolean activo        = Boolean.parseBoolean(p[4]);

                // Buscar el objeto Conductor por cédula en la lista recibida
                Conductor conductor = null;
                for (Conductor c : conductores) {
                    if (c.getCedula().equals(cedulaConductor)) {
                        conductor = c;
                        break;
                    }
                }

                if (conductor != null) {
                    Turno t = new Turno(idTurno, conductor, inicio, fin);
                    t.setActivo(activo);
                    lista.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar turnos: " + e.getMessage());
        }
        return lista;
    }
}
