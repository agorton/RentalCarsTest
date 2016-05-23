package com.agorton.JSON;

import com.agorton.classes.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 * @author andrewgorton
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Search {

    @JsonProperty
    private ArrayList<Vehicle> VehicleList;

    public Search() {
    }

    public ArrayList<Vehicle> getVehicleList() {
        return VehicleList;
    }

    public void setVehicleList(ArrayList<Vehicle> VehicleList) {
        this.VehicleList = VehicleList;
    }

 

}
