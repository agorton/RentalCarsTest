package com.agorton.classes;

import com.agorton.enums.CarType;
import java.util.ArrayList;

public class Supplier {

    private final String name;
    private Double rating = 0.0;

    public Supplier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void addRating(Double rating) {
        this.rating += rating;
    }

    public void ratingPerCarType(ArrayList<Vehicle> vehicles, CarType carType) {
        this.rating = 0.0;
        // find all cars of that type.
        for (Vehicle v : vehicles) {
            // add their rating to the supplier.
            if (v.getCarType().equals(carType) && v.getSupplier().equals(this.name)) {
                this.rating += v.getRating();
            }
        }
    }
}
