package examen;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main4 {

    public static void main(String[] args) {
        String servidor = "jdbc:mysql://192.168.126.245:3306/";
        String bdades = "gbd";
        String usuari = "root";
        String passwd = "";
        String sql = """
                     SELECT *
                     FROM vehicles
                     WHERE any >= 2020""";
        
        List<Vehicle> vehicles = new ArrayList<>();
       
        // Establir la connexió
        try ( Connection connexio = DriverManager.getConnection(servidor+bdades, usuari, passwd);
              java.sql.Statement statement = connexio.createStatement();
              ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("Connexió amb la base de dades MySQL exitosa.");
            
            // Processar els resultats de la Query
            while (resultSet.next()) {
                Vehicle v = new Vehicle( resultSet.getString("matricula"),
                                                    resultSet.getString("marca")
                                                   resultSet.getString("model"),
                                                    resultSet.getInt("any"), 
                                                      resultSet.getDouble("preu")
                                      );
                vehicles.add(v);
            }

            // Serialització
            try ( FileOutputStream fos = new FileOutputStream("C:\\temp\\vehicles.csv");
                  ObjectOutputStream out = new ObjectOutputStream(fos)
                ) {
                // Iterar cada 'employee'
                for (Vehicle i : vehicles) {
                    System.out.println(i.toString());
                    out.writeObject(i);
                }
                
            } catch (IOException i) {
                System.out.println("Exception writing 'Empleats': " + i);
            }
            
            System.out.println("Connexió tancada.");
        } catch (SQLException e) {
            System.err.println("Error al conectarse a la base de dades: " + e.getMessage());
        }
        
    }
    
}