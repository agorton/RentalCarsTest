package com.agorton.enums;

/**
 *
 * @author andrewgorton
 */
public enum FuelAC {

    N("Petrol / No AC"),
    R("Petrol / AC");

    private final String value;

    FuelAC(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
