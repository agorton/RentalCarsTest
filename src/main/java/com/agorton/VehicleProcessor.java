package com.agorton;

import com.agorton.classes.Supplier;
import com.agorton.classes.Rating;
import com.agorton.classes.Vehicle;
import com.agorton.enums.CarType;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Vehicle Processor Class to hold all main processing of the Vehicle List. 
 * @author andrewgorton
 */
public class VehicleProcessor {
    private final ArrayList<Vehicle> vehicles;

    public VehicleProcessor(ArrayList<Vehicle> vehicleList) {
        this.vehicles = vehicleList;
    }
    
    /**
     * Sort all vehicles in the list into Ascending Price Order.
     * @return 
     */
    public ArrayList<Vehicle> sortVehiclesAscending() {
        Collections.sort(vehicles, (o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
        return vehicles;
    }
    
    /**
     * Return a list of String descriptions which the SIPP code represents.
     * @return 
     */
    public ArrayList<String> getSIPPStrings() {
        ArrayList<String> strings = new ArrayList<>();
        for (Vehicle V : vehicles) {
            strings.add(V.getName() + " - " + V.getSipp() + " - " + V.getFullSIPPString());
        }

        return strings;
    }
    
    /**
     * Return a list of Suppliers with ratings based off the passed in carType.
     * @param suppliers
     * @param value
     * @return 
     */
    public ArrayList<Supplier> getSupplierScorePerCarType(ArrayList<Supplier> suppliers, CarType value) {
        // build supplier rating.
        for (Supplier s : suppliers) {
            s.ratingPerCarType(vehicles, value);
        }

        // sort list comparing on rating.
        Collections.sort(suppliers, (o1, o2) -> o2.getRating().compareTo(o1.getRating()));

        return suppliers;
    }
    
    /**
     * Builds a list of Suppliers from the vehicle list.
     * @return 
     */
    public ArrayList<Supplier> buildSupplierList() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (!ContainSupplier(suppliers, v.getSupplier())) {
                suppliers.add(new Supplier(v.getSupplier()));
            }
        }
        return suppliers;
    }

    /**
     * Returns a list of vehicle ratings based off the vehicle breakdown and supplier rating.
     * @return 
     */
    public ArrayList<Rating> getVehicleRatings() {
        ArrayList<Supplier> suppliers = buildSupplierList();
        ArrayList<Rating> ratings = new ArrayList<>();

        for (Vehicle v : vehicles) {
            Supplier tempSup = suppliers.get(indexOfBySuppName(suppliers, v.getSupplier()));
            tempSup.ratingPerCarType(vehicles, v.getCarType());
            ratings.add(new Rating(v.getName(), v.getBreakdownScore(), tempSup.getRating()));
        }

        Collections.sort(ratings, (o1, o2) -> o2.getOverallRating().compareTo(o1.getOverallRating()));

        return ratings;
    }

    /*
     Helper Classes.
     */
    private int indexOfBySuppName(ArrayList<Supplier> supp, String name) {
        for (int i = 0; i < supp.size(); i++) {
            if (supp.get(i) != null && supp.get(i).getName().equals(name)) {
                return i;
            }
        }

        return -1;
    }

    private boolean ContainSupplier(ArrayList<Supplier> supp, String name) {
        for (Supplier s : supp) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
