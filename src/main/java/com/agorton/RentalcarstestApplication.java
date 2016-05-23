package com.agorton;

import com.agorton.classes.Supplier;
import com.agorton.classes.Rating;
import com.agorton.JSON.JSONResponse;
import com.agorton.classes.Vehicle;
import com.agorton.enums.CarType;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RentalcarstestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RentalcarstestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        JSONResponse resp = readVehicleJSON("http://www.rentalcars.com/js/vehicles.json");
        ArrayList<Vehicle> vehicleList = resp.getSearch().getVehicleList();
        VehicleProcessor vp = new VehicleProcessor(vehicleList);
        
        printAscendingPrice(vp);
        printVehicleSIPP(vp);
        printSupplierScores(vp);
        printVehicleBreakdown(vp);
    }

    public JSONResponse readVehicleJSON(String address) {
        RestTemplate rt = new RestTemplate();
        JSONResponse results = rt.getForObject(address, JSONResponse.class);

        return results;
    }

    private void printAscendingPrice(VehicleProcessor vp) {
        System.out.println();
        System.out.println("Price-based Sort:");
        System.out.println("-----------------------");
        
        // sort list comparing on price.        
        for (Vehicle V : vp.sortVehiclesAscending()) {
            System.out.println(V.toString());
        }
    }
    
    private void printVehicleSIPP(VehicleProcessor vp) {
        System.out.println();
        System.out.println("SIPP Values:");
        System.out.println("-----------------------");
        ArrayList<String> strings = vp.getSIPPStrings();
        for (String V : strings) {
            System.out.println(V);
        }
    }
    
    private void printSupplierScores(VehicleProcessor vp) {
        System.out.println();
        System.out.println("Supplier Rating-based Sort:");
        System.out.println("-----------------------");
        ArrayList<Supplier> suppliers = vp.buildSupplierList();

        // for each car type.
        for (CarType value : CarType.values()) {
            suppliers = vp.getSupplierScorePerCarType(suppliers, value);

            System.out.println();
            System.out.println(value.getValue());
            System.out.println("-----------------------");
            for (Supplier s : suppliers) {
                System.out.printf("%s - %s \n", s.getName(), s.getRating());
            }
        }
    }
    
    private void printVehicleBreakdown(VehicleProcessor vp) {
        System.out.println();
        System.out.println("Vehicle Breakdowns");
        System.out.println("-----------------------");
        System.out.println("{Vehicle name} – {Vehicle score} – {Supplier rating} – {Sum of scores}");
        ArrayList<Rating> ratings = vp.getVehicleRatings();
        
        for(Rating r : ratings){
            System.out.printf("%s - %f - %f - %f\n", r.getVehicleName(), r.getVehicleRating(), r.getSupplierRating(), r.getOverallRating());
        }
    }
}
