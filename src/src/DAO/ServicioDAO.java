package DAO;

import MODEL.Servicio;
import MODEL.Turno;
import MODEL.Vehiculo;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServicioDAO {

    private String archivo = "servicios.txt";

    public void guardar(@org.jetbrains.annotations.UnknownNullability ArrayList<Servicio> s) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(s.toString());
        } catch (IOException e) {
            System.out.println("Error al guardar servicio: " + e.getMessage());
        }
    }

    public ArrayList<Servicio> cargarTodos(ArrayList<Turno> turnos,
                                           ArrayList<Vehiculo> vehiculos) {
        ArrayList<Servicio> lista = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                // formato: idServicio;idTurno;placa;fecha;origen;destino;valorBruto;valorNeto
                String idServicio = p[0];
                String idTurno    = p[1];
                String placa      = p[2];
                LocalDate fecha   = LocalDate.parse(p[3]);
                String origen     = p[4];
                String destino    = p[5];
                double valorBruto = Double.parseDouble(p[6]);
                double valorNeto  = Double.parseDouble(p[7]);

                // Buscar Turno por id
                Turno turno = null;
                for (Turno t : turnos) {
                    if (t.getIdTurno().equals(idTurno)) {
                        turno = t;
                        break;
                    }
                }

                // Buscar Vehiculo por placa
                Vehiculo vehiculo = null;
                for (Vehiculo v : vehiculos) {
                    if (v.getPlaca().equals(placa)) {
                        vehiculo = v;
                        break;
                    }
                }

                if (turno != null && vehiculo != null) {
                    Servicio s = new Servicio(idServicio, turno, vehiculo,
                            fecha, origen, destino);
                    s.setValorBruto(valorBruto);
                    s.setValorNeto(valorNeto);
                    lista.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar servicios: " + e.getMessage());
        }
        return lista;
    }
}
