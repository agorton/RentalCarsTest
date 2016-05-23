/*
 */

package com.agorton.classes;

/**
 * @author andrewgorton
 */
public class Rating {
    private final String vehicleName;
    private final Double vehicleRating, supplierRating, overallRating;

    public Rating(String vehicleName, Double vehicleRating, Double supplierRating) {
        this.vehicleRating = vehicleRating;
        this.supplierRating = supplierRating;
        this.overallRating = vehicleRating + supplierRating;
        this.vehicleName = vehicleName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public Double getVehicleRating() {
        return vehicleRating;
    }

    public Double getSupplierRating() {
        return supplierRating;
    }

    public Double getOverallRating() {
        return overallRating;
    }
    
    

}
