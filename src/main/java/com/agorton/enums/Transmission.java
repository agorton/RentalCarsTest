package com.agorton.enums;

/**
 *
 * @author andrewgorton
 */
public enum Transmission {

    M("Manual"),
    A("Automatic");
    
    private final String value;

    Transmission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
