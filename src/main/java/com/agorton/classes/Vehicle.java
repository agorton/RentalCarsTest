package com.agorton.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.agorton.enums.CarType;
import com.agorton.enums.Doors;
import com.agorton.enums.FuelAC;
import com.agorton.enums.Transmission;

/**
 * @author andrewgorton
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {

    @JsonProperty
    private String sipp;

    @JsonProperty
    private String name;

    @JsonProperty
    private String supplier;

    @JsonProperty
    private Double price;

    @JsonProperty
    private double rating;

    private Double breakdownScore = 0.0;

    public Vehicle() {
    }

    public Double getBreakdownScore() {
        // check if automatic, otherwise must be manual.
        if (this.getTransmission().equals(Transmission.M.getValue())) {
            breakdownScore += 5;
        } else {
            breakdownScore += 1;
        }
        if (this.getFuelAC().equals(FuelAC.R.getValue())) {
            breakdownScore += 1;
        }
        return breakdownScore;
    }
    
    public String getSipp() {
        return sipp;
    }

    public void setSipp(String sipp) {
        this.sipp = sipp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name + " - £" + price;
    }

    public CarType getCarType() {
        for(CarType ct : CarType.values()){
            if(ct.name().charAt(0) == this.sipp.charAt(0)){
                return ct;
            }
        }
        return null;
    }

    public String getTransmission() {
        return this.processSIPPTrans(this.sipp.charAt(2));
    }

    public String getFuelAC() {
        return this.processSIPPFuelAC(this.sipp.charAt(3));
    }

    public String getFullSIPPString() {
        String result = "";
        for (int i = 0; i < sipp.length(); i++) {
            result += processChar(sipp.charAt(i), i) + " ";
        }
        return result;
    }

    private String processChar(char value, int position) {
        switch (position) {
            case 0:
                return processSIPPCarType(value);

            case 1:
                return processSIPPDoors(value);

            case 2:
                return processSIPPTrans(value);

            case 3:
                return processSIPPFuelAC(value);

        }
        return "";
    }

    private String processSIPPCarType(char value) {
        for (CarType ct : CarType.values()) {
            if (ct.name().charAt(0) == (value)) {
                return ct.getValue();
            }
        }
        return "";
    }

    private String processSIPPDoors(char value) {
        for (Doors d : Doors.values()) {
            if (d.name().charAt(0) == (value)) {
                return d.getValue();
            }
        }
        return "";
    }

    private String processSIPPTrans(char value) {
        for (Transmission ct : Transmission.values()) {
            if (ct.name().charAt(0) == (value)) {
                return ct.getValue();
            }
        }
        return "";
    }

    private String processSIPPFuelAC(char value) {
        for (FuelAC ct : FuelAC.values()) {
            if (ct.name().charAt(0) == (value)) {
                return ct.getValue();
            }
        }
        return "";
    }

}
