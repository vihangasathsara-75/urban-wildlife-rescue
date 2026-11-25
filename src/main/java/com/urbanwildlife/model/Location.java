package com.urbanwildlife.model;

import com.urbanwildlife.enums.LocationRisk;

public class Location {

    private String address;
    private String area;
    private LocationRisk risk;

    public Location(String address, String area, LocationRisk risk) {
        this.address = address;
        this.area = area;
        this.risk = risk;
    }

    public String getAddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public LocationRisk getRisk() {
        return risk;
    }

    @Override
    public String toString() {
        return address + ", " + area + " (" + risk + " risk)";
    }
}