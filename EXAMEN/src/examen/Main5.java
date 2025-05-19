package examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main5 {
    
    public static void main(String[] args) throws SQLException, IOException{
        List<Vehicle> vehicles = Arrays.asList(
            new Vehicle("4321-JKL", "Ford","Focus",2019, 17000),
            new Vehicle("8765-MNO", "Hyundai", "Ioniq 5", 2023, 42000),
            new Vehicle("2109-PQR", "Peugeot", "308",2016, 14000));
       
        try{
            insertaVehicles(vehicles);
        }catch{
            System.err.println("Error al intentar insertar los vehiculos");
        }
          
    }
    private void insertaVehicles(List<Vehicle> vehicles) throws SQLException, IOException {
        try (Connection conn = gestorBBDD.getConnectionFromFile()  ) {
            conn.setAutoCommit(true);
            
            for (Vehicle v: vehicles)
                try {
                    gestorBBDD.executaSQL( conn, "INSERT INTO veuicles (matricula, marca, model, any, preu) VALUES (?,?,?,?,?)",
                                           v.getMatricula(), v.getMarca(), v.getModel(),v.getAny(), v.getPreu());

                }catch (SQLException e) {
                    if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062)
                        // Error per PK, modificar
                        gestorBBDD.executaSQL( conn, "INSERT INTO veuicles (matricula, marca, model, any, preu) VALUES (?,?,?,?,?)",
                                               v.getMatricula(), v.getMarca(), v.getModel(),v.getAny(), v.getPreu());
                    else
                        throw e; // Re-llança si no és error de PK
                }
        } catch (SQLException e) {        
            System.err.println("Error descarregant vehicles BBDD: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("");
        }
    }
}
