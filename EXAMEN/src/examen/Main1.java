package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1 {
    
    public static void main(String[] args) {
         try (Stream<String> linies = Files.lines(Paths.get("c:\\temp\\vehicles.csv"))) {

            List<Vehicle> vehicles = 
                   linies.filter(linia -> !linia.isBlank() && !linia.startsWith("#"))
                  .map(linia -> linia.split(","))
                  .sorted((parts1, parts2) -> parts1[2].compareTo(parts2[2])) // ordenat de major a a menor 
                  .map(parts -> new Vehicle( parts[0].trim(),
                                            parts[1].trim(),
                                            Integer.parseInt(parts[2].trim()),
                                            Integer,parseFloatparts[3].trim() //parseig a float 
                                          )
                      )
                  .collect(Collectors.toList());
            
            // Imprimeix la llista
            vehicles.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

