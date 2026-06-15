package DAO;

import MODEL.Automovil;
import MODEL.Mototaxi;
import MODEL.Van;
import MODEL.Vehiculo;

import java.io.*;
import java.util.ArrayList;

public class VehiculoDAO {
    private String archivo = "vehiculos.txt";

    void guardar(Vehiculo v){
        try(PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))){
            pw.println(v.toString());
        } catch (IOException e){
            System.out.println("Error al guardar el vehiculo");
        }
    }

    public ArrayList<Vehiculo> cargarTodos(){
        ArrayList<Vehiculo> lista = new ArrayList<>();
        File file = new File(archivo);
        if(!file.exists()){return lista;}

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea = br.readLine()) != null){
                if(linea.trim().isEmpty())continue;
                String[] p = linea.split(",");

                String placa = p[0];
                String ruta = p[1];
                String tipo = p[2];
                boolean estado = Boolean.parseBoolean (p[3]);

                Vehiculo v = null;
                switch (tipo){
                    case "Mototaxi": v = new Mototaxi(placa,ruta);
                    case "Automovil": v = new Automovil(placa,ruta);
                    case "Van": v = new Van(placa,ruta);
                }
                if (v != null) {
                    v.setEstado(estado);
                    lista.add(v);
                }
            }
        } catch (IOException e){
            System.out.println("Error al cargar los vehiculos");
        }
        return lista;
    }
}
