package com.agorton;

import com.agorton.JSON.JSONResponse;
import com.agorton.classes.Vehicle;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentalcarstestApplication.class)
public class RentalcarstestApplicationTests {

    private final RentalcarstestApplication myApp = new RentalcarstestApplication();
    private ArrayList<Vehicle> vehicleList = new ArrayList<>();
    private VehicleProcessor vp;
    
    @Before
    public void setUpBeforeClass(){
        JSONResponse resp = myApp.readVehicleJSON("http://www.rentalcars.com/js/vehicles.json");
        vehicleList = resp.getSearch().getVehicleList();
        vp = new VehicleProcessor(vehicleList);
    }
    
    @Test
    public void contextLoads() {
    }

    @Test
    /**
     * Test to assert that the JSON is being correctly read and processed into a Vehicle List.
     */
    public void readJSONTest() {
        JSONResponse resp = myApp.readVehicleJSON("http://www.rentalcars.com/js/vehicles.json");
        assertTrue(resp.getSearch().getVehicleList().size() == 31);
    }

    @Test
    /**
     * Method to test variations of SIPP codes to ensure that the correct strings are returned.
     */
    public void parseSippTest() {
        assertTrue(cdmrSIPP() && fvanSIPP());
    }
    
    /**
     * Assert the correct string is returned when parsing SIPP code "CDMR". 
     * @return assertion of the correct parsing.
     */
    private boolean cdmrSIPP(){
        Vehicle tempVeh = new Vehicle();
        tempVeh.setSipp("CDMR");
        return (tempVeh.getFullSIPPString().trim().equals("Compact 5 doors Manual Petrol / AC"));
    }
    
    /**
     * Assert the correct string is returned when parsing SIPP code "FVAN". 
     * @return assertion of the correct parsing.
     */
    private boolean fvanSIPP(){
        Vehicle tempVeh = new Vehicle();
        tempVeh.setSipp("FVAN");
        return (tempVeh.getFullSIPPString().trim().equals("Full size Passenger Van Automatic Petrol / No AC"));
    }
    
    @Test
    /**
     * Assert that the vehicle list is correctly ordered such that the first value is the lowest.
     */
    public void assertPriceOrderAscending(){
        ArrayList<Vehicle> temp = vp.sortVehiclesAscending();
        assertTrue(temp.get(0).getPrice() <= temp.get(temp.size()-1).getPrice());
    }
}
