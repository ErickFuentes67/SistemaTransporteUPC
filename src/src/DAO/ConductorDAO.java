package DAO;

import MODEL.*;

import java.io.*;
import java.util.ArrayList;

public class ConductorDAO {

    private String archivo = "Conductores.txt";

    public void guardar (Conductor conductor) {
        try {
            FileWriter fichero = new FileWriter(archivo, true);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
        }
    }

    public ArrayList<Conductor> cargarTodos(){
        ArrayList<Conductor> lista = new ArrayList<>();
        File file = new File(archivo);
        if(!file.exists()){return lista;}

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                if(linea.trim().isEmpty())continue;
                String[] p = linea.split(",");

                String cedula = p[0];
                String nombre = p[1];
                String telefono = p[2];
                String tipo = p[3];

                Conductor c = null;
                switch (tipo){
                    case "Regular": c = new ConductorRegular(cedula, nombre, telefono);
                    case "Contratado": c = new ConductorContratado(cedula, nombre, telefono);
                    case "Propietario": c = new ConductorPropietario(cedula, nombre, telefono);
                }
                if (c != null) {
                    lista.add(c);
                }
            }
        } catch (IOException e){
            System.out.println("Error al cargar los Conductores");
        }
        return lista;
    }
}
