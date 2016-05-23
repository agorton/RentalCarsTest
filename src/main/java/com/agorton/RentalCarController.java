/*
 */

package com.agorton;

import com.agorton.classes.Supplier;
import com.agorton.classes.Rating;
import com.agorton.classes.Vehicle;
import com.agorton.enums.CarType;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andrewgorton
 */
@RestController
public class RentalCarController {
    RentalcarstestApplication myApp = new RentalcarstestApplication();
    ArrayList<Vehicle> vehList = myApp.readVehicleJSON("http://www.rentalcars.com/js/vehicles.json").getSearch().getVehicleList();
    VehicleProcessor vp = new VehicleProcessor(vehList);
    
    @RequestMapping("/getVehicles")
    public ArrayList<Vehicle> getVehicles(){
        return vp.sortVehiclesAscending();
    }
    
    @RequestMapping("/getVehicleSIPP")
    public ArrayList<String> getVehicleSIPP(){
        return vp.getSIPPStrings();
    }
    
    @RequestMapping("/getVehicleFullRating")
    public ArrayList<Rating> getVehicleFullRating(){
        return vp.getVehicleRatings();
    }
    
    @RequestMapping("/getSupplierRating")
    public ArrayList<Supplier> getVehicleFullRating(@RequestParam(value="carType", required=false, defaultValue="C") CarType type){
        return vp.getSupplierScorePerCarType(vp.buildSupplierList(), type);
    }
}
