package examen;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main3 {

    public static void main(String[] args) {
        int posPreu = 4;
        List<Vehicle> vehicles = Arrays.asList(
            new Vehicle("4321-JKL", "Ford","Focus",2019, 17000),
            new Vehicle("8765-MNO", "Hyundai", "Ioniq 5", 2023, 42000),
            new Vehicle("2109-PQR", "Peugeot", "308",2016, 14000));
        
        //Amb function
        Function<Vehicle, Vehicle>incrementaPreu = i -> i.setPreu(i[4]*1,1);
        
        for (Vehicle v : vehicles){
            incrementaPreu.apply(v);
            
            v.toString();
        } 
    }
    
}

